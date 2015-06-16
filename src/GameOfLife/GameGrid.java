/**
 *
 * @author Lukas Huppertz
 */
package GameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class GameGrid extends JPanel {
    
    // Liste der zu zeichnenden Zellen
    private final List<Point> fillCells;
    // Seitenlänge der Zellen
    private int CellSize;
    // Anzahl der Zellen in der Breite
    private int maxCellsWidth;
    // Anzahl der Zellen in der Höhe
    private int maxCellsHeight;

    // Konstruktor
    public GameGrid(){
        this.fillCells = new ArrayList<>(25);
        
        this.CellSize = 20;
    }
    
    // Setzt die Seitenlänge der Zelle
    public void setCellSize(int size){
        this.CellSize = size;
        this.calcMaxCellsWidth(this.getWidth());
        this.calcMaxCellsHeight(this.getHeight());
        System.out.println("GameGrid.java");
        System.out.println("Grid: x:"+this.getX()+", y:"+this.getY()+", Höhe:"+this.getHeight()+", Breite:"+this.getWidth());
        System.out.println("CellsWidth: "+this.maxCellsWidth+", CellsHeight: "+this.maxCellsHeight);
    }
    
    // Gibt die Größe der Zellen aus
    public int getCellSize(){
        return this.CellSize;
    }
    
    // Berechnet die anzahl der Zellen in der Breite
    public void calcMaxCellsWidth(int width){
        this.maxCellsWidth = (width-(width%this.CellSize))/this.CellSize;
        if(width%this.CellSize <= this.CellSize){
            this.maxCellsWidth -= 1;
            System.out.println("Abzug -1 Breite");
        }
    }
    
    // Gibt die Anzahl der Zellen in der Breite aus
    public int getMaxCellsWidth(){
        return this.maxCellsWidth;
    }
    
    // Berechnet die Anzahl der Zellen in der Höhe
    public void calcMaxCellsHeight(int height){
        this.maxCellsHeight = (height-(height%this.CellSize))/this.CellSize;
        if(height%this.CellSize <= this.CellSize){
            this.maxCellsHeight -= 1;
            System.out.println("Abzug -1 Höhe");
        }
    }
    
    // Gibt die Anzahl der Zellen in der Höhe aus
    public int getMaxCellsHeight(){
        return this.maxCellsHeight;
    }
    
    // Liefert die X Position der Zelle anhand der X-Koordinate
    public int getXCellbyXCoordinate(int XCoord){
        if(XCoord > this.getX()){
            XCoord -= this.getX();
            
            if(XCoord > (this.maxCellsWidth*this.CellSize)){
                return this.maxCellsWidth-1;
            }
            else{
                if(XCoord > this.CellSize){
                    return (int) (XCoord-(XCoord%this.CellSize))/this.CellSize;
                }
                else{
                    return 0;
                }
            }
        }
        else{
            return 0;
        }
    }
    
    // Liefert die Y Position der Zelle anhand der Y-Koordinate
    public int getYCellbyYCoordinate(int YCoord){
        if(YCoord > this.getY()){
            YCoord -= this.getY();
            
            if(YCoord > (this.maxCellsHeight*this.CellSize)){
                return this.maxCellsHeight-1;
            }
            else{
                if(YCoord > this.CellSize){
                    return (int) (YCoord-(YCoord%this.CellSize))/this.CellSize;
                }
                else{
                    return 0;
                }
            }
        }
        else{
            return 0;
        }
    }
    
    // Überschreibt die setBounds-Funktion um im selben Zug 
    // die Anzahl der Zellen in Höhe und Breite zu berechnen
    @Override
    public void setBounds(int x, int y, int width, int height){
        this.calcMaxCellsWidth(width-1);
        this.calcMaxCellsHeight(height-1);
        System.out.println("CellsWidth: "+this.maxCellsWidth+", CellsHeight: "+this.maxCellsHeight);
        super.setBounds(x, y, width-1, height-1);
    }
    
    // Überschreibt die paintComponent-Funktion damit beim Erstellen
    // des Grid-Objekts und beim aufruf von repaint() das Grid gezeichnet wird
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        // Füllt das Grid mit den im Array festgesetzten Zellen
        for (Point fillCell : this.fillCells) {
            int cellX = this.getX() + (fillCell.x * this.CellSize);
            int cellY = this.getY() + (fillCell.y * this.CellSize);
            g.setColor(Color.RED);
            g.fillRect(cellX, cellY, this.CellSize, this.CellSize);
        }
        
        g.setColor(Color.GREEN);
     
        // Zeichnet die vertikalen Linien
        for (int i = 0; i <= this.maxCellsWidth; i++) {
            g.drawLine(this.getX()+(i*this.CellSize), this.getY(), this.getX()+(i*this.CellSize), this.getY()+(this.CellSize*this.maxCellsHeight));
            //System.out.println("Vertikal: x1:"+(this.getX()+(i*this.CellSize))+", y1:"+this.getY()+", x2:"+(this.getX()+(i*this.CellSize))+", y2:"+(this.getY()+(this.CellSize*this.maxCellsHeight)));
        }
        
        // Zeichnet die horizontalen Linien
        for (int i = 0; i <= this.maxCellsHeight; i++) {
            g.drawLine(this.getX(), this.getY()+(i*this.CellSize), this.getX()+(this.CellSize*this.maxCellsWidth), this.getY()+(i*this.CellSize));
            //System.out.println("Horizont: x1:"+(this.getX())+", y1:"+(this.getY()+(i*this.CellSize))+", x2:"+(this.getX()+(this.CellSize*this.maxCellsWidth))+", y2:"+(this.getY()+(i*this.CellSize)));
        }
    }
    
    // Fügt die zu Zeichnenden Zellen in das Array
    public void fillCell(int x, int y) {
        fillCells.add(new Point(x, y));
        // zeichnet das Grid neu
        repaint();
    }
    
    // Ändert die Größe des Grid
    public void alterGridSize(int size){
        this.setCellSize(size);
        this.fillCells.clear();
        // zeichnet das Grid neu
        repaint();
    }
    
}
