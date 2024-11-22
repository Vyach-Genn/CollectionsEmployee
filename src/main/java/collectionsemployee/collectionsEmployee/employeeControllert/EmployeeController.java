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
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName) {
        return employeeService.addEmployee(firstName, lastName);

    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping
    public Collection<Employee> printAllEmployees() {
        return employeeService.printEmployees();
    }
}
