package ui;

import javax.swing.*;
import dboperations.EmployeeDAO;
import model.Employee;

public class AddEmployeeFrame extends JFrame {
  
    JTextField txtName, txtEmail, txtDept, txtSalary;

    public AddEmployeeFrame() {    

        setTitle("Add Employee");
        setSize(300, 300);
        setLayout(null);

        JLabel l1 = new JLabel("Name:");
        l1.setBounds(20, 20, 100, 20);
        add(l1);

        txtName = new JTextField();
        txtName.setBounds(120, 20, 120, 20);
        add(txtName);

        JLabel l2 = new JLabel("Email:");
        l2.setBounds(20, 60, 100, 20);
        add(l2);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 60, 120, 20);
        add(txtEmail);

        JLabel l3 = new JLabel("Dept:");
        l3.setBounds(20, 100, 100, 20);
        add(l3);

        txtDept = new JTextField();
        txtDept.setBounds(120, 100, 120, 20);
        add(txtDept);

        JLabel l4 = new JLabel("Salary:");
        l4.setBounds(20, 140, 100, 20);
        add(l4);

        txtSalary = new JTextField();
        txtSalary.setBounds(120, 140, 120, 20);
        add(txtSalary);

        JButton btn = new JButton("Add");
        btn.setBounds(80, 190, 100, 30);
        add(btn);

        btn.addActionListener(e -> {
            try {
                Employee emp = new Employee();
                emp.setName(txtName.getText());
                emp.setEmail(txtEmail.getText());
                emp.setDepartment(txtDept.getText());
                emp.setSalary(Double.parseDouble(txtSalary.getText()));

                new EmployeeDAO().addEmployee(emp);
                JOptionPane.showMessageDialog(this, "Employee Added!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }
}
