package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *  The Cell class for the kruskal maze.
 *  Differences between the Kruskal cells and the Recursive cells is that they are colored differently
 *  and dont need to hold information about their neighbors since the walls arent based on the cells, but rather
 *  the random tearing of walls.
 *
 *  These cells needs to belong to a set and be capable of storing an arraylist to match with their fellow cells when
 *  they are merged through the maze generation.
 *
 * */

public class CellKruskal {

    ArrayList<CellKruskal> cellSet = new ArrayList();

    int x;
    int y;

    float red;
    float blue;
    float green;

    Random r = new Random();

    boolean wallLeftPlaced;
    boolean wallRightPlaced;
    boolean wallUpPlaced;
    boolean wallDownPlaced;

    boolean visited;

    CellKruskal neighborUp;
    CellKruskal neighborDown;
    CellKruskal neighborRight;
    CellKruskal neighborLeft;

    Wall wallLeft;
    Wall wallRight;
    Wall wallUp;
    Wall wallDown;
    Color randomColour;

    Canvas canvas;

    /**
     *  Constructor for the Kruskal cells sets the values of the cells
     *
     * **/

    public CellKruskal(int x, int y, Canvas canvas) {

        this.x = x;
        this.y = y;
        this.canvas = canvas;

        this.wallLeftPlaced = true;
        this.wallDownPlaced = true;
        this.wallRightPlaced = true;
        this.wallUpPlaced = true;

        this.red = r.nextFloat() * (1.0f - 0.0f) - 0.0f;
        this.blue = r.nextFloat() * (1.0f - 0.0f) - 0.0f;
        this.green = r.nextFloat() * (1.0f - 0.0f) - 0.0f;

        randomColour = new Color(this.red, this.green, this.blue, 1);

    }

    /**
     * Instantiate the walls and draws them with the drawWall method based on the cells boolean values
     *
     * **/

    void drawWalls(double cellSize) {
        if (wallUpPlaced) {
            wallUp = new Wall();

            wallUp.drawWall(cellSize * x, cellSize * y, cellSize * (x + 1), cellSize * y, canvas);
        }
        if (wallDownPlaced) {
            wallDown = new Wall();

            wallDown.drawWall(cellSize * x, cellSize * (y + 1), cellSize * (x + 1), cellSize * (y + 1), canvas);
        }
        if (wallLeftPlaced) {
            wallLeft = new Wall();

            wallLeft.drawWall(cellSize * x, cellSize * y, cellSize * x, cellSize * (y + 1), canvas);
        }
        if (wallRightPlaced) {
            wallRight = new Wall();

            wallRight.drawWall(cellSize * (x + 1), cellSize * y, cellSize * (x + 1), cellSize * (y + 1), canvas);
        }
    }

    /**
     * The visualisation of the cells, intended to color cells of different sets different colors.
     * Used to visualise the joining of the sets.
     *
     * **/

    void colorCell(int cellSize) {

        //  canvas.getGraphicsContext2D().setFill(randomColour);
        canvas.getGraphicsContext2D().setFill(Color.SLATEBLUE);
        canvas.getGraphicsContext2D().fillRect(cellSize * x, cellSize * y, cellSize, cellSize);

    }

    /**
     *
     *
     *
     *
     * **/

    public CellKruskal checkNeighbor() {
        ArrayList<CellKruskal> Neighbors = new ArrayList<>();
        CellKruskal chosenNeighbor;

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

        } else {
            return null;
        }
    }

    void setVisited(boolean v) {

        this.visited = v;
    }
    boolean getVisited() {

        return this.visited;
    }

    public void setNeighborUp (CellKruskal neighborUp) {
        this.neighborUp = neighborUp;
    }
    public void setNeighborDown (CellKruskal neighborDown) {
        this.neighborUp = neighborDown;
    }
    public void setNeighborLeft (CellKruskal neighborLeft) {
        this.neighborUp = neighborLeft;
    }
    public void setNeighborRight (CellKruskal neighborRight) {
        this.neighborUp = neighborRight;
    }




}
