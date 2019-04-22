/**  AStar: algorithme de plus chemin
 * 
 * Vous ne devez pas commprendre cet algorithm en detail. Vous pouvez simplement l'utiliser
 * de la facon suivante: 
 * 
 * -> on instancie un objet AStart avec la position de depart, la destination et la liste de obstacles.
 * -> on invoque la methode getNextStep pour avoir la direction à suivre lors du premier changement de
 *    case. 0,1,2,3 correspondent restpectivement a Est, Nord, Ouest, Sud.
 * 
 * */
package model.characters;

import model.map.Map;
import model.map.Tile;

import java.util.PriorityQueue;

public class AStar {
	private static int mapSize = 25;
	private boolean closed[][];
	private int startI, startJ;
	private int endI, endJ;
	private Cell [][] grid; 
	private PriorityQueue<Cell> open;
	private int V_H_COST = 1;
	private int DIAGONAL_COST = 100000;

	public AStar(Tile start, Tile end, Map map) {
		startI = start.getX();
		startJ = start.getY();
		endI = end.getX();
		endJ = end.getY();

		grid = new Cell[map.WIDTH][Map.HEIGHT];

        closed = new boolean[map.WIDTH][Map.HEIGHT];
        open = new PriorityQueue<>((Object o1, Object o2) -> {
                Cell c1 = (Cell)o1;
                Cell c2 = (Cell)o2;

                return c1.finalCost<c2.finalCost?-1:
                        c1.finalCost>c2.finalCost?1:0;

        	});

        for(int i=0;i<Map.WIDTH;++i){
              for(int j=0;j<map.HEIGHT;++j){
                  if(map.getTileAt(i,j).isWalkable()){
                      grid[i][j] = new Cell(i, j);
                      grid[i][j].heuristicCost = Math.abs(i-endI)+Math.abs(j-endJ);
                  }
              }
           }
        grid[endI][endJ].finalCost = 0;

		open.add(grid[startI][startJ]);
		Cell current;

		while(true){ 
			current = open.poll();
			if(current==null)break;
			closed[current.i][current.j]=true; 

			if(current.equals(grid[endI][endJ])){
				return; 
			}

			Cell t;  
			if(current.i-1>=0){
				t = grid[current.i-1][current.j];
				checkAndUpdateCost(current, t, current.finalCost+V_H_COST); 

				if(current.j-1>=0){                      
					t = grid[current.i-1][current.j-1];
					checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST); 
				}

				if(current.j+1<grid[0].length){
					t = grid[current.i-1][current.j+1];
					checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST); 
				}
			} 

			if(current.j-1>=0){
				t = grid[current.i][current.j-1];
				checkAndUpdateCost(current, t, current.finalCost+V_H_COST); 
			}

			if(current.j+1<grid[0].length){
				t = grid[current.i][current.j+1];
				checkAndUpdateCost(current, t, current.finalCost+V_H_COST); 
			}

			if(current.i+1<grid.length){
				t = grid[current.i+1][current.j];
				checkAndUpdateCost(current, t, current.finalCost+V_H_COST); 

				if(current.j-1>=0){
					t = grid[current.i+1][current.j-1];
					checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST); 
				}

				if(current.j+1<grid[0].length){
					t = grid[current.i+1][current.j+1];
					checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST); 
				}  
			}
		} 
	}

	static class Cell{  
		int heuristicCost = 0; //Heuristic cost
		int finalCost = 0; //G+H
		int i, j;
		Cell parent; 

		Cell(int i, int j){
			this.i = i;
			this.j = j; 
		}

		@Override
		public String toString(){
			return "["+this.i+", "+this.j+"]";
		}
	}


	private void checkAndUpdateCost(Cell current, Cell t, int cost){
		if(t == null || closed[t.i][t.j])return;
		int t_final_cost = t.heuristicCost+cost;

		boolean inOpen = open.contains(t);
		if(!inOpen || t_final_cost<t.finalCost){
			t.finalCost = t_final_cost;
			t.parent = current;
			if(!inOpen)open.add(t);
		}
	}
	
	public int getNextStep() {
		int direction = -1;
		if(closed[endI][endJ]){
			int deltai = 0;
			int deltaj = 0;
		   //Trace back the path 
			Cell current = grid[endI][endJ];
			while(current.parent!=null){
				if (current.parent.i == startI && current.parent.j == startJ) {
					deltai = current.i - startI;
					deltaj = current.j - startJ;
					direction = 1 - deltai + deltaj*(deltaj + 1);
				}
				current = current.parent;
			} 
	   }else {
			direction = -100;
		}
		return direction;
	}
}
