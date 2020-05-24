package sample.fxui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    Stage window;
    Scene menuScene,
            singleGame3,
            singleGame5,
            singleGame10,
            singleGame15;

    int three = 3;
    int five = 5;
    int ten = 10;
    int fifteen = 15;

    Tile tile3 = new Tile(three);
    Tile tile5 = new Tile(five);
    Tile tile10 = new Tile(ten);
    Tile tile15 = new Tile(fifteen);

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        //main menu
        {
            StackPane menu = new StackPane();

            Pane menuBase = new Pane();
            menuBase.setPrefSize(600, 600);

            Buttons newGameButton = new Buttons(140, 60);
            newGameButton.createButton(newGameButton);
            newGameButton.text.setText("3x3");
            newGameButton.setTranslateX(120);
            newGameButton.setTranslateY(80);

            Buttons multiPlayerButton = new Buttons(140, 60);
            multiPlayerButton.createButton(multiPlayerButton);
            multiPlayerButton.text.setText("5x5");
            multiPlayerButton.setTranslateX(120);
            multiPlayerButton.setTranslateY(180);

            menuBase.getChildren().addAll(newGameButton, multiPlayerButton);
            menu.getChildren().add(menuBase);
            menuScene = new Scene(menu);

            newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame3));
            multiPlayerButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame5));
        }

        //size: TEN
        {
            StackPane tilesPane2 = new StackPane();
            Button button2 = new Button("Back to menu");
            button2.setTranslateX(1000);
            button2.setTranslateY(100);
            button2.setOnAction(event -> {
                window.setScene(menuScene);
            });

            Pane base2 = new Pane();
            base2.setPrefSize(1200, 750);

            base2.getChildren().addAll(tile5.createContent(five), button2);

            tilesPane2.getChildren().add(base2);

            singleGame5 = new Scene(tilesPane2);
        }

        //size: THREE
        {
            StackPane tilesPane = new StackPane();
            Button button1 = new Button("Back to menu");
            button1.setTranslateX(1000);
            button1.setTranslateY(100);
            button1.setOnAction(event -> {
                window.setScene(menuScene);
            });

            Pane base = new Pane();
            base.setPrefSize(1200, 750);

            base.getChildren().addAll(tile3.createContent(three), button1);

            tilesPane.getChildren().add(base);

            singleGame3 = new Scene(tilesPane);
        }
        window.setTitle("Tic-Tac-Toe");
        window.setResizable(false);
        window.setScene(menuScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
