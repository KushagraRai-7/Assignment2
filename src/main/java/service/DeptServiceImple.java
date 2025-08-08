package service;

import java.sql.*;
import java.util.*;

import exception.DepartmentNotFound;
import model.Department;
import util.DbUtil;

public class DeptServiceImple implements DeptService{

    // 1. Add department
    @Override
    public void addDepartment(Department dept) throws SQLException {
        String sql = "INSERT INTO department (deptName, location, deptID) VALUES (?, ?, ?)";
        try (Connection conn = DbUtil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dept.getDeptName());
            ps.setString(2, dept.getLocation());
            ps.setInt(3, dept.getDeptID());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Department added successfully.");
            } else {
                System.out.println("⚠️ Failed to add department.");
            }
        }
    }

    // 2. Get department by ID
    @Override
    public Department getDepartmentById(int id) throws SQLException, DepartmentNotFound {
        String sql = "SELECT * FROM department WHERE deptID = ?";
        try (Connection conn = DbUtil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Department dept = new Department();
                dept.setDeptID(rs.getInt("deptID"));              // ✅ Corrected
                dept.setDeptName(rs.getString("deptName"));        // ✅ Corrected
                dept.setLocation(rs.getString("location"));        // ✅ Corrected
                return dept;
            } else {
                throw new DepartmentNotFound(" Department with ID " + id + " not found.");
            }
        }
    }


    // 3. Get all departments
    @Override
    public List<Department> getAllDepartments() throws SQLException {
        List<Department> deptList = new ArrayList<>();
        String sql = "SELECT * FROM department";

        try (Connection conn = DbUtil.getconnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Department dept = new Department();
                dept.setDeptID(rs.getInt("id"));
                dept.setDeptName(rs.getString("name"));
                dept.setLocation(rs.getString("location"));
                deptList.add(dept);
            }
        }

        return deptList;
    }

    // 4. Update department
    @Override
    public void updateDepartment(Department dept) throws SQLException, DepartmentNotFound {
        String sql = "UPDATE department SET name = ?, location = ? WHERE deptID = ?";
        try (Connection conn = DbUtil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dept.getDeptName());
            ps.setString(2, dept.getLocation());
            ps.setInt(3, dept.getDeptID());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new DepartmentNotFound("❌ Cannot update. Department with ID " + dept.getDeptID() + " not found.");
            } else {
                System.out.println("✅ Department updated successfully.");
            }
        }
    }

    // 5. Delete department by ID
    @Override
    public void deleteDepartmentById(int id) throws SQLException, DepartmentNotFound {
		String sql = "DELETE FROM department WHERE deptID = ?";
        try (Connection conn = DbUtil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new DepartmentNotFound("❌ Cannot delete. Department with ID " + id + " not found.");
            } else {
                System.out.println("🗑️ Department deleted successfully.");
            }
        }
    }
}
