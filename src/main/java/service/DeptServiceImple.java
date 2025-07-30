package service;

import java.sql.SQLException;
import java.util.List;

import exception.DepartmentNotFound;
import model.Department;
import dao.DeptDAO;
import dao.DeptDAOImpli;

public class DeptServiceImple implements DeptService {
	private DeptDAO dao = new DeptDAOImpli();

	@Override
	public void addDepartment(Department dept) throws SQLException {
		// TODO Auto-generated method stub
		dao.addDepartment(dept);
	}

	@Override
	public Department getDepartmentById(int id) throws SQLException, DepartmentNotFound {
		// TODO Auto-generated method stub
		return dao.getDepartmentById(id);
	}

	@Override
	public List<Department> getAllDepartments() throws SQLException {
		// TODO Auto-generated method stub
		return dao.getAllDepartments();
	}

	@Override
	public void updateDepartment(Department dept) throws SQLException, DepartmentNotFound {
		// TODO Auto-generated method stub
		dao.updateDepartment(dept);
	}

	@Override
	public void deleteDepartmentById(int id) throws SQLException, DepartmentNotFound {
		// TODO Auto-generated method stub
		dao.deleteDepartmentById(id);
	}

}
