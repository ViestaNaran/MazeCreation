package sample;

import javafx.scene.canvas.Canvas;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * The RecursiveMaze algorithm
 *
 */

public class MazeRecursive {

    int width;
    int height;

    CellRecursive[][] maze;
    CellRecursive current;
    CellRecursive holder;
    Canvas canvas;

    ArrayDeque<CellRecursive> stack = new ArrayDeque<>();

    /**
     *  Constructor for the Recursive maze, as with Kruskal, it takes ints to determine size of the grid, and a canvas
     *  It then creates all the cells. Sets the current cell to 0,0 the first cell in the grid, to start the
     *  traversal through the grid that will eventually lead to the maze. It also adds that cell to the stack
     *  thus beginning the recursive method "createPath"
     *
     * **/

    public MazeRecursive(int width, int height, Canvas canvas) {

        this.width = width;
        this.height = height;
        this.canvas = canvas;

        this.maze = new CellRecursive[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                maze[i][j] = new CellRecursive(i, j, canvas);

            }
        }
        this.current = maze[0][0];
        current.setVisited(true);
        current.setCurrent(true);
        stack.add(current);

    } // Maze Constructor Ends

    /**
     *  The visualisation of the grid, colors the cell and then draws the walls
     *
     * **/

    void drawMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {

                maze[i][j].colorCell(20);
                maze[i][j].drawWalls(20);
            }
        }
    } // Draw maze Ends

    /**
     *  Gives the cell its neighbors. These are needed to check where it can move
     *  when the path is created. It only adds cells within the grid, secured by the if statements
     *  This has to happen here, instead of inside the CellRecursive class, because the cells around each cell
     *  aren't created before the maze method is run. The neighbors are added to the cell through setMethods in the
     *  CellRecursive class.
     *
     * **/

    void cellNeighbors() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                CellRecursive currentCell = maze[i][j];

                if (i > 0) {
                    currentCell.neighborLeft(maze[i - 1][j]);
                }
                if (i < maze.length - 1) {

                    currentCell.neighborRight(maze[i + 1][j]);
                }
                if (j > 0) {
                    currentCell.neighborUp(maze[i][j - 1]);
                }
                if (j < maze[0].length - 1) {

                    currentCell.neighborDown(maze[i][j + 1]);
                }
            }
        }
    } // cellNeighbors End

    /**
     *  The tearWalls method removes walls. It takes 2 cells and calculates their position in comparison to each other.
     *  Depending on the calculation it then sets their booleans to false. Every wall in the grid is in essence 2 walls
     *  one comming from each of the neighboring cells, meaning that every move from the pathCreater removes 2 walls, not one.
     *
     * **/

    void tearWalls(CellRecursive cell1, CellRecursive cell2) {

        if (cell1.getX() - cell2.getX() == -1) {

            cell1.setWallRight(false);
            cell2.setWallLeft(false);
        }
        if (cell1.getX() - cell2.getX() == 1) {

            cell1.setWallLeft(false);
            cell2.setWallRight(false);
        }
        if (cell1.getY() - cell2.getY() == -1) {

            cell1.setWallDown(false);
            cell2.setWallUp(false);
        }
        if (cell1.getY() - cell2.getY() == 1) {

            cell1.setWallUp(false);
            cell2.setWallDown(false);
        }
    } // tearWalls End

    /**
     *  The createPath method is the recursive element in this class.
     *  It achieves the recursive quality by continuesly changing the current cell and then calling checkNeighbor
     *  on that cell.
     *  It does this for aslong as there are elements in the stack, secured in the constructor method.
     *  It then uses the holder cell to figure out where it should move from the current cell, by checking the
     *  current cells list of neighbors in the checkNeighbors method.
     *  It then tears the walls between the 2 and adds the current to the stack, and changes holder to the new current.
     *  Assuming it runs into a cell that has no neighbors that aren't visited, meaning it cannot go anywhere,
     *  it will take the last added cell from the stack and make that the current cell, continuing from there.
     *  It then returns true, when it has removed all the elements from the stack.
     *
     * **/

    boolean createPath() {
        if (!stack.isEmpty()) {
            if (current.checkNeighbor() != null) {

                holder = current.checkNeighbor();
                tearWalls(current, holder);
                stack.add(current);
                current.setCurrent(false);
                current = holder;
                current.setVisited(true);
                current.setCurrent(true);

            } else if (current.checkNeighbor() == null && !stack.isEmpty()) {
                current.setCurrent(false);
                current = stack.removeLast();
                current.setCurrent(true);
            }
            return false;
        } else {
          //  System.out.println("Maze is finished");
            return true;
        }
    }

    void openMaze() {
        maze[0][0].setWallUp(false);
        maze[maze.length-1][maze[0].length-1].setWallRight(false);
        drawMaze();
    }



} // MazeRecursive ends
