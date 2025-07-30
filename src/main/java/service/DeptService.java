package service;

import model.Department;
import java.util.List;
import java.sql.*;
import exception.DepartmentNotFound;


public interface DeptService {
	void addDepartment(Department dept) throws SQLException;
    Department getDepartmentById(int id) throws SQLException, DepartmentNotFound;
    List<Department> getAllDepartments() throws SQLException;
    void updateDepartment(Department dept) throws SQLException, DepartmentNotFound;
    void deleteDepartmentById(int id) throws SQLException, DepartmentNotFound;
}
