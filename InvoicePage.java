package Website;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class InvoicePage extends JFrame {

    private int userId;
    private String address, country, state, paymentMethod;
    private JTable cardTable;
    private JLabel subtotalLabel, taxLabel, shippingLabel, totalLabel;

    public InvoicePage(int userId, JTable cartData, String address, String country,
                       String state, String paymentMethod, String subtotal,
                       String tax, String shipping, String total) {
        this.userId = userId;
        this.address = address;
        this.country = country;
        this.state = state;
        this.paymentMethod = paymentMethod;

        setTitle("Invoice");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        cardTable = cartData;

        // Total panel
        JPanel totalPanel = new JPanel(new GridLayout(4, 2));
        subtotalLabel = new JLabel("Subtotal: " + subtotal);
        taxLabel = new JLabel("Tax: " + tax);
        shippingLabel = new JLabel("Shipping: " + shipping);
        totalLabel = new JLabel("Total: " + total);

        // Styling labels
        StyleLabel(subtotalLabel);
        StyleLabel(taxLabel);
        StyleLabel(shippingLabel);
        StyleLabel(totalLabel);

        totalPanel.add(subtotalLabel);
        totalPanel.add(taxLabel);
        totalPanel.add(shippingLabel);
        totalPanel.add(totalLabel);

        // Download button
        JButton downloadButton = new JButton("Download Invoice PDF");
        downloadButton.addActionListener(this::downloadInvoice);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(totalPanel, BorderLayout.CENTER);
        bottomPanel.add(downloadButton, BorderLayout.SOUTH);

        add(new JScrollPane(cardTable), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    private void StyleLabel(JLabel label) {
        //label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
private void showBillOnScreen() {
    JFrame billFrame = new JFrame("Invoice");
    billFrame.setSize(600, 600);
    billFrame.setLocationRelativeTo(null);

    JTextArea billArea = new JTextArea();
    billArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
    billArea.setEditable(false);

    StringBuilder billText = new StringBuilder();
    billText.append("========= Geetha Cards - Invoice =========\n");
    billText.append("Date: ").append(new Date()).append("\n\n");
    billText.append("Delivery Details:\n");
    billText.append("Country: ").append(country.getSelectedItem()).append("\n");
    billText.append("State: ").append(state.getSelectedItem()).append("\n");
    billText.append("Address: ").append(address.getText()).append("\n");
    billText.append("Payment Method: ").append(paymentMethod.getSelectedItem()).append("\n\n");

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
}



    private void downloadInvoice(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose path to save invoice");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath() + ".pdf";

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(path));
                document.open();

                Font boldFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
                document.add(new Paragraph("Geetha Cards - Invoice", boldFont));
                document.add(new Paragraph("Date: " + new Date()));
                document.add(new Paragraph("User ID: " + userId));
                document.add(new Paragraph("Country: " + country));
                document.add(new Paragraph("State: " + state));
                document.add(new Paragraph("Address: " + address));
                document.add(new Paragraph("Payment Method: " + paymentMethod));
                document.add(Chunk.NEWLINE);

                PdfPTable table = new PdfPTable(4);
                table.addCell("Card Name");
                table.addCell("Price");
                table.addCell("Quantity");
                table.addCell("Total");

                for (int i = 0; i < cardTable.getRowCount(); i++) {
                    table.addCell(cardTable.getValueAt(i, 1).toString()); // name
                    table.addCell(cardTable.getValueAt(i, 2).toString()); // price
                    table.addCell(cardTable.getValueAt(i, 3).toString()); // qty
                    table.addCell(cardTable.getValueAt(i, 4).toString()); // total
                }

                document.add(table);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("Subtotal: " + subtotalLabel.getText().substring(9))); // Adjusting text
                document.add(new Paragraph("Shipping: " + shippingLabel.getText().substring(9))); // Adjusting text
                document.add(new Paragraph("Tax: " + taxLabel.getText().substring(5))); // Adjusting text
                document.add(new Paragraph("Total: " + totalLabel.getText().substring(7))); // Adjusting text
                document.close();

                JOptionPane.showMessageDialog(this, "Invoice saved successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to generate invoice: " + e.getMessage());
            }
        }
    }
}