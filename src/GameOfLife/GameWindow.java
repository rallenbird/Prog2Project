/**
 *
 * @author Lukas Huppertz
 */
package GameOfLife;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameWindow extends JFrame implements ActionListener, ChangeListener, MouseListener {

    // Die Koordinaten und Größe des Spielfeldes
    private final int xCoordGrid, yCoordGrid;
    private final int widthGrid, heightGrid;
      
    // Die Koordinaten und Größe des Steuerungspanels
    private final int xCoordPanel, yCoordPanel;
    private final int widthPanel, heightPanel;
    
    // Die JFrame Komponenten
    private GameGrid         gg_grid;
    private JPanel           gg_panel;
    private JButton          gg_start, gg_stop, gg_reset;
    private JSlider          gg_velocity, gg_groesse;
    private GameLogic        gg_logic;   
   
    //Thread für laufendes Programm
    private GameThreadRun    ongoing = new GameThreadRun();
   
    //Sperrt den Mehrfachen start von Threads
    private boolean          locked = false;
    private int speed, newspeed;
    //Cell elements
    /**
     * Konstruktor
     * @param width die Breite des Hauptfensters
     * @param height die Höhe des Hauptfensters
     */
    public GameWindow(int width, int height){
        this.getContentPane().setLayout(null);
             
        // Die Größe und Position des Steuerpanels wird gesetzt
        this.heightPanel = 70;
        this.widthPanel = 800;
        
        this.xCoordPanel = 0;
        this.yCoordPanel = height-this.heightPanel;
        
        // Die Position und Größe des GameGrids wird gesetzt
        this.xCoordGrid = 10;
        this.yCoordGrid = 10;
        
        this.widthGrid = width-this.xCoordGrid*2;
        this.heightGrid = height-this.yCoordGrid*2-this.heightPanel;
        
        // Ausgabe der Programmdetails (Debug)
        System.out.println("GameWindow.java");
        System.out.println("Grid: x:"+this.xCoordGrid+", y:"+this.yCoordGrid+", Höhe:"+this.heightGrid+", Breite:"+this.widthGrid);
        System.out.println("Panel: x:"+this.xCoordPanel+", y:"+this.yCoordPanel+", Höhe:"+this.heightPanel+", Breite:"+this.widthPanel);
        
        this.initGameWindow();   
    }
       
    // Setzt die ganzen Komponenten des Fensters fest
    // Einfache Auslagerung aus Konstruktor
    protected void initGameWindow(){
        
        // Erstellt das GameGrid
        this.gg_grid = new GameGrid();
        this.gg_grid.setBounds(this.xCoordGrid, this.yCoordGrid, this.widthGrid, this.heightGrid);
        this.gg_grid.addMouseListener(this);
        //Add game logic
        this.gg_logic = new GameLogic(gg_grid);
        this.gg_logic.drawLogic();
        // Erstellt das Steuerungs-Panel
        this.gg_panel = new JPanel();
        this.gg_panel.setBounds(this.xCoordPanel, this.yCoordPanel, this.widthPanel, this.heightPanel);        
        // Erstellt die anderen Komponenten
        // Buttons + ActionListener
        this.gg_start = new JButton("Start");
        this.gg_start.addActionListener(this);
        
        this.gg_stop = new JButton("Stop");
        this.gg_stop.addActionListener(this);
        
        this.gg_reset = new JButton("Reset");
        this.gg_reset.addActionListener(this);
        
        // Sliders + SliderDesign + ChangeListener
        this.gg_velocity = new JSlider(JSlider.HORIZONTAL, 0, 2, 1);
        this.gg_velocity.setMajorTickSpacing(1);
        this.gg_velocity.setPaintTicks(true);
        this.gg_velocity.addChangeListener(this);
        
        this.gg_groesse = new JSlider(JSlider.HORIZONTAL, 0, 2, 1);
        this.gg_groesse.setMajorTickSpacing(1);
        this.gg_groesse.setPaintTicks(true);
        this.gg_groesse.addChangeListener(this);
        
        // Fügt die Komponenten dem JPanel hinzu
        this.gg_panel.add(new JLabel("Langsam"));
        this.gg_panel.add(gg_velocity);
        this.gg_panel.add(new JLabel("Schnell"));
        this.gg_panel.add(gg_start);
        this.gg_panel.add(gg_stop);
        this.gg_panel.add(gg_reset);
        this.gg_panel.add(new JLabel("Klein"));
        this.gg_panel.add(gg_groesse);
        this.gg_panel.add(new JLabel("Groß"));
        
        // Fügt JCanvas+JPanel dem JFrame hinzu
        this.getContentPane().add(gg_grid);
        this.getContentPane().add(gg_panel);
          
        this.gg_grid.fillCell(5, 5);
        
        this.pack();
    }
    
   
    // Fängt die Aktionen der Buttons
    @Override
    public void actionPerformed (final ActionEvent ae){
  
        //Thread für Buttons
        Thread buttonhandling = new Thread(){
        public void run(){    

        /**Start Button
         * 
         */
        if(ae.getSource() == gg_start){      
            //start new generation     
            System.out.println("Start gedrückt");
            //Thread zum beginnen
            if(locked == false){
            ongoing = new GameThreadRun();
            ongoing.start();
            locked = true;
        }
            //Go on
            synchronized(ongoing){
                ongoing.proceed();
            }
        }
        //Stop Button
        else if(ae.getSource() == gg_stop){
            System.out.println("Stop gedrückt");
            synchronized(ongoing){
                ongoing.pause();
            }
        }
        //Reset Button
        else if (ae.getSource() == gg_reset){
            System.out.println("Reset gedrückt");
            gg_grid.flushGrid(true);
            gg_logic.drawLogic();
            synchronized(ongoing){
                ongoing.pause();
            }
        }
    }};
        buttonhandling.start();
    }
      
    // Fängt die Änderung der Slider
    @Override
    public void stateChanged(ChangeEvent ce) {
        if(ce.getSource() == this.gg_velocity){
            JSlider source = (JSlider)ce.getSource();
            if (!source.getValueIsAdjusting()) {
                System.out.println("Geschwindigkeit: "+(int)source.getValue());
                speed = (int)source.getValue();
            }     
        }
        else if(ce.getSource() == this.gg_groesse){
            JSlider source = (JSlider)ce.getSource();
            if (!source.getValueIsAdjusting()) {
                System.out.println("Größe: "+(int)source.getValue());
                
                if(source.getValue() == 0){
                    this.gg_grid.alterGridSize(10);
                    this.gg_logic.drawLogic();

                }
                if(source.getValue() == 1){
                    this.gg_grid.alterGridSize(20);
                    this.gg_logic.drawLogic();

                }
                if(source.getValue() == 2){
                    this.gg_grid.alterGridSize(30);
                    this.gg_logic.drawLogic();


                }
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        // Nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Nothing
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Maus-X: "+(e.getX())+", Maus-Y: "+(e.getY()));
        int x = this.gg_grid.getXCellbyXCoordinate(e.getX());
        int y = this.gg_grid.getYCellbyYCoordinate(e.getY());
        System.out.println("Calced: x:"+x+", y:"+y+" ");
        
        // Add Cells to grid and put logic behind it
        if(this.gg_logic.getCellState(x, y)){
            this.gg_grid.removeCell(x, y);
            this.gg_logic.setCellState(x, y, false);
        }
        else{
            this.gg_grid.fillCell(x, y);
            this.gg_logic.setCellState(x, y, true);
        }
    }
    
            //Thread für laufendes Programm
    public class GameThreadRun extends Thread {
    
         boolean fPause = false;
            //continue new generation
         @Override
        public void run(){
            
            while(true){
            gg_logic.nextGeneration();
            gg_grid.flushGrid(true);
            for(int i = 0; i <= gg_grid.getMaxCellsWidth(); i++){          
                for(int j = 0; j <= gg_grid.getMaxCellsHeight(); j++){
                   if(gg_logic.getCellState(i, j)){
                       gg_grid.fillCell(i, j);
                   }
                }
            } 
            //Geschwindigkeit ändern
                switch(speed){
                    case 0: newspeed = 500;
                        break;
                    case 1: newspeed = 300;
                        break;
                    case 2: newspeed = 100;
                        break;
                }
            
                try {
                    Thread.sleep(newspeed);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            //Thread pausieren
            synchronized (this) {
            while (fPause) {
               try {
                  wait();
               } catch (Exception e) {
                  e.printStackTrace();
              }
            }
          }
        }
     }
            //Funktionen zum Thread pausieren
             public void pause() {
             fPause = true;
                }
 
             public void proceed() {
             fPause = false;
             notify();
                }
}
            
    

    
  
        
}

   