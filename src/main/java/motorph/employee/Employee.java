/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package motorph.employee;

/**
 *
 * @author page1
 */
import java.util.Scanner;

public class Employee {

    public static void main(String[] args) {
        Scanner SalaryInput = new Scanner(System.in);
        System.out.println("How much is basic salary?");
    
        double BasicSalary = SalaryInput.nextDouble();
        
    //declare new variables, values and formula
    
    double Tax = 0.15;
    double Deductions = (BasicSalary * Tax);
    double NetSalary = (BasicSalary - Deductions);
        
        System.out.println("Your Tax payable is : " + Tax);
        System.out.println("Your total deductions is : " + Deductions);
        System.out.println("Basic salary is : " + NetSalary);
    }
}
