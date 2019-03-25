/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Caso2_Command;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class SendSMSCommand implements Command{

    private SMS sms;

    public void setSms(SMS sms) {
        this.sms = sms;
    }
    
    @Override
    public void execute() {
        System.out.println("Process ID: " + (int) (Thread.currentThread().getId()-10) + " executing SMS command");
        if (sms!=null){
            sms.sendSMS();
        }
        try{
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
}
