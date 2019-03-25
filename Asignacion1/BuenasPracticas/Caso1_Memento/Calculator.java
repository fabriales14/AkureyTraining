/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Caso1_Memento;

/**
 *
 * @author Usuario
 */
public class Calculator implements Operations{
    
    private double x;
    private double y;
    private double z;
    private String lastOperation;
    private Savepoint previousCalculation;
    private SavepointsCareTaker savepointsCareTaker;

    public Calculator(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.savepointsCareTaker = new SavepointsCareTaker();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        System.out.println("Assign x = " + this.x);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        System.out.println("Assign y = " + this.y);
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
        System.out.println("Assign z = " + this.z);
    }

    public String getLastOperation() {
        return lastOperation;
    }

    public void setLastOperation(String lastOperation) {
        this.lastOperation = lastOperation;
    }

    public Savepoint getPreviousCalculation() {
        return previousCalculation;
    }

    public void setPreviousCalculation(Savepoint previousCalculation) {
        this.previousCalculation = previousCalculation;
    }
    
    public void createSavepoint(){
        this.savepointsCareTaker.addSavepoint(new Savepoint(lastOperation, x, y, z, previousCalculation));
    }
    
    public void setSavepoint(int i){
        this.x = this.savepointsCareTaker.getSavepoint(i).getX();
        this.y = this.savepointsCareTaker.getSavepoint(i).getY();
        this.z = this.savepointsCareTaker.getSavepoint(i).getZ();
        this.lastOperation = this.savepointsCareTaker.getSavepoint(i).getOperation();
        System.out.println("Calculator restores to savepoint " + i + " " + this.toString());
    }
    
    public void undo(){
        this.x = this.previousCalculation.getX();
        this.y = this.previousCalculation.getY();
        this.z = this.previousCalculation.getZ();
        this.lastOperation = this.previousCalculation.getOperation();
        this.previousCalculation = this.previousCalculation.getLastOpSavepoint();
        System.out.println("Undo of calculador " + this.toString());
    }
    
    public void reset(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.lastOperation = null;
        this.previousCalculation = this.previousCalculation.getLastOpSavepoint();
        System.out.println("Reset of calculator " + this.toString());
    }

    @Override
    public String toString() {
        return "Calculator{" + "x=" + x + ", y=" + y + ", z=" + z + ", lastOperation=" + lastOperation + '}';
    }

    @Override
    public void sum(String var1, double var2) {
        this.previousCalculation = new Savepoint(lastOperation, x, y, z, previousCalculation);
        this.lastOperation = "+";
        if ("x".equals(var1)){
            this.x += var2;
            System.out.println("Sum x + " + var2 + " = " + this.x);
            System.out.println("Actual values " + this.toString());
        }
        else if ("y".equals(var1)){
            this.y += var2;
            System.out.println("Sum y + " + var2 + " = " + this.y);
            System.out.println("Actual values " + this.toString());
        }
        else if ("z".equals(var1)){
            this.z += var2;
            System.out.println("Sum z + " + var2 + " = " + this.z);
            System.out.println("Actual values " + this.toString());
        } else
            System.out.println("Invalid variable");       
    }

    @Override
    public void rest(String var1, double var2) {
        this.previousCalculation = new Savepoint(lastOperation, x, y, z, previousCalculation);
        this.lastOperation = "-";
        if ("x".equals(var1)){
            this.x -= var2;
            System.out.println("Rest x - " + var2 + " = " + this.x);
            System.out.println("Actual values " + this.toString());
        }
        else if ("y".equals(var1)){
            this.y -= var2;
            System.out.println("Rest y - " + var2 + " = " + this.y);
            System.out.println("Actual values " + this.toString());
        }
        else if ("z".equals(var1)){
            this.z -= var2;
            System.out.println("Rest z - " + var2 + " = " + this.z);
            System.out.println("Actual values " + this.toString());
        } else
            System.out.println("Invalid variable");
    }

    @Override
    public void mult(String var1, double var2) {
        this.previousCalculation = new Savepoint(lastOperation, x, y, z, previousCalculation);
        this.lastOperation = "*";
        if ("x".equals(var1)){
            this.x *= var2;
            System.out.println("Mult x * " + var2 + " = " + this.x);
            System.out.println("Actual values " + this.toString());
        }
        else if ("y".equals(var1)){
            this.y *= var2;
            System.out.println("Mult y * " + var2 + " = " + this.y);
            System.out.println("Actual values " + this.toString());
        }
        else if ("z".equals(var1)){
            this.z *= var2;
            System.out.println("Mult z * " + var2 + " = " + this.z);
            System.out.println("Actual values " + this.toString());
        } else
            System.out.println("Invalid variable");
    }

    @Override
    public void div(String var1, double var2) {
        this.previousCalculation = new Savepoint(lastOperation, x, y, z, previousCalculation);
        this.lastOperation = "/";
        if ("x".equals(var1)){
            this.x /= var2;
            System.out.println("Div x / " + var2 + " = " + this.x);
            System.out.println("Actual values " + this.toString());
        }
        else if ("y".equals(var1)){
            this.y /= var2;
            System.out.println("Div y / " + var2 + " = " + this.y);
            System.out.println("Actual values " + this.toString());
        }
        else if ("z".equals(var1)){
            this.z /= var2;
            System.out.println("Div z / " + var2 + " = " + this.z);
            System.out.println("Actual values " + this.toString());
        } else
            System.out.println("Invalid variable");
    }

    @Override
    public void pot(String var1, double var2) {
        this.previousCalculation = new Savepoint(lastOperation, x, y, z, previousCalculation);
        this.lastOperation = "**";
        if ("x".equals(var1)){
            this.x = Math.pow(x, var2);
            System.out.println("Pow x ** " + var2 + " = " + this.x);
            System.out.println("Actual values " + this.toString());
        }
        else if ("y".equals(var1)){
            this.y = Math.pow(y, var2);
            System.out.println("Pow y ** " + var2 + " = " + this.y);
            System.out.println("Actual values " + this.toString());
        }
        else if ("z".equals(var1)){
            this.z = Math.pow(z, var2);
            System.out.println("Pow z ** " + var2 + " = " + this.z);
            System.out.println("Actual values " + this.toString());
        } else
            System.out.println("Invalid variable");
    }

    @Override
    public void root(String var1) {
        this.previousCalculation = new Savepoint(lastOperation, x, y, z, previousCalculation);
        this.lastOperation = "sqrt";
        if ("x".equals(var1)){
            this.x = Math.sqrt(x);
            System.out.println("Sqrt x  = " + this.x);
            System.out.println("Actual values " + this.toString());
        }
        else  if ("y".equals(var1)){
            this.y = Math.sqrt(y);
            System.out.println("Sqrt y = " + this.y);
            System.out.println("Actual values " + this.toString());
        }
        else if ("z".equals(var1)){
            this.z = Math.sqrt(z);
            System.out.println("Sqrt z = " + this.z);
            System.out.println("Actual values " + this.toString());
        } else
            System.out.println("Invalid variable");
    }
    
}
