
package GameOfLife;

import java.awt.Color;
import javax.swing.JFrame;
        
public class GameOfLife {
    
    // Die Koordinaten und Größe des Fensters
    private static final int xCoordWindow = 30; 
    private static final int yCoordWindow = 30;
    private static final int widthWindow = 800;
    private static final int heightWindow = 600;
    
    public static void main(String[] args){
        // Erstellt das Fenster
        JFrame window = new GameWindow(800, 600);
        // Setzt den Titel des Spiels
        window.setTitle("Game Of Life");
        // Setzt die Größe und die Position des Fensters
        window.setBounds(xCoordWindow, yCoordWindow, widthWindow, heightWindow);
        // Blockiert die Änderbarkeit der Größe des Fensters
        window.setResizable(false);
        // Das Fenster wird Sichtbar gesetzt
        window.setVisible(true);
    }  
}
