/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Caso2_Command;

/**
 *
 * @author Usuario
 */
public class SendEmailCommand implements Command{
    
    private Email email;

    public void setEmail(Email email) {
        this.email = email;
    }

    @Override
    public void execute() {
        System.out.println("Process ID: " + (int) (Thread.currentThread().getId()-10) + " executing Email command");
        if (email!=null){
            email.sendEmail();
        }
        try{
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
