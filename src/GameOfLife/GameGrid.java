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
    private int CellSize;
    private int maxCellsWidth;
    private int maxCellsHeight;
    
    public GameGrid(){
        this.fillCells = new ArrayList<>(25);
        
        this.CellSize = 20;
    }
    
    public void setCellSize(int size){
        this.CellSize = size;
        this.calcMaxCellsWidth(this.getWidth());
        this.calcMaxCellsHeight(this.getHeight());
        System.out.println("GameGrid.java");
        System.out.println("Grid: x:"+this.getX()+", y:"+this.getY()+", Höhe:"+this.getHeight()+", Breite:"+this.getWidth());
        System.out.println("CellsWidth: "+this.maxCellsWidth+", CellsHeight: "+this.maxCellsHeight);
    }
    
    public int getCellSize(){
        return this.CellSize;
    }
    
    public int calcMaxCellsWidth(int width){
        this.maxCellsWidth = (width-(width%this.CellSize))/this.CellSize-1;
        return this.maxCellsWidth;
    }
    
    public int getMaxCellsWidth(){
        return this.maxCellsWidth;
    }
    
    public int calcMaxCellsHeight(int height){
        this.maxCellsHeight = (height-(height%this.CellSize))/this.CellSize-1;
        return this.maxCellsHeight;
    }
    
    public int getMaxCellsHeight(){
        return this.maxCellsHeight;
    }
    
    @Override
    public void setBounds(int x, int y, int width, int height){
        int CellWidth = this.calcMaxCellsWidth(width-1);
        int CellHeight = this.calcMaxCellsHeight(height-1);
        System.out.println("CellsWidth: "+this.maxCellsWidth+", CellsHeight: "+this.maxCellsHeight);
        super.setBounds(x, y, width-1, height-1);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
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
            System.out.println("Vertikal: x1:"+(this.getX()+(i*this.CellSize))+", y1:"+this.getY()+", x2:"+(this.getX()+(i*this.CellSize))+", y2:"+(this.getY()+(this.CellSize*this.maxCellsHeight)));
        }
        
        // Zeichnet die horizontalen Linien
        for (int i = 0; i <= this.maxCellsHeight; i++) {
            g.drawLine(this.getX(), this.getY()+(i*this.CellSize), this.getX()+(this.CellSize*this.maxCellsWidth), this.getY()+(i*this.CellSize));
            System.out.println("Horizont: x1:"+(this.getX())+", y1:"+(this.getY()+(i*this.CellSize))+", x2:"+(this.getX()+(this.CellSize*this.maxCellsWidth))+", y2:"+(this.getY()+(i*this.CellSize)));
        }
    }
    
        public void fillCell(int x, int y) {
            fillCells.add(new Point(x, y));
            repaint();
        }
        
        public void alterGridSize(int size){
            this.setCellSize(size);
            repaint();
        }
    
}
