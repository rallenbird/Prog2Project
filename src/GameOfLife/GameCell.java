/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameOfLife;

/**
 *
 * @author Ralle
 */
public class GameCell {

    int cellCoordinateX;
    int cellCoordinateY;
    int cellCount;
    int cellState;
    
    public GameCell(int cellCoordinateX, int cellCoordinateY, int cellState) {
        this.cellCoordinateX = cellCoordinateX;
        this.cellCoordinateY = cellCoordinateY;
        this.cellState = cellState;
    }
    
    
    
    
}
