/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class GameGrid extends JPanel {
    
    private final List<Point> fillCells;
    private int widthCell;
    private int heightCell;
    
    public GameGrid(){
        this.fillCells = new ArrayList<>(25);
        
        this.widthCell = 20;
        this.heightCell = 20;
    }
    
    public void setWidthCell(int widthCell){
        this.widthCell = widthCell;
    }
    
    public int getWidthCell(){
        return this.widthCell;
    }
    
    public void setHeightCell(int heightCell) {
        this.heightCell = heightCell;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        for (Point fillCell : fillCells) {
            int cellX = this.getX() + (fillCell.x * this.widthCell);
            int cellY = this.getY() + (fillCell.y * this.heightCell);
            g.setColor(Color.RED);
            g.fillRect(cellX, cellY, this.widthCell, this.heightCell);
        }
        g.setColor(Color.GREEN);
        g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        // Zeichnet die vertikalen Linien
        for (int i = this.getX()+this.widthCell; i <= this.getWidth(); i += this.widthCell) {
            g.drawLine(i, this.getY(), i, this.getHeight());
        }
        
        // Zeichnet die horizontalen Linien
        for (int i = this.getY()+this.heightCell; i <= this.getHeight(); i += this.heightCell) {
            g.drawLine(this.getX(), i, this.getWidth(), i);
        }
    }
    
        public void fillCell(int x, int y) {
            fillCells.add(new Point(x, y));
            repaint();
        }
        
        public void alterGridSize(int width, int height){
            setWidthCell(width);
            setHeightCell(height);
            repaint();
        }
    
}
