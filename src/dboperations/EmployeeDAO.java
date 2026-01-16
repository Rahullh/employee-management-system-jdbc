package dboperations;

import java.sql.*;
import db.DBConnection;
import model.Employee;

public class EmployeeDAO {

    public void addEmployee(Employee emp) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO employee(name,email,department,salary) VALUES(?,?,?,?)"
        );

        ps.setString(1, emp.getName());
        ps.setString(2, emp.getEmail());
        ps.setString(3, emp.getDepartment());
        ps.setDouble(4, emp.getSalary());

        ps.executeUpdate();
        con.close();
    }

    public ResultSet getEmployees() throws Exception {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        return st.executeQuery("SELECT * FROM employee");
    }

    public void deleteEmployee(int id) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
            con.prepareStatement("DELETE FROM employee WHERE emp_id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }

    
    public void updateEmployee(Employee emp) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "UPDATE employee SET name=?, email=?, department=?, salary=? WHERE emp_id=?"
        );

        ps.setString(1, emp.getName());
        ps.setString(2, emp.getEmail());
        ps.setString(3, emp.getDepartment());
        ps.setDouble(4, emp.getSalary());
        ps.setInt(5, emp.getEmpId());

        ps.executeUpdate();
        con.close();
    }
}
