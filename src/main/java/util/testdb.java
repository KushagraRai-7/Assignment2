package util;

import model.Department;
import service.DeptService;
import service.DeptServiceImple;

public class testdb {
    public static void main(String[] args) {
        DeptService service = new DeptServiceImple();

        try {
            // Insert test
            Department newDept = new Department();
            newDept.setDeptName("Research");
            newDept.setLocation("Delhi");
            newDept.setDeptID(5);
            service.addDepartment(newDept);

            // Delete test
            service.deleteDepartmentById(1); // Try with a valid ID

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
