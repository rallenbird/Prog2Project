/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOfLife;

import java.awt.Point;

/**
 *
 * @author Ralle
 */
public class GameCell extends Point {

  
    public boolean cellState;
    
    public GameCell() {
        this.x = 0;
        this.y = 0;
        this.cellState = false;
    }
    
    public GameCell(int cellCoordinateX, int cellCoordinateY) {
        this.x = cellCoordinateX;
        this.y = cellCoordinateY;
        this.cellState = false;
    }
     public GameCell(int cellCoordinateX, int cellCoordinateY, boolean cellState) {
        this.x = cellCoordinateX;
        this.y = cellCoordinateY;
        this.cellState = cellState;
    }
    
    public int hash(){
        return 31 * x + y;
    }
    
    
    
}
