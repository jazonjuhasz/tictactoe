package sample.fxui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import static sample.fxui.Tile.createContent;


public class Main extends Application {
    Stage window;
    Scene menuScene,
            singleGame3,
            singleGame10,
            singleGame15;

    int smallMapSize = 3;
    int medMapSize = 10;
    int largeMapSize = 15;

    int menuWidth = 750;
    int menuHeight = 750;
    int menuBtnWidth = 260;
    int menuBtnHeight = 100;
    double btnMiddleX = (double) (menuWidth - menuBtnWidth) / 2;

    private void menuBtnCreator(Buttons name, String text, int heightOnPane) {
        //maybe should use a map to store buttons objects
        //but it would be only one line less per button
        //meh..
        //maybe w a matrix w params and a foreach......
        name.createButton(name);
        name.text.setText(text);
        name.setTranslateX(btnMiddleX);
        name.setTranslateY(heightOnPane);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        //main menu, buttons
        {
            StackPane menu = new StackPane();
            Pane menuBase = new Pane();
            menuBase.setPrefSize(menuWidth, menuHeight);

            // main page
            Buttons singleplayerBtn = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(singleplayerBtn, "singleplayer", 100);

            Buttons multiPlayerBtn = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(multiPlayerBtn, "multiplayer", 250);

            Buttons exitButton = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(exitButton, "exit", 600);
            exitButton.changeBorderColor(exitButton.border, Color.RED);

            // singleplayer page
            Buttons smallGameButton = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(smallGameButton, "small", 100);

            Buttons midGameButton = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(midGameButton, "medium", 250);

            Buttons largeGameButton = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(largeGameButton, "large", 400);

            Buttons backBtn = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(backBtn, "back", 600);

            // multiplayer page
            Buttons createGameBtn = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(createGameBtn, "create", 100);

            Buttons joinGameBtn = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(joinGameBtn, "join", 250);

            Buttons hotSeatGameBtn = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(hotSeatGameBtn, "hot-seat", 400);

            menuBase.getChildren().addAll(
                    singleplayerBtn,
                    multiPlayerBtn,
                    exitButton
            );


            // singleplayer (click) for each
            singleplayerBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> menuBase.getChildren().removeAll(
                            singleplayerBtn,
                            multiPlayerBtn,
                            exitButton
                    ));
            singleplayerBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> menuBase.getChildren().addAll(
                            smallGameButton,
                            midGameButton,
                            largeGameButton,
                            backBtn
                    ));
            backBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> menuBase.getChildren().removeAll(
                            smallGameButton,
                            midGameButton,
                            largeGameButton,
                            joinGameBtn,
                            createGameBtn,
                            hotSeatGameBtn,
                            backBtn
                    ));
            backBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> menuBase.getChildren().addAll(
                            singleplayerBtn,
                            multiPlayerBtn,
                            exitButton
                    ));

            // multiplayer (click) for each
            multiPlayerBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> menuBase.getChildren().removeAll(
                            singleplayerBtn,
                            multiPlayerBtn,
                            exitButton
                    ));
            multiPlayerBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> menuBase.getChildren().addAll(
                            createGameBtn,
                            joinGameBtn,
                            hotSeatGameBtn,
                            backBtn
                    ));


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

            base.getChildren().addAll(createContent(smallMapSize), button1);

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

            midMapPane.getChildren().addAll(createContent(medMapSize), button2);

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

            largeMapPane.getChildren().addAll(createContent(largeMapSize), button3);

            largeMapStackPane.getChildren().add(largeMapPane);

            singleGame15 = new Scene(largeMapStackPane);
        }

        // let's roll!
        window.setTitle("Tic-Tac-Toe");
        window.setResizable(false);
        window.getIcons().add(new Image("tttico.png"));
        window.setScene(menuScene);
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
