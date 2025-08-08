package client;

import model.Department;
import service.DeptService;
import service.DeptServiceImple;
import exception.DepartmentNotFound;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DepartmentApp {
    public static void main(String[] args) {
        DeptService service = new DeptServiceImple();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Department Management Menu =====");
            System.out.println("1. Add Department");
            System.out.println("2. Get Department by ID");
            System.out.println("3. Get All Departments");
            System.out.println("4. Update Department");
            System.out.println("5. Delete Department");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Dept ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Dept Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Location: ");
                        String loc = sc.nextLine();
                        service.addDepartment(new Department(id, name, loc));
                        System.out.println("Department added successfully.");
                        break;

                    case 2:
                        System.out.print("Enter Dept ID to fetch: ");
                        int searchId = sc.nextInt();
                        Department dept = service.getDepartmentById(searchId);
                        System.out.println(dept);
                        break;

                    case 3:
                        List<Department> depts = service.getAllDepartments();
                        for (Department d : depts) {
                            System.out.println(d);
                        }
                        break;

                    case 4:
                        System.out.print("Enter Dept ID to update: ");
                        int updId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new Dept Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new Location: ");
                        String newLoc = sc.nextLine();
                        service.updateDepartment(new Department(updId, newName, newLoc));
                        System.out.println("Department updated successfully.");
                        break;

                    case 5:
                        System.out.print("Enter Dept ID to delete: ");
                        int delId = sc.nextInt();
                        service.deleteDepartmentById(delId);
                        System.out.println("Department deleted successfully.");
                        break;

                    case 6:
                        System.out.println("Exiting... Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException | DepartmentNotFound e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 6);

        sc.close();
    }
}