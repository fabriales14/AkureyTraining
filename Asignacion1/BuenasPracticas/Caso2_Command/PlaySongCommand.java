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
public class PlaySongCommand implements Command{
    
    private Song song;

    public void setSong(Song song) {
        this.song = song;
    }

    @Override
    public void execute() {
        System.out.println("Process ID: " + (int) (Thread.currentThread().getId()-10) + " executing Song command");
        if (song!=null){
            song.playSong();
        }
        try{
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
 
}
