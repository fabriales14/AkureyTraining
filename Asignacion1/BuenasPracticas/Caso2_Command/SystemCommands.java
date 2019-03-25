/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Caso2_Command;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Usuario
 */
public class SystemCommands {
    
    private final BlockingQueue<Command> commandQueue;
    private final Thread[] commandThreads;
    private volatile boolean shutdown;
 
    public SystemCommands(int n){
        commandQueue = new LinkedBlockingQueue<>();
        commandThreads = new Thread[n];
        for (int i = 0; i < n; i++) {
            commandThreads[i] = new Worker("Pool Thread " + i);
            commandThreads[i].start();
        }
    }

    public void addCommand(Command c){
        try  {
            commandQueue.put(c);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void shutdownPool(){
        while (!commandQueue.isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        shutdown = true;
        for (Thread workerThread : commandThreads) {
            workerThread.interrupt();
        }
    }

    private class Worker extends Thread
    {
        public Worker(String name){
            super(name);
        }

        @Override
        public void run(){
            while (!shutdown) {
                try {
                    Command c = commandQueue.take();
                    c.execute();
                } catch (InterruptedException e) {
                }
            }
        }
    }

}
