package sample;

import java.util.ArrayDeque;

public class MazeSolver {

    ArrayDeque<CellKruskal> stackK = new ArrayDeque<>();
    ArrayDeque<CellRecursive> stakcR = new ArrayDeque<>();
    CellKruskal[][] mazeKruskal;
    CellRecursive[][] mazeRecursive;

    CellKruskal currentCellK;

    public MazeSolver(CellKruskal[][] mazeKruskal, CellRecursive[][] mazeRecursive) {
        this.mazeKruskal = mazeKruskal;
        this.mazeRecursive = mazeRecursive;

   }

    void solveKruskal() {
        boolean done = false;
        currentCellK = mazeKruskal[0][0];


        // This method also needs to check the neighbors of the cells to insure that it does not run out of bounds
        while (!done) {
                System.out.println("x =" + currentCellK.x + "y = " + currentCellK.y);

                if (currentCellK.x != mazeKruskal.length && currentCellK.y != mazeKruskal[0].length) {

                    if (!currentCellK.wallRightPlaced && !mazeKruskal[currentCellK.x + 1][currentCellK.y].visited) {

                        moveToCell(currentCellK, mazeKruskal[currentCellK.x + 1][currentCellK.y]);

                    } else if (!currentCellK.wallDownPlaced && !mazeKruskal[currentCellK.x][currentCellK.y + 1].visited) {

                        moveToCell(currentCellK, mazeKruskal[currentCellK.x][currentCellK.y + 1]);

                    } else if (!currentCellK.wallLeftPlaced && !mazeKruskal[currentCellK.x][currentCellK.y -1].visited) {

                        moveToCell(currentCellK, mazeKruskal[currentCellK.x - 1][currentCellK.y]);

                    } else if (!currentCellK.wallUpPlaced && !mazeKruskal[currentCellK.x][currentCellK.y + 1].visited) {

                        moveToCell(currentCellK, mazeKruskal[currentCellK.x][currentCellK.y - 1]);

                    } else {
                        done = true;
                    }
                } else {
                    if (!stackK.isEmpty())
                        stackK.removeLast();
                        currentCellK = stackK.getLast();
                }
            }
        }

    void solveRecursive() {
        boolean done = false;
        while (!done) {

        }
    }
    void moveToCell(CellKruskal current, CellKruskal newCurrent) {
        stackK.add(current);
        current.visited = true;
        currentCellK = newCurrent;
    }
}
