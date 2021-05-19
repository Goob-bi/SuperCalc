/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supercalc;

import util.*;
import parser.*;


/**
 *
 * @author andreas lees
 */
public class ReimannSumm {
    public double LOWBOUND;
    public double UPBOUND;
    public int RECTCOUNT;
    public String FUNCTION;
    
    public ReimannSumm() {
        LOWBOUND = 0;
        UPBOUND = 0;
        RECTCOUNT = 0;
        FUNCTION = "x";
        FunctionManager.add("H=@(x)"+FUNCTION);
    }
    public ReimannSumm(String function, double lowerBound, double upperBound, int rectangleCount) {
        LOWBOUND = lowerBound;
        UPBOUND = upperBound;
        RECTCOUNT = rectangleCount;
        FUNCTION = function;
        FunctionManager.add("H=@(x)"+FUNCTION);
    }    
    public void setFunct(String func) {
      //MathExpression expr = new MathExpression(func);
        FUNCTION = func;
        FunctionManager.add("H=@(x)"+FUNCTION);
    }    
    public double myFunct(double n) {
        MathExpression expr = new MathExpression("H("+n+")");
        double ans;
        ans = Double.parseDouble(expr.solve());
        return ans;
    }    
    
    public double leftSum() {
        //left endpoint
         double rectWidth = (UPBOUND - LOWBOUND) / RECTCOUNT;
         double myVal = 0.0;
         int counter;
         double z = LOWBOUND;
         for (counter = 0; counter < RECTCOUNT; ++counter) {
             myVal = myVal + (rectWidth * myFunct(z));
             z = z + rectWidth;
         }
         return myVal;
    }    
    
    public double rightSum() {
        //right endpoint
         double rectWidth = (UPBOUND - LOWBOUND) / RECTCOUNT;
         double myVal = 0.0;
         int counter;
         double z = LOWBOUND + rectWidth;
         for (counter = 1; counter <= RECTCOUNT; ++counter) {
             myVal = myVal + (rectWidth * myFunct(z));
             z = z + rectWidth;
         }
         return myVal;
    }
    //trapezoid summ is (right+left endpoint)/2
    public double trapezoidSum() {
        //trapezoid sum
        double myVal = (rightSum() + leftSum())/2;
         return myVal;
    }
    
    public double simpsonSum() {
        //simpson sum
        
         double rectWidth = (UPBOUND - LOWBOUND) / RECTCOUNT;
         double myVal = 0.0;
         int counter;
         double z = LOWBOUND;
         for (counter = 0; counter <= RECTCOUNT; ++counter) {
             if (counter == 0 || counter == RECTCOUNT ) {
                myVal = myVal + ((rectWidth / 3) * myFunct(z));
                //System.out.println("Simpson end func" + counter + ". " + myVal);
                 
             } else if (counter % 2 == 0) { //even
                myVal = myVal + ((rectWidth / 3) * 2 * myFunct(z));
                //System.out.println("Simpson even func" + counter + ". " + myVal);
             } else { //odd
                myVal = myVal + ((rectWidth / 3) * 4 * myFunct(z));
                //System.out.println("Simpson odd func" + counter + ". " + myVal);
             }
             z = z + rectWidth;
         }
        
         return myVal;
    }
    
    public static void main(String[] args) {
         
         ReimannSumm test = new ReimannSumm("2x^3", 1, 3, 4);
         //test.setFunct("2x^3");
         System.out.println(test.leftSum());
         System.out.println(test.rightSum());
         System.out.println(test.trapezoidSum());
         System.out.println(test.simpsonSum());
         
    }
    
}
