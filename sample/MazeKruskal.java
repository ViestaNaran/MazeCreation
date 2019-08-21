package sample;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Random;

/**
 *  The Kruskal maze algorithm
 */


public class MazeKruskal {

    int width;
    int height;

    CellKruskal[][] maze;

    Canvas canvas;
    ArrayList<Wall> wallSet = new ArrayList<Wall>();

    /**
     *  Constructor for the Kruskal maze. Takes integers for the size of the maze, and a canvas to draw on
     *  Then defines an arraylist which the cell is added to and then contains as a list.
     *  Also sets the values of its wallLeft and wallUP to false, assuming the x is above 0
     *  or y is above 0. This is to make sure that the maze doesnt draw a "double layer" of walls.
     *
     *
     * **/
    public MazeKruskal(int width, int height, Canvas canvas) {

        this.canvas = canvas;
        this.width = width;
        this.height = height;
        this.maze = new CellKruskal[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                ArrayList<CellKruskal> set = new ArrayList();
                maze[i][j] = new CellKruskal(i, j, canvas);
                set.add(maze[i][j]);
                maze[i][j].cellSet = set;

                if (i > 0) {
                    maze[i][j].wallLeftPlaced = false;
                }
                if (j > 0) {
                    maze[i][j].wallUpPlaced = false;
                }
            }
        }
    } // Maze constructor ends

    /**
     *  Draws the maze. It colors all the cells and then draws the walls based on the
     *  active boolean values of the cell.
     *
     * **/
    public void drawMaze() {

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {

                maze[i][j].colorCell(20);
                maze[i][j].drawWalls(20);
            }
        }
    } // drawMaze ends

    /**
     *  In this method we create walls and add them to the wallSet, so we can later pull out a random wall.
     *
     * **/

    void addWallSet() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                CellKruskal currentCell = maze[i][j];

                if (!currentCell.wallUpPlaced) {
                    Wall currentWall = new Wall();
                    currentWall.cell1 = currentCell;
                    currentWall.cell2 = maze[i][j - 1];
                    currentWall.wallPointer = "up";
                    wallSet.add(currentWall);
                }
                if (!currentCell.wallDownPlaced) {
                    Wall currentWall = new Wall();
                    currentWall.cell1 = currentCell;
                    currentWall.cell2 = maze[i][j + 1];
                    currentWall.wallPointer = "down";
                    wallSet.add(currentWall);
                }
                if (!currentCell.wallLeftPlaced) {
                    Wall currentWall = new Wall();
                    currentWall.cell1 = currentCell;
                    currentWall.cell2 = maze[i - 1][j];
                    currentWall.wallPointer = "left";
                    wallSet.add(currentWall);
                }
                if (!currentCell.wallRightPlaced) {
                    Wall currentWall = new Wall();
                    currentWall.cell1 = currentCell;
                    currentWall.cell2 = maze[i + 1][j];
                    currentWall.wallPointer = "right";
                    wallSet.add(currentWall);
                }
            }
        }
    }
    void setNeighBors() {

        CellKruskal currentCell = maze[0][0];

    for(int i = 0; i < maze.length; i ++) {
        for(int j = 0; j < maze[0].length; j++) {

            if( i > 0 ) {
                currentCell.setNeighborLeft(maze[i-1][j]);
            }
            if(i < maze.length) {
                currentCell.setNeighborRight(maze[i+1][j]);
            }
            if(j > 0) {


                }
            }
        }
    }

    /**
     *  This method gets a random int from 0 to the size of the wallSet list, aslong as the wallSet list is not empty
     *  It uses that random int to select a wall at that index in the wallSet list.
     *
     *
     * **/
    boolean deleteWall() {
        Random randomWall = new Random();

        while (wallSet.size() > 0 && !checkSet()) {

         //   System.out.println("Does this work?");
            int r = randomWall.nextInt(wallSet.size());
            Wall deleteWall = wallSet.get(r);

            if (deleteWall.cell1.cellSet != deleteWall.cell2.cellSet) {

                if (deleteWall.wallPointer == "up") {
                    deleteWall.cell2.wallDownPlaced = false;

                } else if (deleteWall.wallPointer == "down") {
                    deleteWall.cell2.wallUpPlaced = false;

                } else if (deleteWall.wallPointer == "left") {
                    deleteWall.cell2.wallRightPlaced = false;

                } else if (deleteWall.wallPointer == "right") {
                    deleteWall.cell2.wallLeftPlaced = false;
                }

                deleteWall.cell1.cellSet.addAll(deleteWall.cell2.cellSet);
                deleteWall.cell2.cellSet.removeAll(deleteWall.cell2.cellSet);

                for (int i = 0; i < deleteWall.cell1.cellSet.size(); i++) {
                    CellKruskal currentCell = deleteWall.cell1.cellSet.get(i);
                    currentCell.cellSet = deleteWall.cell1.cellSet;
                }
            }
            wallSet.remove(r);
            return false;
        }
        return true;
    } //deleteWalls end

    /**
     *
     *
     * **/
    boolean checkSet() {
        boolean done = false;
        int antal = maze[0][0].cellSet.size();

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {

                if (antal == (maze.length * maze[0].length)) {
                    done = true;
                }
            }
        }
        return done;
    }// checkSet ends

    void openMaze() {
        maze[0][0].wallUpPlaced = false;
        maze[maze.length-1][maze[0].length-1].wallRightPlaced = false;
        drawMaze();
    }
}
