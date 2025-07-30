package dao;

import java.sql.SQLException;
import java.util.List;
import model.Department;
import exception.DepartmentNotFound;


public interface DeptDAO {
	
	    void addDepartment(Department dept) throws SQLException;
	    Department getDepartmentById(int id) throws SQLException, DepartmentNotFound;
	    List<Department> getAllDepartments() throws SQLException;
	    void updateDepartment(Department dept) throws SQLException, DepartmentNotFound;
	    void deleteDepartmentById(int id) throws SQLException, DepartmentNotFound;
	}
