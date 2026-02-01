
package Website;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import Website.LoginFrame.UserSession;
import java.awt.Image;
import java.sql.CallableStatement;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class PurchasePage extends javax.swing.JFrame {

    private int userId;
    public PurchasePage(int userid) {
        this.userId = userid;
        initComponents();
        connectDatabase();
        loadCartData();
        calculateTotals();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        CountryCombo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        StateCombo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        AddressField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        PayCombo = new javax.swing.JComboBox<>();
        PurchaseButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        CardTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        SubTotal = new javax.swing.JLabel();
        Shipping = new javax.swing.JLabel();
        Tax = new javax.swing.JLabel();
        TotalAmount = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(255, 153, 102));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("DELIVERY DETAILS");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setText("Country");

        CountryCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "India" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setText("State");

        StateCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal" }));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel8.setText("Address");

        AddressField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddressFieldActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setText("Payment Method");

        PayCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit Card", "Debit Card", "GPay", "PhonePe", "Paytm" }));

        PurchaseButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        PurchaseButton.setText("Purchase ");
        PurchaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PurchaseButtonActionPerformed(evt);
            }
        });

        CardTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Card", "Name", "Price", "Quantity", "Total Amount"
            }
        ));
        jScrollPane1.setViewportView(CardTable);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setText("Sub Total");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel11.setText("Shipping ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel13.setText("Tax (10%)");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel14.setText("Total Amount (including tax)");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Payment Summary");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addComponent(TotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addComponent(Shipping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Tax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(186, 186, 186))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Shipping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Tax, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(TotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addGap(26, 26, 26))
        );

        BackButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\SHINU\\Downloads\\3669231_cart_shopping_ic_icon.png")); // NOI18N
        BackButton.setText("BACK");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CountryCombo, 0, 261, Short.MAX_VALUE)
                            .addComponent(AddressField))
                        .addGap(104, 104, 104)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(StateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(69, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(PurchaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CountryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(AddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PurchaseButton)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddressFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddressFieldActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_AddressFieldActionPerformed

    private void PurchaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PurchaseButtonActionPerformed
        // TODO add your handling code here:
        saveOrderToDatabase();

    }//GEN-LAST:event_PurchaseButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();  
        new ShoppingCartPage(UserSession.currentUserId).setVisible(true);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void connectDatabase() {
    try {
        Connection con = conn.getConnection();  
        if (con != null) {
            System.out.println("️ Connected to DB from PurchasePage");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    private void loadCartData() {
        try {
            CallableStatement ps = conn.prepareCall("{CALL GetCartItemsByUserId(?)}");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) CardTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int qty = rs.getInt("quantity");
                double total = price * qty;
                // Load image from the path
               String imagePath = rs.getString("image_path");
               ImageIcon icon = new ImageIcon(imagePath);
               Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
               icon = new ImageIcon(scaledImage);
               model.addRow(new Object[]{icon, name, "₹" + price, qty, "₹" + total});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void calculateTotals() {
        double subtotal = 0;
        DefaultTableModel model = (DefaultTableModel) CardTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String totalStr = model.getValueAt(i, 4).toString().replace("₹", "");
            subtotal += Double.parseDouble(totalStr);
        }

        double tax = subtotal * 0.10;
        double shipping = 50;
        double total = subtotal + tax + shipping;

        SubTotal.setText("₹" + subtotal);
        Tax.setText("₹" + tax);
        Shipping.setText("50.00");
        TotalAmount.setText("₹" + total);
    }

   private void saveOrderToDatabase() {
    try {
        Connection con = conn.getConnection();

        String country = (String) CountryCombo.getSelectedItem();
        String state = (String) StateCombo.getSelectedItem();
        String address = AddressField.getText();
        String paymentMethod = (String) PayCombo.getSelectedItem();
        Double total = Double.parseDouble(TotalAmount.getText().replace("₹", "").trim());
        if(address.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please Fill Address Field");
        }
        else{
       PreparedStatement ps = con.prepareStatement(
            "INSERT INTO orders(user_id, card_name, quantity, amount, country, state, address, payment_method, order_date, total_price) " +
            "SELECT ?, c.name, ct.quantity, (c.price * ct.quantity), ?, ?, ?, ?, NOW(), ? " +
            "FROM cart ct JOIN cards c ON ct.card_id = c.card_id WHERE ct.userid = ?"
        );
        ps.setInt(1, userId);        
        ps.setString(2, country);     
        ps.setString(3, state);     
        ps.setString(4, address);    
        ps.setString(5, paymentMethod);
        ps.setDouble(6, total);      
        ps.setInt(7, userId);         

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println(" Order inserted for user ID: " + userId);
            JOptionPane.showMessageDialog(this, " Payment Successful!");
            showBillOnScreen();
            clearCart();
        } else {
            System.out.println("️ No rows inserted. Check cart for user ID: " + userId);
        }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Can't insert to Orders: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void showBillOnScreen() {
    JFrame billFrame = new JFrame("Invoice");
    billFrame.setSize(600, 600);
    billFrame.setLocationRelativeTo(null);

    JTextArea billArea = new JTextArea();
    billArea.setEditable(false);

    StringBuilder billText = new StringBuilder();
    billText.append("========= Geetha Cards - Invoice =========\n");
    billText.append("Date: ").append(new Date()).append("\n");
    billText.append("Further Details : 6379520078 (Also for Customization)\n\n");
    billText.append("Delivery Details:\n");
    billText.append("Country: ").append(CountryCombo.getSelectedItem()).append("\n");
    billText.append("State: ").append(StateCombo.getSelectedItem()).append("\n");
    billText.append("Address: ").append(AddressField.getText()).append("\n");
    billText.append("Payment Method: ").append(PayCombo.getSelectedItem()).append("\n\n");

    billText.append("Purchased Items:\n");
    billText.append("--------------------------------------------\n");
    billText.append(String.format("%-20s %-10s %-5s %-10s\n", "Card Name", "Price", "Qty", "Total"));

    DefaultTableModel model = (DefaultTableModel) CardTable.getModel();
    for (int i = 0; i < model.getRowCount(); i++) {
        String name = model.getValueAt(i, 1).toString();
        String price = model.getValueAt(i, 2).toString();
        String qty = model.getValueAt(i, 3).toString();
        String total = model.getValueAt(i, 4).toString();
        billText.append(String.format("%-20s %-10s %-5s %-10s\n", name, price, qty, total));
    }

    billText.append("--------------------------------------------\n");
    billText.append("Subtotal: ").append(SubTotal.getText()).append("\n");
    billText.append("Shipping: ₹50.00\n");
    billText.append("Tax (10%): ").append(Tax.getText()).append("\n");
    billText.append("Total Amount: ").append(TotalAmount.getText()).append("\n");

    billArea.setText(billText.toString());

    JScrollPane scrollPane = new JScrollPane(billArea);
    billFrame.add(scrollPane);
    
    billFrame.setVisible(true);
    
    int choice = JOptionPane.showConfirmDialog(null, "Do you want to send the invoice to the customer's email?", "Send Invoice", JOptionPane.YES_NO_OPTION);
   if (choice == JOptionPane.YES_OPTION) {
    String email = getEmailFromDatabase(userId);
    if (email != null && !email.trim().isEmpty()) {
        sendInvoiceEmail(email, billText.toString()); 
    } else {
        JOptionPane.showMessageDialog(null, "Email not found for the user.");
    }
}

}

private String getEmailFromDatabase(int UserId) {
    String email = null;
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Shin@maya");
        String query = "SELECT emailid FROM users WHERE userid = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, UserId);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            email = rs.getString("emailid");  
        }
        rs.close();
        pst.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to fetch email: " + e.getMessage());
    }
    return email;
}

private void sendInvoiceEmail(String recipientEmail, String invoiceContent) {
    String fromEmail = "geetharanioffsetprinters10@gmail.com";  
    String password = "fyap xvip iawk knlr";   
    Properties props = new Properties();

    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(fromEmail, password);
        }
    });

    try {
        String filePath = "Invoice.pdf"; 
        Document document = new Document();
        PdfWriter.getInstance(document, new java.io.FileOutputStream(filePath));
        document.open();
        document.add(new Paragraph(invoiceContent));
        document.close();

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Your Geetha Cards Invoice (PDF Attached)");
        Multipart multipart = new MimeMultipart();
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText("Dear Customer,\n\nPlease find your invoice attached.\n\nThank you for shopping with Geetha Cards!");
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(new java.io.File(filePath));
        multipart.addBodyPart(textBodyPart);
        multipart.addBodyPart(attachmentBodyPart);
        message.setContent(multipart);
        Transport.send(message);

        JOptionPane.showMessageDialog(null, "Invoice PDF sent successfully to customer!");
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to send invoice PDF: " + e.getMessage());
    }
}

    private void clearCart() {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM cart WHERE userid = ?");
            ps.setInt(1, userId);
            ps.executeUpdate();
            loadCartData();
            calculateTotals();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PurchasePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchasePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchasePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchasePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new PurchasePage().setVisible(true);
            }
        });
    }
    
public class conn {
    private static Connection con;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false&allowPublicKeyRetrieval=true", "root", "Shin@maya");
            System.out.println("Connected successfully!");
        } catch (Exception e) {
            System.out.println("Connection failed: " + e);
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public static CallableStatement prepareCall(String query) throws SQLException {
        return con.prepareCall(query);
    }

    public static PreparedStatement prepareStatement(String sql) throws SQLException {
        return con.prepareStatement(sql);     
    }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddressField;
    private javax.swing.JButton BackButton;
    private javax.swing.JTable CardTable;
    private javax.swing.JComboBox<String> CountryCombo;
    private javax.swing.JComboBox<String> PayCombo;
    private javax.swing.JButton PurchaseButton;
    private javax.swing.JLabel Shipping;
    private javax.swing.JComboBox<String> StateCombo;
    private javax.swing.JLabel SubTotal;
    private javax.swing.JLabel Tax;
    private javax.swing.JLabel TotalAmount;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
