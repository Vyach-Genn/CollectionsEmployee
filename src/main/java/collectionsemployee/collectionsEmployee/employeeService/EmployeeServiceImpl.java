package collectionsemployee.collectionsEmployee.employeeService;

import collectionsemployee.collectionsEmployee.employee.Employee;
import collectionsemployee.collectionsEmployee.employeeControllert.runtimeExceptionEmployee.EmployeeAlreadyAddedException;
import collectionsemployee.collectionsEmployee.employeeControllert.runtimeExceptionEmployee.EmployeeNotFoundException;
import collectionsemployee.collectionsEmployee.employeeControllert.runtimeExceptionEmployee.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Integer maxEmployee = 10;

    private final List<Employee> employees = new ArrayList<>(asList(
            new Employee("John", "Doe"),
            new Employee("Jane", "Doe")

    ));

    @Override
    public void addEmployee(Employee employee) {
        if (employees.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException("Коллекция переполнена");
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже имеется в коллекции");
        }
        employees.add(employee);
    }

    @Override
    public void removeEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Удаляемый сотрудник не найден");
        }
        employees.remove(employee);
    }

    @Override
    public void findEmployee(Employee employee) {
        if (employees.contains(employee)) {
            System.out.println("Сотрудник найден: " + employee);
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден.");
        }

    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    @Override
    public String printEmployees(List<Employee> employees) {
        StringBuilder result = new StringBuilder();
        for (Employee employee : employees) {
            result.append(employee.toString()).append("\n");
        }
        return result.toString();

    }
}
