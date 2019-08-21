package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    Canvas canvasKruskal;
    Canvas canvasRecursive;

    MazeKruskal mazeKruskal;

    MazeRecursive mazeRecursive;
    MazeSolver mazeSolverKruskal;


    boolean kruskalDone = false;
    boolean recursiveDone = false;

    /**
     * In this method we setup the canvas / scene for us to draw the mazes on.
     * We have 2 different canvas 1 for each of the maze methods, complete with their
     * respective scenes and panes.
     * Wrapped around our handle method is the Timeline function. In this we call our drawMaze functions
     * This is to give it the visual effect, showing the grid being carved out.
     *
     ***/
    @Override
    public void start(Stage primaryStage) throws Exception{

        /**
         * Instantiate the canvas to draw on.
         **/
        canvasKruskal = new Canvas(800,400);
        canvasRecursive = new Canvas(800,400);

        GraphicsContext gcK = canvasKruskal.getGraphicsContext2D();
        GraphicsContext gcR = canvasRecursive.getGraphicsContext2D();

        gcK.setStroke(Color.BLACK);
        gcR.setStroke(Color.BLACK);


        /**
         * Instantiate mazes on their respective canvas
         **/
        mazeKruskal = new MazeKruskal(10,10,canvasKruskal);
        mazeKruskal.addWallSet();
     //   mazeKruskal.setNeighBors();

        mazeRecursive = new MazeRecursive(10,10,canvasRecursive);
        mazeRecursive.cellNeighbors();

        mazeSolverKruskal = new MazeSolver(mazeKruskal.maze,mazeRecursive.maze);

        /**
         * Time event to grant a smoother visual representation.
         * */
        Timeline tidsLinje = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /**
                 *  As long as the deleteWall methods returns true, we want to draw the maze.
                 **/

                if (!mazeKruskal.deleteWall()) {
                    mazeKruskal.drawMaze();
                } else {
                    kruskalDone = true;
                    mazeKruskal.openMaze();
                }
                /**
                 *   As long as createPath returns false, we would like to draw the maze again.
                 **/
                if(!mazeRecursive.createPath()) {
                 mazeRecursive.drawMaze();
                } else {
                    recursiveDone = true;
                    mazeRecursive.openMaze();
                }
                if(kruskalDone && recursiveDone) {
                  //  System.out.println("Maze konstruction complete, proceeding to solve");
            //        mazeSolverKruskal.solveKruskal();
                }
            }
        }));

        tidsLinje.setCycleCount(Timeline.INDEFINITE);
        tidsLinje.play();

        /**
         *  Creates panes / scenes to add the canvas too.
         */

        Pane rootKruskal = new Pane();
        Pane rootRecursive = new Pane();
        rootKruskal.getChildren().add(canvasKruskal);
        rootRecursive.getChildren().add(canvasRecursive);

        Scene sceneKruskal = new Scene(rootKruskal);
        Scene sceneRecursive = new Scene(rootRecursive);

        Stage secondaryStage = new Stage();

        primaryStage.setScene(sceneKruskal);
        secondaryStage.setScene(sceneRecursive);

        primaryStage.show();
        secondaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
