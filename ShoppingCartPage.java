package Website;

import Website.LoginFrame.UserSession;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShoppingCartPage extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JLabel subtotalLabel;
    private int userId;

    public ShoppingCartPage(int userId) {
        this.userId = userId;
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JButton backButton = new JButton("Back");
        ImageIcon licon = new ImageIcon("C:\\Users\\SHINU\\OneDrive\\문서\\miniproject\\wedding cards\\Icons\\backbtn.png");
       
        Image lgoutimg = licon.getImage();
        Image logoutImg = lgoutimg.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        licon = new ImageIcon(logoutImg);
        backButton.setIcon(licon);
        backButton.addActionListener(e -> {
            this.dispose();
            new UserCardPage().setVisible(true); 
        });
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

       
        model = new DefaultTableModel(new String[]{"Image", "Card Name", "Price", "Quantity", "Total"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 3; 
            }

            public Class<?> getColumnClass(int column) {
                return (column == 0) ? ImageIcon.class : Object.class;
            }
        };

        table = new JTable(model);
        table.setRowHeight(100);

        
        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String name = value.toString();
                if (name.contains("Stock Unavailable")) {
                    c.setForeground(Color.RED);
                } else {
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        
        table.getModel().addTableModelListener(e -> {
            if (e.getColumn() == 3) {
                int row = e.getFirstRow();
                updateQuantityInDB(row);
                calculateTotals();
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());

        subtotalLabel = new JLabel("Subtotal: ₹0.00");
        bottomPanel.add(subtotalLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        JButton removeBtn = new JButton("Remove Selected");
        removeBtn.addActionListener(e -> removeSelectedRow());
        buttonPanel.add(removeBtn);

        JButton purchaseBtn = new JButton("Purchase");
        purchaseBtn.addActionListener(e -> purchaseItems());
        buttonPanel.add(purchaseBtn);

        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        loadCartItems(userId);
    }

    private void loadCartItems(int userId) {
        try (Connection con = getConnection();
             CallableStatement cs = con.prepareCall("{call GetCartItemsByUserId(?)}")) {

            cs.setInt(1, userId);
            ResultSet rs = cs.executeQuery();
            model.setRowCount(0);

            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String imagePath = rs.getString("image_path");
                int quantity = rs.getInt("quantity");
                int availability = rs.getInt("available");

                String displayName = name;
                if (availability == 0) {
                    displayName += " (Stock Unavailable)";
                }

                ImageIcon icon;
                try {
                    Image img = new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(img);
                } catch (Exception e) {
                    icon = new ImageIcon(); 
                }

                double total = getTotalFromDB(price, quantity);
                model.addRow(new Object[]{icon, displayName, price, quantity, total});
            }

            calculateTotals();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading cart items: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(ShoppingCartPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateQuantityInDB(int row) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE cart SET quantity = ? WHERE userId = ? AND name = ?")) {

            int qty = Integer.parseInt(model.getValueAt(row, 3).toString());
            String fullName = model.getValueAt(row, 1).toString();
            String name = fullName.split(" \\(")[0]; 

            ps.setInt(1, qty);
            ps.setInt(2, userId);
            ps.setString(3, name);
            ps.executeUpdate();

            double price = Double.parseDouble(model.getValueAt(row, 2).toString());
            model.setValueAt(price * qty, row, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeSelectedRow() {
        int selected = table.getSelectedRow();
        if (selected >= 0) {
            String fullName = model.getValueAt(selected, 1).toString();
            String name = fullName.split(" \\(")[0]; 
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement("DELETE FROM cart WHERE userId = ? AND name = ?")) {

                ps.setInt(1, userId);
                ps.setString(2, name);
                ps.executeUpdate();
                model.removeRow(selected);
                calculateTotals();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void calculateTotals() {
        double subtotal = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            subtotal += Double.parseDouble(model.getValueAt(i, 4).toString());
        }
        subtotalLabel.setText("Subtotal: ₹" + String.format("%.2f", subtotal));
    }

    private void purchaseItems() {
        try (Connection con = getConnection()) {
            UserSession.currentUserId = userId;
            new PurchasePage(userId).setVisible(true);  
            dispose(); 
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error completing purchase: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(ShoppingCartPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private double getTotalFromDB(double price, int quantity) {
        double total = 0.0;
        try (Connection con = getConnection();
             CallableStatement cs = con.prepareCall("{?=call calculate_total(?,?)}")) {
            cs.registerOutParameter(1, Types.DOUBLE);
            cs.setDouble(1, price);
            cs.setInt(2, quantity);
            cs.execute();
            total = cs.getDouble(1);
            
        } catch (Exception ex) {
            Logger.getLogger(ShoppingCartPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/project?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String pass = "Shin@maya";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    public static void main(String[] args) {
        new ShoppingCartPage(1).setVisible(true);
    }
}
