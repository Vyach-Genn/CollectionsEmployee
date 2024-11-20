package collectionsemployee.collectionsEmployee.employeeControllert;


import collectionsemployee.collectionsEmployee.employee.Employee;
import collectionsemployee.collectionsEmployee.employeeControllert.runtimeExceptionEmployee.EmployeeAlreadyAddedException;
import collectionsemployee.collectionsEmployee.employeeControllert.runtimeExceptionEmployee.EmployeeNotFoundException;
import collectionsemployee.collectionsEmployee.employeeControllert.runtimeExceptionEmployee.EmployeeStorageIsFullException;
import collectionsemployee.collectionsEmployee.employeeService.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            employeeService.addEmployee(employee);
            return "Сотрудник добавлен: " + employee;
        } catch (EmployeeStorageIsFullException | EmployeeAlreadyAddedException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            employeeService.removeEmployee(employee);
            return "Сотрудник удален: " + employee;
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            employeeService.findEmployee(employee);
            return "Сотрудник найден: " + employee;
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/print")
    public String printAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            return "Список сотрудников пуст.";
        }
        return employeeService.printEmployees(employees);
    }

}
