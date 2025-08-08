package dao;

import model.Department;
import util.DbUtil;
import exception.DepartmentNotFound;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeptDAOImpli implements DeptDAO {

	@Override
	public void addDepartment(Department dept) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO department (deptID, deptName, location) VALUES ( ? , ? , ? )";
		try(Connection conn = DbUtil.getconnection(); PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, dept.getDeptID());
			ps.setString(2, dept.getDeptName());
			ps.setString(3, dept.getLocation());
			ps.executeUpdate();
		}
		
	}

	@Override
	public Department getDepartmentById(int id) throws SQLException, DepartmentNotFound {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM department WHERE deptID = ?";
		try(Connection conn = DbUtil.getconnection(); PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Department(rs.getInt("deptID"), rs.getString("deptName"), rs.getString("location0"));
			} else {
				throw new DepartmentNotFound("Department with id"+ id + "not found");
			}
		}
		
	}

	@Override
	public List<Department> getAllDepartments() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM department";
		List<Department> departments = new ArrayList<>();
		try(Connection conn = DbUtil.getconnection(); PreparedStatement ps = conn.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
                departments.add(new Department(rs.getInt("deptId"), rs.getString("deptName"), rs.getString("location")));
            }
        }
        return departments;
				
	}

	@Override
	public void updateDepartment(Department dept) throws SQLException, DepartmentNotFound {
		// TODO Auto-generated method stub
		String sql = "UPDATE department SET deptName = ?, location = ? WHERE deptID = ?";
        try (Connection conn = DbUtil.getconnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dept.getDeptName());
            ps.setString(2, dept.getLocation());
            ps.setInt(3, dept.getDeptID());
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new DepartmentNotFound("Department with ID " + dept.getDeptID() + " not found.");
            }
        }
	}

	@Override
	public void deleteDepartmentById(int id) throws SQLException, DepartmentNotFound {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM department WHERE deptID = ?";
		try (Connection conn = DbUtil.getconnection(); PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			if (rows == 0) {
				throw new DepartmentNotFound("Department with ID " + id + " not found");
				}
			}
		
	}
	

}
