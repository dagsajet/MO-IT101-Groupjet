/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package payroll.payroll;

/**
 *
 * @author page1
 */
import java.util.*;

// Role-based access control
enum Role {
    ADMIN, EMPLOYEE
}

class Employee {
    String id, name;
    double hourlyRate;
    double hoursWorked;
    double sss;
    double philHealth;
    double pagIbig;
    double tax;
    Role role;

    public Employee(String id, String name, double hourlyRate, Role role) {
        this.id = id;
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.role = role;
    }

    public void setHoursWorked(double hours) {
        this.hoursWorked = hours;
    }

    public double calculateGrossSalary() {
        return hourlyRate * hoursWorked;
    }

    public void calculateDeductions() {
        sss = 0.045 * calculateGrossSalary();
        philHealth = 0.03 * calculateGrossSalary();
        pagIbig = 100;
        tax = 0.10 * calculateGrossSalary();
        
    }

    public double calculateNetSalary() {
        calculateDeductions();
        return calculateGrossSalary() - (sss + philHealth + pagIbig + tax);
    }

    public void displayPayslip() {
        System.out.println("Payslip for: " + name);
        System.out.println("Gross Salary: " + calculateGrossSalary());
        System.out.println("Deductions: SSS=" + sss + " PhilHealth=" + philHealth + " Pag-IBIG=" + pagIbig + " Tax=" + tax);
        System.out.println("Net Salary: " + calculateNetSalary());
    }
}

class PayrollSystem {
    Map<String, Employee> employees = new HashMap<>();

    public void addEmployee(Employee emp) {
        employees.put(emp.id, emp);
    }

    public void processPayroll() {
        System.out.println("Processing Payroll...");
        for (Employee emp : employees.values()) {
            emp.displayPayslip();
            System.out.println("----------------------------");
        }
    }
}

public class Payroll {
    public static void main(String[] args) {
        PayrollSystem payroll = new PayrollSystem();
        

        Employee emp1 = new Employee("E001", "Jose Crisostomo", 500, Role.EMPLOYEE);
        Employee emp2 = new Employee("E002", "Christian Mata", 600, Role.EMPLOYEE);
        Employee emp3 = new Employee("E003", "Brad San Jose", 700, Role.EMPLOYEE);
        Employee emp4 = new Employee("E004", "Anthony Salcedo", 700, Role.EMPLOYEE);
        Employee emp5 = new Employee("E004", "Rosie Atienza", 600, Role.EMPLOYEE);

        emp1.setHoursWorked(40);
        emp2.setHoursWorked(35);
        emp3.setHoursWorked(27);
        emp4.setHoursWorked(20);
        emp5.setHoursWorked(35);

        payroll.addEmployee(emp1);
        payroll.addEmployee(emp2);
        payroll.addEmployee(emp3);
        payroll.addEmployee(emp4);
        payroll.addEmployee(emp5);

        payroll.processPayroll();
    }
}
