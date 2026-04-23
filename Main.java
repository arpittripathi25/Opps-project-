import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame {
    private StudentService studentService;
    private JTextField nameField;
    private JTextField courseField;
    private JTextField searchField;
    private JTextArea outputArea;
    private JLabel photoLabel;
    private String currentPhotoPath;
    private JTextField rollField;
    private JTextField dobField;
    private JTextField phoneField;
    
    public Main() {
        studentService = new StudentService();
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Student ID Card Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Top Panel - Title
        // JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // JLabel titleLabel = new JLabel("Student ID Card Management System");
        // titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        // titleLabel.setForeground(Color.BLACK);
        // titlePanel.add(titleLabel);
        // add(titlePanel, BorderLayout.NORTH);
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // logo load
        ImageIcon icon = new ImageIcon("logo.png");
        Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(img));

        // title
        JLabel titleLabel = new JLabel("Student ID Card Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLACK);

        // add logo + title
        titlePanel.add(logoLabel);
        titlePanel.add(titleLabel);

        // add panel to top
        add(titlePanel, BorderLayout.NORTH);
        
        // Center Panel - Input Fields
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        centerPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Name Field
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        nameField = new JTextField(20);
        centerPanel.add(nameField, gbc);
        
        // Course Field
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        centerPanel.add(new JLabel("Course:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        courseField = new JTextField(20);
        centerPanel.add(courseField, gbc);
        
        // Search Field
        // gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        // centerPanel.add(new JLabel("Search (ID/Name):"), gbc);
        // gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        // searchField = new JTextField(20);
        // centerPanel.add(searchField, gbc);
        // Roll No Field
        gbc.gridx = 0; gbc.gridy = 3;
        centerPanel.add(new JLabel("Roll No:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        rollField = new JTextField(20);
        centerPanel.add(rollField, gbc);

// DOB Field
        gbc.gridx = 0; gbc.gridy = 4;
        centerPanel.add(new JLabel("DOB:"), gbc);
        gbc.gridx = 1;
        dobField = new JTextField(20);
        centerPanel.add(dobField, gbc);

// Phone Field
        gbc.gridx = 0; gbc.gridy = 5;
        centerPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(20);
        centerPanel.add(phoneField, gbc);
        
        // Photo Display
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.CENTER;
        photoLabel = new JLabel("No Photo Selected");
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        photoLabel.setPreferredSize(new Dimension(150, 150));
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(photoLabel, gbc);
        
        // add(centerPanel, BorderLayout.CENTER);
        // Search Field (BOTTOM)
         gbc.gridx = 0; gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        centerPanel.add(new JLabel("Search (ID/Name/Roll):"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchField = new JTextField(20);
        centerPanel.add(searchField, gbc);

        add(centerPanel, BorderLayout.CENTER);
        
        // Right Panel - Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        buttonPanel.setBorder(new EmptyBorder(20, 10, 20, 20));
        buttonPanel.setBackground(Color.WHITE);
        
        String[] buttonNames = {
            "Add Student", "View All Students", //"Search by ID",        
            "Search",
            "Update Student", "Delete Student", "Generate ID Card", "Export Data",
            "Upload Photo", "Clear Fields"
        };
        
        for (String buttonName : buttonNames) {
            JButton button = new JButton(buttonName);
            button.setFont(new Font("Arial", Font.PLAIN, 12));
            button.setBackground(new Color(240, 240, 240));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }
        
        add(buttonPanel, BorderLayout.EAST);
        
        // Bottom Panel - Output Area
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 20, 20, 20));
        
        outputArea = new JTextArea(8, 50);
        outputArea.setEditable(false);
        outputArea.setBackground(Color.WHITE);
        outputArea.setForeground(Color.BLACK);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 12));
        outputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        getContentPane().setBackground(Color.WHITE);
    }
    
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            switch (command) {
                case "Add Student":
                    addStudent();
                    break;
                case "View All Students":
                    viewAllStudents();
                    break;
                // case "Search by ID":
                //     searchById();
                //     break;

                // case "Search by Name":
                //     searchStudentsByName();
                //     break;
                case "Search":
                  searchStudentsByName();
                  break;
                case "Update Student":
                    updateStudent();
                    break;
                case "Delete Student":
                    deleteStudent();
                    break;
                case "Generate ID Card":
                    generateIdCard();
                    break;
                case "Export Data":
                    exportData();
                    break;
                case "Upload Photo":
                    uploadPhoto();
                    break;
                case "Clear Fields":
                    clearFields();
                    break;
            }
        }
    }
    
    private void addStudent() {
        String name = nameField.getText().trim();
        String course = courseField.getText().trim();
        String roll = rollField.getText().trim();
        String dob = dobField.getText().trim();
        String phone = phoneField.getText().trim();
        
        if (name.isEmpty() || course.isEmpty()) {
            outputArea.setText("Error: Name and Course fields cannot be empty.");
            return;
        }
        
        // Student student = new Student(name, course, currentPhotoPath);
        Student student = new Student(name, course, roll, dob, phone, currentPhotoPath);
        studentService.addStudent(student);
        outputArea.setText("Student added successfully!\n" + student.toString());
        clearFields();
    }
    
    private void viewAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            outputArea.setText("No students found.");
            return;
        }
        
        StringBuilder sb = new StringBuilder("All Students:\n");
        sb.append("Total Students: ").append(students.size()).append("\n\n");
        for (int i = 0; i < students.size(); i++) {
            sb.append(i + 1).append(". ").append(students.get(i).toString()).append("\n");
        }
        outputArea.setText(sb.toString());
    }
    
    private void searchById() {
        String id = searchField.getText().trim();
        if (id.isEmpty()) {
            outputArea.setText("Error: Please enter an ID to search.");
            return;
        }
        
        Student student = studentService.searchById(id);
        if (student != null) {
            outputArea.setText("Student Found:\n" + student.toString());
            nameField.setText(student.getName());
            courseField.setText(student.getCourse());
            currentPhotoPath = student.getPhotoPath();
            if (currentPhotoPath != null && !currentPhotoPath.isEmpty()) {
                displayPhoto(currentPhotoPath);
            }
        } else {
            outputArea.setText("Student not found with ID: " + id);
        }
    }
    
    private void searchStudentsByName() {
        // System.out.println("BUTTON CLICKED");
        // String name = searchField.getText().trim();
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            outputArea.setText("Error: Please enter a name to search.");
            return;
        }
        
        // List<Student> students = studentService.searchStudentsByName(name);
        List<Student> students = studentService.searchStudent(query);
        if (students.isEmpty()) {
            outputArea.setText("No students found with name containing: " + query);
            return;
        }
        
        StringBuilder sb = new StringBuilder("Students Found:\n");
        for (int i = 0; i < students.size(); i++) {
            sb.append(i + 1).append(". ").append(students.get(i).toString()).append("\n");
        }
        outputArea.setText(sb.toString());
    }
    
    private void updateStudent() {
        String id = searchField.getText().trim();
        String name = nameField.getText().trim();
        String course = courseField.getText().trim();
        
        if (id.isEmpty()) {
            outputArea.setText("Error: Please enter student ID to update.");
            return;
        }
        
        if (name.isEmpty() || course.isEmpty()) {
            outputArea.setText("Error: Name and Course fields cannot be empty.");
            return;
        }
        
        if (studentService.updateStudent(id, name, course, currentPhotoPath)) {
            outputArea.setText("Student updated successfully!\nID: " + id + "\nName: " + name + "\nCourse: " + course);
        } else {
            outputArea.setText("Student not found with ID: " + id);
        }
    }
    
    private void deleteStudent() {
        String id = searchField.getText().trim();
        if (id.isEmpty()) {
            outputArea.setText("Error: Please enter student ID to delete.");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete student with ID: " + id + "?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            if (studentService.deleteStudent(id)) {
                outputArea.setText("Student deleted successfully with ID: " + id);
                clearFields();
            } else {
                outputArea.setText("Student not found with ID: " + id);
            }
        }
    }
    
    private void generateIdCard() {
        String id = searchField.getText().trim();
        if (id.isEmpty()) {
            outputArea.setText("Error: Please enter student ID to generate ID card.");
            return;
        }
        
        Student student = studentService.searchById(id);
        if (student != null) {
            new IDCardDialog(this, student).setVisible(true);
        } else {
            outputArea.setText("Student not found with ID: " + id);
        }
    }
    
    private void exportData() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            outputArea.setText("No data to export.");
            return;
        }
        
        StringBuilder sb = new StringBuilder("Student Data Export\n");
        sb.append("===================\n");
        sb.append("Total Students: ").append(students.size()).append("\n\n");
        
        for (Student student : students) {
            sb.append("ID: ").append(student.getId()).append("\n");
            sb.append("Name: ").append(student.getName()).append("\n");
            sb.append("Course: ").append(student.getCourse()).append("\n");
            sb.append("Photo Path: ").append(student.getPhotoPath() != null ? student.getPhotoPath() : "No photo").append("\n");
            sb.append("-------------------\n");
        }
        
        outputArea.setText(sb.toString());
        outputArea.append("\nData exported successfully to output area!");
    }
    
    private void uploadPhoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(
            "Image Files", "jpg", "jpeg", "png", "gif"));
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            currentPhotoPath = selectedFile.getAbsolutePath();
            displayPhoto(currentPhotoPath);
            outputArea.setText("Photo uploaded: " + selectedFile.getName());
        }
    }
    
    private void displayPhoto(String photoPath) {
        try {
            ImageIcon icon = new ImageIcon(photoPath);
            Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            photoLabel.setIcon(new ImageIcon(scaledImage));
            photoLabel.setText("");
        } catch (Exception e) {
            photoLabel.setText("Error loading photo");
            photoLabel.setIcon(null);
        }
    }
    
    private void clearFields() {
        nameField.setText("");
        courseField.setText("");
        searchField.setText("");
        rollField.setText("");
        dobField.setText("");
        phoneField.setText("");
        photoLabel.setText("No Photo Selected");
        photoLabel.setIcon(null);
        currentPhotoPath = null;
        outputArea.setText("Fields cleared.");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}
