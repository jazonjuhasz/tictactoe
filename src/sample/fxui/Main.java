package sample.fxui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    Stage window;
    Scene scene2, gameScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;


        StackPane tilesPane2 = new StackPane();
        Button button2 = new Button("Change size");
        button2.setTranslateX(1000);
        button2.setTranslateY(100);
        button2.setOnAction(event -> {
            window.setScene(gameScene);
        });

        Pane base2 = new Pane();
        base2.setPrefSize(1200, 750);

        base2.getChildren().addAll(Tile.createContent(10), button2);

        tilesPane2.getChildren().add(base2);

        scene2 = new Scene(tilesPane2);




        StackPane tilesPane = new StackPane();
        Button button1 = new Button("Change size");
        button1.setTranslateX(1000);
        button1.setTranslateY(100);
        button1.setOnAction(event -> {
            window.setScene(scene2);
        });

        Pane base = new Pane();
        base.setPrefSize(1200, 750);

        base.getChildren().addAll(Tile.createContent(3), button1);

        tilesPane.getChildren().add(base);

        gameScene = new Scene(tilesPane);
        //primaryStage.setScene(gameScene);
        window.setTitle("Tic-Tac-Toe");
        window.setResizable(false);
        window.setScene(gameScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
