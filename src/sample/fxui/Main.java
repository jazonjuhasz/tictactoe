package sample.fxui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;


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
        int menuWidth = 750;
        int menuHeight = 750;
        int menuBtnWidth = 260;
        int menuBtnHeight = 100;

        double btnMiddleX = (double)(menuWidth - menuBtnWidth) / 2;

        //main menu, buttons
        {
            StackPane menu = new StackPane();

            Pane menuBase = new Pane();
            menuBase.setPrefSize(menuWidth, menuHeight);

            Buttons smallGameButton = new Buttons(menuBtnWidth, menuBtnHeight);
            smallGameButton.createButton(smallGameButton);
            smallGameButton.text.setText("small");
            smallGameButton.setTranslateX(btnMiddleX);
            smallGameButton.setTranslateY(100);

            Buttons midGameButton = new Buttons(menuBtnWidth, menuBtnHeight);
            midGameButton.createButton(midGameButton);
            midGameButton.text.setText("medium");
            midGameButton.setTranslateX(btnMiddleX);
            midGameButton.setTranslateY(250);

            Buttons largeGameButton = new Buttons(menuBtnWidth, menuBtnHeight);
            largeGameButton.createButton(midGameButton);
            largeGameButton.text.setText("large");
            largeGameButton.setTranslateX(btnMiddleX);
            largeGameButton.setTranslateY(400);

            Buttons exitButton = new Buttons(menuBtnWidth, menuBtnHeight);
            exitButton.createButton(exitButton);
            exitButton.text.setText("exit");
            exitButton.setTranslateX(btnMiddleX);
            exitButton.setTranslateY(600);
            exitButton.changeBorderColor(exitButton.border, Color.RED);

            menuBase.getChildren().addAll(
                    smallGameButton,
                    midGameButton,
                    largeGameButton,
                    exitButton
            );

            smallGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame3));
            midGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame10));
            largeGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame15));
            exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event -> System.exit(0)));


            menu.getChildren().add(menuBase);
            menuScene = new Scene(menu);
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
