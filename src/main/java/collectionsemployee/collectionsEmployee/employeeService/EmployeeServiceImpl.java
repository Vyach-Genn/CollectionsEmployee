package collectionsemployee.collectionsEmployee.employeeService;

import collectionsemployee.collectionsEmployee.employee.Employee;
import collectionsemployee.collectionsEmployee.employeeControllert.runtimeExceptionEmployee.EmployeeAlreadyAddedException;
import collectionsemployee.collectionsEmployee.employeeControllert.runtimeExceptionEmployee.EmployeeNotFoundException;
import collectionsemployee.collectionsEmployee.employeeControllert.runtimeExceptionEmployee.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Integer maxEmployee = 10;

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException("Коллекция переполнена");
        }
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже имеется в коллекции");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        String  fullName = employee.getFullName();
        if (employees.containsKey(fullName)) {
            return employees.remove(fullName);

        }
        System.out.println("Сотрудник не найден.");
        throw new EmployeeNotFoundException("Удаляемый сотрудник не найден");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        String  fullName = employee.getFullName();
        if (employees.containsKey(fullName)) {
            return employees.get(fullName);
        }
        System.out.println("Сотрудник не найден.");
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    @Override
    public Collection<Employee> printEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }

}
