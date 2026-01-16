package ui;

import javax.swing.*;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {

        setTitle("Employee Management System");
        setSize(300, 200);
        setLayout(null);
        setLocationRelativeTo(null);

        JButton btnAdd = new JButton("Add Employee");
        btnAdd.setBounds(70, 40, 160, 30);
        add(btnAdd);

        JButton btnView = new JButton("View Employees");
        btnView.setBounds(70, 90, 160, 30);
        add(btnView);

        btnAdd.addActionListener(e -> new AddEmployeeFrame());
        btnView.addActionListener(e -> new ViewEmployeeFrame());

        setVisible(true);
    }
}
