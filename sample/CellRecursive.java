package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Cell class for the recursive Maze
 *
 * */

public class CellRecursive {


    private boolean wallUp;
    private boolean wallDown;
    private boolean wallLeft;
    private boolean wallRight;
    private boolean visited;
    private boolean current;

    CellRecursive neighborUp;
    CellRecursive neighborDown;
    CellRecursive neighborLeft;
    CellRecursive neighborRight;

    private int x;
    private int y;

    private Canvas canvas;


    /**
     *  The constructor for the cell class declares the initial values of the cell
     *
     * **/
    public CellRecursive (int x, int y, Canvas canvas) {

        this.wallDown = true;
        this.wallLeft = true;
        this.wallRight = true;
        this.wallUp = true;
        this.canvas = canvas;

        this.x = x;
        this.y = y;
        this.current = false;
        this.visited = false;
    }

    /**
     *  The visualisation of the walls. Based on what boolean values are true for the cell the walls are shown.
     *
     * **/
    void drawWalls(int cellSize) {

        if (wallUp) {
            canvas.getGraphicsContext2D().strokeLine(cellSize * x, cellSize * y, cellSize * (x + 1), cellSize * y);
        }
        if (wallDown) {
            canvas.getGraphicsContext2D().strokeLine(cellSize * x, cellSize * (y + 1), cellSize * (x + 1), cellSize * (y + 1));
        }
        if (wallLeft) {
            canvas.getGraphicsContext2D().strokeLine(cellSize * x, cellSize * y, cellSize * x, cellSize * (y + 1));
        }
        if (wallRight) {
            canvas.getGraphicsContext2D().strokeLine(cellSize * (x + 1), cellSize * y, cellSize * (x + 1), cellSize * (y + 1));
        }
    }


    /**
     *  The visualisation of the cells state.
     *  Colors the cell differently depending on if itt curret, visited or unvisited.
     *
     * **/
    void colorCell(int cellSize) {


        if (this.visited) {
            canvas.getGraphicsContext2D().setFill(Color.GREY);
            //   canvas.getGraphicsContext2D().fillRect(x,y,cellSize,cellSize);
            //  System.out.println("This cell is not visted nor current");
        } else {
            canvas.getGraphicsContext2D().setFill(Color.RED);
            //   canvas.getGraphicsContext2D().fillRect(x,y,cellSize,cellSize);
       //     System.out.println("This Visisted is called");
        }
        if (this.current) {
            canvas.getGraphicsContext2D().setFill(Color.BLUE);
            // canvas.getGraphicsContext2D().fillRect(x,y,cellSize,cellSize);
          //  System.out.println("This curent is called");

        }

        canvas.getGraphicsContext2D().fillRect(cellSize * x, cellSize * y, cellSize, cellSize);
    } // color Cell End

    // Creates an ArrayList and adds the neighbors to that list, assuming they exist.
    // The neighbors are found in the maze method, as they aren't set before the grid is actually created
    // The method returns a random cell based on its index in the ArrayList.

    /**
     *  Creates an Arraylist and adds the neighbors to that list, assuming they exist.
     *  The neighbors are found in the maze class with the cellNeighbors method, since they arent set before the grid
     *  is actually created. The method returns a random cell based on its index in the arrayList.
     *  This is how the maze achives to be new everytime its created.
     *
     * **/
    public CellRecursive checkNeighbor() {
        ArrayList<CellRecursive> Neighbors = new ArrayList<>();
        CellRecursive chosenNeighbor;

        if (neighborUp != null && !this.neighborUp.getVisited()) {
            Neighbors.add(neighborUp);
        }
        if (neighborDown != null && !this.neighborDown.getVisited()) {

            Neighbors.add(neighborDown);
        }
        if (neighborRight != null && !this.neighborRight.getVisited()) {

            Neighbors.add(neighborRight);
        }
        if (neighborLeft != null && !this.neighborLeft.getVisited()) {

            Neighbors.add(neighborLeft);
        }
        if (!Neighbors.isEmpty()) {

            chosenNeighbor = Neighbors.get(ThreadLocalRandom.current().nextInt(0, Neighbors.size()));
            Neighbors.remove(chosenNeighbor);
            return chosenNeighbor;
            //       Neighbors.get(ThreadLocalRandom.current().nextInt(0,Neighbors.size()));

        } else {
            return null;
        }
    }

    /**
     *  Below are the get / set methods for each of the variables in the cell class.
     *
     * */


    void setCurrent(boolean c) {

        this.current = c;
    }

    boolean getCurrent() {

        return this.current;
    }

    void setVisited(boolean v) {

        this.visited = v;
    }

    boolean getVisited() {

        return this.visited;
    }

    void setX(int x) {

        this.x = x;
    }

    void setY(int y) {

        this.y = y;
    }

    int getX() {

        return this.x;
    }

    int getY() {
        return this.y;
    }

    void neighborUp(CellRecursive neighborUp) {

        this.neighborUp = neighborUp;
    }

    void neighborDown(CellRecursive neighborDown) {

        this.neighborDown = neighborDown;
    }

    void neighborLeft(CellRecursive neighborLeft) {

        this.neighborLeft = neighborLeft;
    }

    void neighborRight(CellRecursive neighborRight) {

        this.neighborRight = neighborRight;
    }

    void setWallUp(boolean wallUp) {

        this.wallUp = wallUp;
    }

    void setWallDown(boolean wallDown) {

        this.wallDown = wallDown;
    }

    void setWallLeft(boolean wallLeft) {

        this.wallLeft = wallLeft;
    }

    void setWallRight(boolean wallRight) {

        this.wallRight = wallRight;
    }


}
