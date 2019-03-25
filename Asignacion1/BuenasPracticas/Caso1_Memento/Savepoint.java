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
public class Savepoint {
    
    private int ind;
    private String operation;
    private double x;
    private double y;
    private double z;
    private Savepoint lastOpSavepoint;

    public Savepoint(String operation, double x, double y, double z, Savepoint lastOpSavepoint) {
        this.ind = 0;
        this.operation = operation;
        this.x = x;
        this.y = y;
        this.z = z;
        this.lastOpSavepoint = lastOpSavepoint;
    }
    
    public int getInd() {
        return ind;
    }

    public void setInd(int ind) {
        this.ind = ind;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Savepoint getLastOpSavepoint() {
        return lastOpSavepoint;
    }

    public void setLastOpSavepoint(Savepoint lastOpSavepoint) {
        this.lastOpSavepoint = lastOpSavepoint;
    }
  
}
