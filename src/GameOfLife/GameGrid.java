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
    
    public GameGrid(){
        fillCells = new ArrayList<>(25);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        for (Point fillCell : fillCells) {
            int cellX = 10 + (fillCell.x * 10);
            int cellY = 10 + (fillCell.y * 10);
            g.setColor(Color.RED);
            g.fillRect(cellX, cellY, 10, 10);
        }
        g.setColor(Color.GREEN);
        g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        for (int i = 10; i <= this.getWidth(); i += 10) {
            g.drawLine(i, 10, i, this.getHeight()+10);
        }

        for (int i = 10; i <= this.getHeight(); i += 10) {
            g.drawLine(10, i, this.getWidth()+10, i);
        }
    }
    
        public void fillCell(int x, int y) {
            fillCells.add(new Point(x, y));
            repaint();
        }
    
}
