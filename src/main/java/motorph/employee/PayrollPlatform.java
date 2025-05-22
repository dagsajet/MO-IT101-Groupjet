

package motorphpayroll.payrollplatform;

//MotorPH Payroll System - Group 8

import java.util.*;
import java.time.LocalDate;

// Base class for all users
class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }
}

// Login session class
class LoginSession {
    private boolean active;
    private LocalDate loginDate;

    public void startSession() {
        this.active = true;
        this.loginDate = LocalDate.now();
    }

    public void endSession() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getLoginDate() {
        return loginDate;
    }
}

// Deductions class
class Deductions {
    private double sss;
    private double philHealth;
    private double pagIbig;
    private double tax;
    private double other;

    public Deductions(double grossSalary) {
        this.sss = grossSalary * 0.045;
        this.philHealth = grossSalary * 0.04;
        this.pagIbig = grossSalary * 0.02;
        this.tax = grossSalary * 0.10;
        this.other = grossSalary * 0.01; // Example: other deduction 1%
    }

    public double getTotalDeductions() {
        return sss + philHealth + pagIbig + tax + other;
    }

    public void printBreakdown() {
        System.out.println("Deduction Breakdown:");
        System.out.printf("SSS: PHP %.2f\n", sss);
        System.out.printf("PhilHealth: PHP %.2f\n", philHealth);
        System.out.printf("Pag-IBIG: PHP %.2f\n", pagIbig);
        System.out.printf("Tax: PHP %.2f\n", tax);
        System.out.printf("Other: PHP %.2f\n", other);
    }
}

// Attendance class
class Attendance {
    private List<LocalDate> datesPresent;

    public Attendance() {
        this.datesPresent = new ArrayList<>();
    }

    public void addAttendance(LocalDate date) {
        datesPresent.add(date);
    }

    public int getDaysWorked() {
        return datesPresent.size();
    }
}

// Employee class
class Employee extends User {
    private int employeeId;
    private String name;
    private String position;
    private double basicSalary;
    private Attendance attendance;
    private LoginSession session;

    public Employee(String username, String password, int employeeId, String name, String position, double basicSalary) {
        super(username, password);
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.basicSalary = basicSalary;
        this.attendance = new Attendance();
        this.session = new LoginSession();
    }

    public double calculateMonthlySalary() {
        int daysWorked = attendance.getDaysWorked();
        double grossSalary = (basicSalary / 22) * daysWorked; // Assuming 22 working days
        Deductions deductions = new Deductions(grossSalary);
        double netSalary = grossSalary - deductions.getTotalDeductions();

        System.out.printf("Gross Salary: PHP %.2f\n", grossSalary);
        deductions.printBreakdown();

        return netSalary;
    }

    public void recordAttendance(LocalDate date) {
        attendance.addAttendance(date);
    }

    public void startSession() {
        session.startSession();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }
}

// Payroll system controller
public class PayrollPlatform {
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        // Adding sample employee
        Employee emp1 = new Employee("jet01", "Pws123456@1", 1, "Jett Dagsa", "Clerk", 22000);
        Employee emp2 = new Employee("sarah02", "pass456", 2, "Sarah Trinidad", "HR Officer", 30000);
        Employee emp3 = new Employee("ela03", "pass789", 3, "Ela Abigail Acal", "IT Staff", 28000);
        Employee emp4 = new Employee("fran04", "pass321", 4, "Franchella Martini Micu-Paculan", "Accountant", 35000);
        Employee emp5 = new Employee("iris05", "pass654", 5, "Irisha Bea Montaño", "Manager", 50000);

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        

        // Simulate login
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Password: ");
        String inputPassword = scanner.nextLine();

        for (Employee e : employees) {
            if (e.login(inputUsername, inputPassword)) {
                System.out.println("Login successful! Welcome, " + e.getName());
                e.startSession();
                e.recordAttendance(LocalDate.now());
                System.out.println("Recorded attendance.");
                System.out.printf("Net monthly salary so far: PHP %.2f\n", e.calculateMonthlySalary());
                return;
            }
        }
        System.out.println("Invalid login.");
    }
}
