package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

    /**
     *  The wall class for the Kruskal Maze
     *
     * */

public class Wall {


    Canvas canvas;
  //  int x1;
  //  int x2;
  //  int y1;
  //  int y2;

    double x1;
    double x2;
    double y1;
    double y2;

    public CellKruskal cell1;
    public CellKruskal cell2;

    String wallPointer;

    /**
     *  The constructor for the wall.
     *  Sets the values for the pointers on the walls and te color of the line.
     *
     * **/
    // public void drawWall(int x1, int x2, int y1, int y2, Canvas canvas) {
    public void drawWall(double x1, double y1, double x2, double y2, Canvas canvas) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        this.canvas = canvas;

        canvas.getGraphicsContext2D().setStroke(Color.BLACK);
        canvas.getGraphicsContext2D().strokeLine(x1,y1,x2,y2);
    }








}
