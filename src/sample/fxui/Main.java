package sample.fxui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane tilesPane = new StackPane();
        tilesPane.getChildren().addAll(Tile.createContent(10));

        Scene gameScene = new Scene(tilesPane);
        primaryStage.setScene(gameScene);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
