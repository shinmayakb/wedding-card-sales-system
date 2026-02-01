package Website;

import Website.LoginFrame.UserSession;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UserCardPage extends JFrame {
    private Connection conn;
    private JTabbedPane tabbedPane;
    

    public UserCardPage() {
        setSize(1280, 910);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(207, 215, 255));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("Geetha Rani Cards");
        titleLabel.setForeground(new Color(0, 0, 128));
        titleLabel.setFont(new Font("Edwardian Script ITC", Font.BOLD, 48));
        topPanel.add(titleLabel, BorderLayout.WEST);

        JPanel rightButtonsWrapper = new JPanel();
        rightButtonsWrapper.setLayout(new BoxLayout(rightButtonsWrapper, BoxLayout.Y_AXIS));
        rightButtonsWrapper.setBackground(new Color(207, 215, 255));
        rightButtonsWrapper.setAlignmentY(Component.CENTER_ALIGNMENT);

        JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightButtons.setBackground(new Color(207, 215, 255));

        JButton cartButton = new JButton();
        cartButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
       
        cartButton.setBorder(null);
        ImageIcon icon = new ImageIcon("C:\\Users\\SHINU\\OneDrive\\문서\\miniproject\\wedding cards\\Icons\\cartbtn.png");
        Image cartimg = icon.getImage();
        Image newImg = cartimg.getScaledInstance(32, 32, Image.SCALE_SMOOTH); // Resize to 50x50
        icon = new ImageIcon(newImg);

        cartButton.setIcon(icon);
        cartButton.addActionListener(e -> {
            dispose();
            new ShoppingCartPage(UserSession.currentUserId).setVisible(true);
        });
        rightButtons.add(cartButton);

        JButton logoutButton = new JButton();
        logoutButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
        
        logoutButton.setBorder(null);
        ImageIcon licon = new ImageIcon("C:\\Users\\SHINU\\OneDrive\\문서\\miniproject\\wedding cards\\Icons\\logoutbtn.png");
        Image lgoutimg = licon.getImage();
        Image logoutImg = lgoutimg.getScaledInstance(32, 32, Image.SCALE_SMOOTH); // Resize to 50x50
        licon = new ImageIcon(logoutImg);
        logoutButton.setIcon(licon);
        
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        rightButtons.add(logoutButton);

        rightButtonsWrapper.add(Box.createVerticalGlue());
        rightButtonsWrapper.add(rightButtons);
        rightButtonsWrapper.add(Box.createVerticalGlue());

        topPanel.add(rightButtonsWrapper, BorderLayout.EAST);

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.WHITE);

        connectDatabase();

        tabbedPane.addTab("All Cards", createCardPanel("All"));
        tabbedPane.addTab("Hindu Cards", createCardPanel("Hindu"));
        tabbedPane.addTab("Muslim Cards", createCardPanel("Muslim"));
        tabbedPane.addTab("Christian Cards", createCardPanel("Christian"));
        JScrollPane scrollPane = new JScrollPane(tabbedPane);
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void connectDatabase() {
        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/project?useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "Shin@maya"
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage());
        }
    }

    private JPanel createCardPanel(String typeFilter) {
    
    JPanel cardPanel = new JPanel(new GridLayout(0, 5, 20, 20));
    cardPanel.setBackground(Color.WHITE);

    try {
        CallableStatement cs = conn.prepareCall("{CALL GetCardsByType(?)}");
        cs.setString(1, typeFilter);
        ResultSet rs = cs.executeQuery();

        while (rs.next()) {
            int cardId = rs.getInt("card_id");
            String cardName = rs.getString("name");
            float cardPrice = rs.getFloat("price");
            int availability = rs.getInt("available");
            String displayName = " ";
                if (availability == 0) {
                    displayName += " (Stock Unavailable)";
                }


            JButton cardButton = new JButton("<html><center>" + cardName + "<br>Price: " + cardPrice + "<br>" +displayName +"</center></html>");
            cardButton.setPreferredSize(new Dimension(200, 200));
            cardButton.setBackground(Color.WHITE);

            String imagePath = rs.getString("image_path");
            if (imagePath != null && !imagePath.isEmpty()) {
                ImageIcon icon = new ImageIcon(imagePath);
                Image img = icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
                cardButton.setIcon(new ImageIcon(img));
                cardButton.setHorizontalTextPosition(SwingConstants.CENTER);
                cardButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            }

            cardButton.addActionListener(e -> {
                dispose();
                new CardFrame(cardId).setVisible(true);
            });

            cardPanel.add(cardButton);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading cards: " + e.getMessage());
    }

    return cardPanel;
}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserCardPage().setVisible(true));
    }
}
