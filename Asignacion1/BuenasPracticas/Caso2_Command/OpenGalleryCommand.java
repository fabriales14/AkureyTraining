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
public class OpenGalleryCommand implements Command{
    
    private Gallery gallery;

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }

    @Override
    public void execute() {
        //int id = (int) (Thread.currentThread().getId()-10);
        System.out.println("Process ID: " + (int) (Thread.currentThread().getId()-10) + " executing Gallery command");
        if (gallery!=null){
            gallery.openGallery();
        }
        try{
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
}
