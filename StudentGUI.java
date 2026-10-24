import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentGUI extends JFrame {

    // TEXT FIELDS
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField courseField;
    private JTextField gwaField;

    // COMBO BOX
    private JComboBox<String> typeComboBox;


    // BUTTONS
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton viewButton;


    // TABLE
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    // STUDENT MANAGER
    private StudentManager manager;


    // CONSTRUCTOR
    public StudentGUI() {

        manager = new StudentManager();

        // Window
        setTitle("Student Management System");

        setSize(900, 650);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        // MAIN PANEL
        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

       
        // TITLE


        JLabel titleLabel =
                new JLabel(
                        "STUDENT MANAGEMENT SYSTEM",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

  
        // INPUT PANEL
        JPanel inputPanel =
                new JPanel(
                        new GridLayout(
                                6,
                                2,
                                10,
                                8
                        )
                );

        JLabel idLabel =
                new JLabel("Student ID:");

        JLabel nameLabel =
                new JLabel("Name:");

        JLabel ageLabel =
                new JLabel("Age:");

        JLabel courseLabel =
                new JLabel("Course:");

        JLabel gwaLabel =
                new JLabel("GWA:");

        JLabel typeLabel =
                new JLabel("Type:");

        // Text fields

        idField = new JTextField();

        nameField = new JTextField();

        ageField = new JTextField();

        courseField = new JTextField();

        gwaField = new JTextField();

        // Combo box

        typeComboBox =
                new JComboBox<>(
                        new String[]{
                                "Regular",
                                "Irregular"
                        }
                );

        // Add input components

        inputPanel.add(idLabel);
        inputPanel.add(idField);

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        inputPanel.add(ageLabel);
        inputPanel.add(ageField);

        inputPanel.add(courseLabel);
        inputPanel.add(courseField);

        inputPanel.add(gwaLabel);
        inputPanel.add(gwaField);

        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);

        // BUTTON PANEL
        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                10
                        )
                );

        addButton =
                new JButton("ADD");

        updateButton =
                new JButton("UPDATE");

        deleteButton =
                new JButton("DELETE");

        searchButton =
                new JButton("SEARCH");

        viewButton =
                new JButton("VIEW ALL");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(viewButton);

       
        // TOP PANEL
        JPanel topPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        topPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        topPanel.add(
                inputPanel,
                BorderLayout.CENTER
        );

        topPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        // TABLE
        String[] columns = {

                "Student ID",
                "Name",
                "Age",
                "Course",
                "GWA",
                "Type"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                );

        studentTable =
                new JTable(tableModel);

        scrollPane =
                new JScrollPane(studentTable);

        // Hide table when program starts

        scrollPane.setVisible(false);

       
        // MAIN PANEL
        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(mainPanel);

      
        // BUTTON ACTIONS
        addButton.addActionListener(
                e -> addStudent()
        );

        updateButton.addActionListener(
                e -> updateStudent()
        );

        deleteButton.addActionListener(
                e -> deleteStudent()
        );

        searchButton.addActionListener(
                e -> searchStudent()
        );

        viewButton.addActionListener(
                e -> toggleTable()
        );

        setVisible(true);
    }


    // ADD STUDENT
    private void addStudent() {

        try {

            String id =
                    idField.getText().trim();

            String name =
                    nameField.getText().trim();

            String ageText =
                    ageField.getText().trim();

            String course =
                    courseField.getText().trim();

            String gwaText =
                    gwaField.getText().trim();

            // Empty field validation

            if (id.isEmpty()
                    || name.isEmpty()
                    || ageText.isEmpty()
                    || course.isEmpty()
                    || gwaText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // Convert numbers

            int age =
                    Integer.parseInt(ageText);

            double gwa =
                    Double.parseDouble(gwaText);

            // Age validation

            if (age <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Age must be greater than 0.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // GWA validation

            if (gwa < 1.0 || gwa > 5.0) {

                JOptionPane.showMessageDialog(
                        this,
                        "GWA must be between 1.0 and 5.0.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // Get type

            String type =
                    (String)
                            typeComboBox
                                    .getSelectedItem();

            Student student;

            // Polymorphism

            if (type.equals("Regular")) {

                student =
                        new RegularStudent(
                                name,
                                id,
                                age,
                                course,
                                gwa
                        );

            } else {

                student =
                        new IrregularStudent(
                                name,
                                id,
                                age,
                                course,
                                gwa
                        );
            }

            // Add student

            boolean added =
                    manager.addStudent(student);

            if (added) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student added successfully!"
                );

                clearFields();

                // Update table data
                viewStudents();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Student ID already exists.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Age must be a whole number and GWA must be a number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // UPDATE STUDENT
    private void updateStudent() {

        try {

            String id =
                    idField.getText().trim();

            String name =
                    nameField.getText().trim();

            String ageText =
                    ageField.getText().trim();

            String course =
                    courseField.getText().trim();

            String gwaText =
                    gwaField.getText().trim();

            // Empty field validation

            if (id.isEmpty()
                    || name.isEmpty()
                    || ageText.isEmpty()
                    || course.isEmpty()
                    || gwaText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            int age =
                    Integer.parseInt(ageText);

            double gwa =
                    Double.parseDouble(gwaText);

            // Age validation

            if (age <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Age must be greater than 0.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // GWA validation

            if (gwa < 1.0 || gwa > 5.0) {

                JOptionPane.showMessageDialog(
                        this,
                        "GWA must be between 1.0 and 5.0.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            boolean updated =
                    manager.updateStudent(
                            id,
                            name,
                            age,
                            course,
                            gwa
                    );

            if (updated) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student updated successfully!"
                );

                clearFields();

                viewStudents();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Student not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers for Age and GWA.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // DELETE STUDENT
    private void deleteStudent() {

        String id =
                idField.getText().trim();

        if (id.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter the Student ID first.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        boolean deleted =
                manager.deleteStudent(id);

        if (deleted) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student deleted successfully!"
            );

            clearFields();

            viewStudents();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Student not found.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // SEARCH STUDENT
    private void searchStudent() {

        String id =
                idField.getText().trim();

        if (id.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter the Student ID first.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Student student =
                manager.searchStudent(id);

        if (student != null) {

            // Clear current table

            tableModel.setRowCount(0);

            String type;

            if (student instanceof RegularStudent) {

                type = "Regular";

            } else {

                type = "Irregular";
            }

            // Add only searched student

            Object[] row = {

                    student.getStudentId(),
                    student.getName(),
                    student.getAge(),
                    student.getCourse(),
                    student.getGwa(),
                    type
            };

            tableModel.addRow(row);

            // Show table

            scrollPane.setVisible(true);

            viewButton.setText(
                    "HIDE TABLE"
            );

            revalidate();
            repaint();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Student not found.",
                    "Search Result",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }


    // VIEW STUDENTS
    private void viewStudents() {

        tableModel.setRowCount(0);

        Student[] students =
                manager.getStudents();

        for (int i = 0;
             i < manager.getCount();
             i++) {

            Student student =
                    students[i];

            String type;

            if (student instanceof RegularStudent) {

                type = "Regular";

            } else {

                type = "Irregular";
            }

            Object[] row = {

                    student.getStudentId(),
                    student.getName(),
                    student.getAge(),
                    student.getCourse(),
                    student.getGwa(),
                    type
            };

            tableModel.addRow(row);
        }
    }

    // VIEW / HIDE TABLE
    private void toggleTable() {

        if (scrollPane.isVisible()) {

            scrollPane.setVisible(false);

            viewButton.setText(
                    "VIEW ALL"
            );

        } else {

            viewStudents();

            scrollPane.setVisible(true);

            viewButton.setText(
                    "HIDE TABLE"
            );
        }

        revalidate();
        repaint();
    }


    // CLEAR FIELDS
    private void clearFields() {

        idField.setText("");

        nameField.setText("");

        ageField.setText("");

        courseField.setText("");

        gwaField.setText("");

        typeComboBox.setSelectedIndex(0);
    }


    // MAIN
    public static void main(String[] args) {

        new StudentGUI();
    }
}