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
            singleGame10,
            singleGame15;

    int three = 3;
    int ten = 10;
    int fifteen = 15;

    Tile tile3 = new Tile(three);
    Tile tile10 = new Tile(ten);
    Tile tile15 = new Tile(fifteen);

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        int menuBtnSizeX = 140;
        int menuBtnSizeY = 60;

        //main menu
        {
            StackPane menu = new StackPane();

            Pane menuBase = new Pane();
            menuBase.setPrefSize(600, 600);

            Buttons smallGameButton = new Buttons(menuBtnSizeX, menuBtnSizeY);
            smallGameButton.createButton(smallGameButton);
            smallGameButton.text.setText("small");
            smallGameButton.setTranslateX(120);
            smallGameButton.setTranslateY(80);

            Buttons midGameButton = new Buttons(menuBtnSizeX, menuBtnSizeY);
            midGameButton.createButton(midGameButton);
            midGameButton.text.setText("medium");
            midGameButton.setTranslateX(120);
            midGameButton.setTranslateY(180);

            Buttons largeGameButton = new Buttons(menuBtnSizeX, menuBtnSizeY);
            largeGameButton.createButton(midGameButton);
            largeGameButton.text.setText("large");
            largeGameButton.setTranslateX(120);
            largeGameButton.setTranslateY(280);

            menuBase.getChildren().addAll(smallGameButton, midGameButton, largeGameButton);
            menu.getChildren().add(menuBase);
            menuScene = new Scene(menu);

            smallGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame3));
            midGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame10));
            largeGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame15));
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

        //size: TEN
        {
            StackPane midMapStackPane = new StackPane();
            Button button2 = new Button("Back to menu");
            button2.setTranslateX(1000);
            button2.setTranslateY(100);
            button2.setOnAction(event -> {
                window.setScene(menuScene);
            });

            Pane midMapPane = new Pane();
            midMapPane.setPrefSize(1200, 750);

            midMapPane.getChildren().addAll(tile10.createContent(ten), button2);

            midMapStackPane.getChildren().add(midMapPane);

            singleGame10 = new Scene(midMapStackPane);
        }

        //size: FIFTEEN
        {
            StackPane largeMapStackPane = new StackPane();
            Button button3 = new Button("Back to menu");
            button3.setTranslateX(1000);
            button3.setTranslateY(100);
            button3.setOnAction(event -> {
                window.setScene(menuScene);
            });

            Pane largeMapPane = new Pane();
            largeMapPane.setPrefSize(1200, 750);

            largeMapPane.getChildren().addAll(tile15.createContent(fifteen), button3);

            largeMapStackPane.getChildren().add(largeMapPane);

            singleGame15 = new Scene(largeMapStackPane);
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
