
package com.example.employee.service;

import com.example.employee.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        assertEquals(2, employees.size());
        assertEquals("Rahul", employees.get(0).getName());
        assertEquals("Priya", employees.get(1).getName());
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = employeeService.getEmployeeById(1);

        assertNotNull(employee);
        assertEquals("Rahul", employee.getName());
        assertEquals("CSE", employee.getDepartment());
    }

    @Test
    void testGetEmployeeByInvalidId() {
        Employee employee = employeeService.getEmployeeById(99);

        assertNull(employee);
    }

    @Test
    void testAddEmployee() {
        Employee newEmployee =
                new Employee(3, "Arjun", "IT", 60000);

        Employee addedEmployee =
                employeeService.addEmployee(newEmployee);

        assertNotNull(addedEmployee);
        assertEquals(3, employeeService.getAllEmployees().size());
        assertEquals("Arjun",
                employeeService.getEmployeeById(3).getName());
    }

    @Test
    void testUpdateEmployee() {
        Employee updatedEmployee =
                new Employee(1, "Rahul Kumar", "IT", 55000);

        Employee result =
                employeeService.updateEmployee(1, updatedEmployee);

        assertNotNull(result);
        assertEquals("Rahul Kumar", result.getName());
        assertEquals("IT", result.getDepartment());
        assertEquals(55000, result.getSalary());
    }

    @Test
    void testUpdateInvalidEmployee() {
        Employee updatedEmployee =
                new Employee(99, "Unknown", "IT", 30000);

        Employee result =
                employeeService.updateEmployee(99, updatedEmployee);

        assertNull(result);
    }

    @Test
    void testDeleteEmployee() {
        boolean deleted = employeeService.deleteEmployee(1);

        assertTrue(deleted);
        assertNull(employeeService.getEmployeeById(1));
        assertEquals(1, employeeService.getAllEmployees().size());
    }

    @Test
    void testDeleteInvalidEmployee() {
        boolean deleted = employeeService.deleteEmployee(99);

        assertFalse(deleted);
        assertEquals(2, employeeService.getAllEmployees().size());
    }
}