package collectionsemployee.collectionsEmployee.employeeService;

import collectionsemployee.collectionsEmployee.employee.Employee;
import collectionsemployee.collectionsEmployee.employeeControllert.runtimeExceptionEmployee.EmployeeAlreadyAddedException;
import collectionsemployee.collectionsEmployee.employeeControllert.runtimeExceptionEmployee.EmployeeNotFoundException;
import collectionsemployee.collectionsEmployee.employeeControllert.runtimeExceptionEmployee.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Integer maxEmployee = 10;

    private final List<Employee> employeesList;

    public EmployeeServiceImpl() {
        this.employeesList = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeesList.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException("Коллекция переполнена");
        }
        if (employeesList.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже имеется в коллекции");
        }
        employeesList.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeesList.contains(employee)) {
            employeesList.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException("Удаляемый сотрудник не найден");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeesList.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    @Override
    public Collection<Employee> printEmployees() {
        return Collections.unmodifiableList(employeesList);
    }


}
