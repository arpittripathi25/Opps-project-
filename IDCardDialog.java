import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class IDCardDialog extends JDialog {
    private Student student;
    
    public IDCardDialog(JFrame parent, Student student) {
        super(parent, "Student ID Card", true);
        this.student = student;
        initializeUI();
    }
    
    private void initializeUI() {
        setSize(400, 300);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());
        
        // Main card panel
        JPanel cardPanel = new JPanel(new BorderLayout(10, 10));
        cardPanel.setBorder(new LineBorder(Color.BLACK, 2));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(350, 200));
        
        // Header panel
        // JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // headerPanel.setBackground(new Color(200, 200, 255));
        // JLabel headerLabel = new JLabel("STUDENT ID CARD");
        // headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        // headerLabel.setForeground(Color.BLACK);
        // headerPanel.add(headerLabel);
        // cardPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(200, 200, 255));

        // 🔹 Logo add
        // ImageIcon logo = new ImageIcon("logo.png");
        ImageIcon logo = new ImageIcon(new File("logo.png").getAbsolutePath());
        Image img = logo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        logo = new ImageIcon(img);
        JLabel logoLabel = new JLabel(logo);

        // 🔹 Title
        JLabel headerLabel = new JLabel("STUDENT ID CARD");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel.setForeground(Color.BLACK);

        // 🔹 Add both
        headerPanel.add(logoLabel);
        headerPanel.add(headerLabel);

        cardPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Content panel
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Left panel - Photo
        JPanel photoPanel = new JPanel(new BorderLayout());
        photoPanel.setBackground(Color.WHITE);
        photoPanel.setBorder(new LineBorder(Color.GRAY, 1));
        
        JLabel photoLabel = new JLabel("No Photo");
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        photoLabel.setPreferredSize(new Dimension(100, 120));
        
        if (student.getPhotoPath() != null && !student.getPhotoPath().isEmpty()) {
            try {
                ImageIcon icon = new ImageIcon(student.getPhotoPath());
                Image scaledImage = icon.getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH);
                photoLabel.setIcon(new ImageIcon(scaledImage));
                photoLabel.setText("");
            } catch (Exception e) {
                photoLabel.setText("Photo Error");
            }
        }
        
        photoPanel.add(photoLabel, BorderLayout.CENTER);
        contentPanel.add(photoPanel, BorderLayout.WEST);
        
        // Right panel - Student info
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoPanel.setBackground(Color.WHITE);
        
        infoPanel.add(createInfoLabel("ID: " + student.getId()));
        infoPanel.add(createInfoLabel("Name: " + student.getName()));
        infoPanel.add(createInfoLabel("Course: " + student.getCourse()));
        
        // QR Code placeholder
        JLabel qrLabel = new JLabel();
        qrLabel.setHorizontalAlignment(SwingConstants.CENTER);
        qrLabel.setPreferredSize(new Dimension(80, 80));
        
        try {
            BufferedImage qrImage = generateQRCode(student.getId() + "|" + student.getName());
            if (qrImage != null) {
                qrLabel.setIcon(new ImageIcon(qrImage));
            } else {
                qrLabel.setText("QR Code");
                qrLabel.setFont(new Font("Arial", Font.PLAIN, 10));
            }
        } catch (Exception e) {
            qrLabel.setText("QR Error");
            qrLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        }
        
        infoPanel.add(qrLabel);
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        
        cardPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton printButton = new JButton("Print ID Card");
        JButton closeButton = new JButton("Close");
        
        printButton.addActionListener(e -> printIdCard());
        closeButton.addActionListener(e -> dispose());
        
        buttonPanel.add(printButton);
        buttonPanel.add(closeButton);
        
        add(cardPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        getContentPane().setBackground(Color.LIGHT_GRAY);
    }
    
    private JLabel createInfoLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        label.setForeground(Color.BLACK);
        return label;
    }
    
    private BufferedImage generateQRCode(String text) {
        try {
            // Simple QR code generation using basic drawing
            // This is a simplified version - in production, use ZXing library
            int size = 80;
            BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, size, size);
            g2d.setColor(Color.BLACK);
            
            // Create a simple pattern representing QR code
            // This is just a placeholder - real QR codes need proper encoding
            int cellSize = 4;
            for (int i = 0; i < size; i += cellSize) {
                for (int j = 0; j < size; j += cellSize) {
                    if ((i + j) % (cellSize * 2) == 0) {
                        g2d.fillRect(i, j, cellSize, cellSize);
                    }
                }
            }
            
            // Add some text in the center for identification
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 8));
            String shortId = student.getId().substring(3, 8);
            FontMetrics fm = g2d.getFontMetrics();
            int x = (size - fm.stringWidth(shortId)) / 2;
            int y = size / 2;
            g2d.drawString(shortId, x, y);
            
            g2d.dispose();
            return image;
        } catch (Exception e) {
            return null;
        }
    }
    
    private void printIdCard() {
        JOptionPane.showMessageDialog(this, 
            "ID Card ready for printing!\n\nStudent: " + student.getName() + 
            "\nID: " + student.getId() + 
            "\n\n(Use Ctrl+P to print this dialog)", 
            "Print ID Card", JOptionPane.INFORMATION_MESSAGE);
    }
}
