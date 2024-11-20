package collectionsemployee.collectionsEmployee.employeeService;

import collectionsemployee.collectionsEmployee.employee.Employee;

import java.util.List;

public interface EmployeeService {

    void addEmployee(Employee employee);

    void removeEmployee(Employee employee);

    void findEmployee(Employee employee);

    List<Employee> getAllEmployees();

    String printEmployees(List<Employee> employees);
}
