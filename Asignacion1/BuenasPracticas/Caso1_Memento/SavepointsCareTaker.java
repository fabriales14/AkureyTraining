/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Caso1_Memento;

import java.util.HashMap;

/**
 *
 * @author Usuario
 */
public class SavepointsCareTaker {
    
    private int counter;
    private HashMap<Integer, Savepoint> savepoints = new HashMap<>();

    public SavepointsCareTaker() {
        this.counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public HashMap<Integer, Savepoint> getSavepoints() {
        return savepoints;
    }
        
    public void addSavepoint(Savepoint s){
        s.setInd(counter++);
        this.savepoints.put(counter, s);
        System.out.println("Savepoint " + counter + " created");
    }
    
    public Savepoint getSavepoint(int id){
        return this.savepoints.get(id);
    }
}
