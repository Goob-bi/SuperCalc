/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supercalc;
import java.util.Scanner;
import util.*;
import parser.*;
import math.numericalmethods.Integration;
import math.numericalmethods.NumericalIntegral;
import static math.numericalmethods.NumericalIntegral.FUNCTIONAL_INTEGRATION;
import util.help.Topic;
/**
 *
 * @author andreas lees
 */
public class SuperCalc {
    public static final Scanner SCNR = new Scanner(System.in);

    public static String getNext() {
        String nextLine = SCNR.next();
        return nextLine;
        
    }

    public static String getNextLine() {
        String nextLine = SCNR.nextLine();
        return nextLine;
        
    }
    public static int getNextInt() {
        int nextLine = SCNR.nextInt();
        return nextLine;
        
    }
    public static double getNextDouble() {
        double nextLine = SCNR.nextDouble();
        return nextLine;
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        printMenu();
    }
    public static void printMenuOptions() {
      
      System.out.println("\nMENU\n" +
        "a - Algebra expression\n" +
        "i - Solve integrals\n" +
        "rs - Solve Reimann Sums\n" +
        "d - Calculate Derivative\n" +
        "q - Quit\n" + "\nChoose an option:");
        
    }
    public static void printMenu() {
      MathExpression expr = new MathExpression();
      Boolean quitMenu = false;
      printMenuOptions();
      String usrInput = SuperCalc.getNextLine();
      int i;
      
      while (!quitMenu) {
          //TODO add check to make sure expression is correct format
          /*examples
          quad(@(x)x^2+x-12)    quadratic, must be in terms of zero
          intg(@(x)2*x,1,3)     integration using definite integral
          diff(@(x)x^3,1,3)     derivative within given bounds
          diff(@(x)2*x)         derivative of function
          
          */
        switch (usrInput){
            case "a":
                System.out.println("Input algebra equation");
                System.out.println("examples:\n\t|(1+3)/2\n\t|x=3+4*(8-7) ");
                usrInput = SuperCalc.getNextLine();
                
                for (i=0; i<usrInput.length(); ++i) {   //checks if a variable is used in equation
                    if (Character.isLetter(usrInput.charAt(i))) {   //modifies to use proper syntax
                        usrInput = usrInput.charAt(i) + ";" + usrInput;
                        break;
                    }
                }
                //System.out.println(usrInput); //testing purposes
                MathExpression algebra = new MathExpression(usrInput);
                System.out.println("result: " + algebra.solve());
                usrInput = SuperCalc.getNextLine();
                break;
            case "i":
                //example "f(x)=2*x; intg(f,1,3)"
                System.out.println("Input equation you want to integrate, with x as variable followed by lower and upper bound");
                System.out.println("ex: 2*x,1,3");
                //usrInput = "f(x)=" + SuperCalc.getNext() + "; intg(f," + SuperCalc.getNextDouble() + "," + SuperCalc.getNextDouble() + ")";
                usrInput = "intg(@(x)" + SuperCalc.getNextLine() + ")";
                expr.setExpression(usrInput);
                System.out.println("result: " + expr.solve());
                usrInput = SuperCalc.getNextLine();
                break;
            case "rs":
                String func;
                int iterations;
                double lowerBound;
                double upperBound;
                System.out.println("Input equation you want to use, with x as variable followed by lower bound, upper bound, and number of iterations");
                System.out.println("ex: 2*x 1 3 4");
                func = SuperCalc.getNext();
                System.out.println(func);
                lowerBound = SuperCalc.getNextDouble();
                System.out.println(lowerBound);
                upperBound = SuperCalc.getNextDouble();
                System.out.println(upperBound);
                iterations = SuperCalc.getNextInt();
                System.out.println(iterations);
                ReimannSumm integral = new ReimannSumm(func, lowerBound, upperBound, iterations);
                System.out.println("Left endpoint: "+integral.leftSum());
                System.out.println("Right endpoint: "+integral.rightSum());
                System.out.println("Trapezoid rule: "+integral.trapezoidSum());
                System.out.println("Simpson rule: "+integral.simpsonSum());
                
                usrInput = SuperCalc.getNextLine();
                break;
            case "d":
                System.out.println("Input equation you want to derive, with x as variable");
                System.out.println("You can input just the equation or provide a lower/upper bound");
                System.out.println("ex: x^3");
                System.out.println("ex: x^3,1,3");
                usrInput = "diff(@(x)" + SuperCalc.getNextLine() + ")";
                expr.setExpression(usrInput);
                System.out.println("result: " + expr.solve());
                usrInput = SuperCalc.getNextLine();
                break;
            case "q":
                quitMenu = true;
                break;
            default:
                System.out.println("Choose an valid option:");
                usrInput = SuperCalc.getNextLine();
                break;
        }
          
      }
        
    }
    
}
