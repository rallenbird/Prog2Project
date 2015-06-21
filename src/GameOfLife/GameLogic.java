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
public class GameLogic{
    
    //Filled by GameWindow
    private GameGrid gg_grid;
    private boolean[][] isAlive;
    
    public GameLogic(GameGrid grid){
        gg_grid = grid;

    }
    
    public boolean getCellState(int x, int y){
        return this.isAlive[x][y];
    }
    
    public void setCellState(int x, int y, boolean state){
        this.isAlive[x][y] = state;
    }
    
    public void drawLogic(){
        isAlive = new boolean[gg_grid.getMaxCellsWidth() + 1][gg_grid.getMaxCellsHeight() + 1];

        for(int i = 0; i <= gg_grid.getMaxCellsWidth(); i++){
            
            for(int j = 0; j <= gg_grid.getMaxCellsHeight(); j++){
                isAlive[i][j] = false;
                System.out.println(isAlive[i][j]);
            }
        }
         
    }
    
    //get neighbors of cell
    public int getNeighbours(int x, int y){
        
        int neighbours = 0;
        if(x != 0 && y != 0){
            if(this.isAlive[(x - 1)][(y - 1)]){
                neighbours++;
            }
        }
        if(x != 0){
            if(this.isAlive[(x - 1)][(y + 1)]){
                neighbours++;
            }
             if(this.isAlive[(x - 1)][y]){
                neighbours++;
            }
        }
        if(y != 0){
            if(this.isAlive[(x + 1)][(y - 1)]){
                neighbours++;
            }
            if(this.isAlive[x][(y - 1)]){
                neighbours++;
            }
        }
        if(this.isAlive[(x + 1)][(y + 1)]){
            neighbours++;
        }
        if(this.isAlive[(x + 1)][y]){
            neighbours++;
        }
        if(this.isAlive[x][(y + 1)]){
            neighbours++;
        }
        
        System.out.println(neighbours);
        
        return neighbours;
    }
    
    
    public void nextGeneration(){
        int n = 0;
        for(int i = 0; i <= gg_grid.getMaxCellsWidth() -1; i++){
            for(int j = 0; j <= gg_grid.getMaxCellsHeight() -1; j++){
                //System.out.println(cell);
                n = this.getNeighbours(i, j);
                if(n <= 1 || n >= 4){
                    //this.removeCell(cell.x, cell.y);
                    this.isAlive[i][j] = false;          
                }

                if(this.isAlive[i][j] == false && n == 3){
                    this.isAlive[i][j] = true;
                }
            }
        }
    }
    
    
}
