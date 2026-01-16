package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import dboperations.EmployeeDAO;
import model.Employee;

public class ViewEmployeeFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewEmployeeFrame() {

        setTitle("View Employees");
        setSize(700, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(
        	    new String[]{"ID", "Name", "Email", "Department", "Salary"}, 0
        	) {
        	    @Override
        	    public boolean isCellEditable(int row, int column) {
        	        return column != 0; // ID column not editable
        	    }
        	};


        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 640, 250);
        add(sp);

        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");

        btnUpdate.setBounds(180, 300, 120, 30);
        btnDelete.setBounds(340, 300, 120, 30);

        add(btnUpdate);
        add(btnDelete);

        loadEmployees();

        // DELETE employee
        btnDelete.addActionListener(e -> {
            try {
                int row = table.getSelectedRow();
                int empId = (int) model.getValueAt(row, 0);

                new EmployeeDAO().deleteEmployee(empId);
                refreshTable();
                JOptionPane.showMessageDialog(this, "Employee Deleted");


                JOptionPane.showMessageDialog(this, "Employee Deleted");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Select a row first");
            }
        });

        // UPDATE employee
        btnUpdate.addActionListener(e -> {
            try {
                int row = table.getSelectedRow();

                Employee emp = new Employee();
                emp.setEmpId((int) model.getValueAt(row, 0));
                emp.setName(model.getValueAt(row, 1).toString());
                emp.setEmail(model.getValueAt(row, 2).toString());
                emp.setDepartment(model.getValueAt(row, 3).toString());
                emp.setSalary(Double.parseDouble(
                    model.getValueAt(row, 4).toString()
                ));

                new EmployeeDAO().updateEmployee(emp);
                refreshTable(); // ✅ forces DB → UI sync
                JOptionPane.showMessageDialog(this, "Employee Updated Successfully");


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Edit a row and click Update");
            }
        });

        setVisible(true);
    }

    private void loadEmployees() {
        try {
            ResultSet rs = new EmployeeDAO().getEmployees();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("emp_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("department"),
                    rs.getDouble("salary")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    private void refreshTable() {
        model.setRowCount(0); // clears all rows from JTable
        loadEmployees();      // reloads fresh data from DB
    }

}

