package sample.fxui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

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
    int btnMiddleX = (int) (menuWidth - menuBtnWidth) / 2;

    public static void menuBtnCreator(Buttons name, String text, int widthOnPane, int heightOnPane) {
        name.createButton(name);
        name.text.setText(text);
        name.setTranslateX(widthOnPane);
        name.setTranslateY(heightOnPane);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        //main menu, buttons
        {
            StackPane menu = new StackPane();
            menu.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));

            Pane menuBase = new Pane();
            menuBase.setPrefSize(menuWidth, menuHeight);

            // main page
            Buttons singleplayerBtn = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(singleplayerBtn, "hotseat", btnMiddleX, 100);

            Buttons multiPlayerBtn = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(multiPlayerBtn, "lan", btnMiddleX, 250);

            Buttons exitButton = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(exitButton, "exit", btnMiddleX, 600);
            exitButton.changeBorderColor(exitButton.border, Color.RED);

            // singleplayer page
            Buttons smallGameButton = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(smallGameButton, "small", btnMiddleX, 100);

            Buttons midGameButton = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(midGameButton, "medium", btnMiddleX, 250);

            Buttons largeGameButton = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(largeGameButton, "large", btnMiddleX, 400);

            Buttons backBtn = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(backBtn, "back", btnMiddleX, 600);

            // multiplayer page
            Buttons createGameBtn = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(createGameBtn, "create", btnMiddleX, 100);

            Buttons joinGameBtn = new Buttons(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(joinGameBtn, "join", btnMiddleX, 250);

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
                            backBtn
                    ));

            smallGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> createSmallMap());
            smallGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame3));

            midGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> createMedMap());
            midGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame10));

            largeGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> createLargeMap());
            largeGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame15));


            exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event -> System.exit(0)));

            menu.getChildren().add(menuBase);
            menuScene = new Scene(menu);
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

    private void createSmallMap() {
        StackPane tilesPane = new StackPane();
        tilesPane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));

        Pane base = new Pane();
        base.setPrefSize(1200, 750);

        Buttons resetBtn = new Buttons(200, 60);
        menuBtnCreator(resetBtn, "reset", 875, 530);
        resetBtn.changeFontSize(32);

        Buttons backToMenuBtn = new Buttons(200, 60);
        menuBtnCreator(backToMenuBtn, "back", 875, 630);
        backToMenuBtn.changeFontSize(32);

        resetBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event) -> {
                    base.getChildren().clear();
                    base.getChildren().addAll(
                            Tile.createContent(smallMapSize),
                            resetBtn,
                            backToMenuBtn
                    );
                    Tile.eraseBoard(Tile.board3);
                    Tile.nextPlayerDisplay.setText("X");
                    System.gc();
                });

        backToMenuBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event) -> {
                    window.setScene(menuScene);
                    base.getChildren().clear();
                    base.getChildren().addAll(
                            Tile.createContent(smallMapSize),
                            resetBtn,
                            backToMenuBtn
                    );
                    Tile.eraseBoard(Tile.board3);
                    System.gc();
                });


        base.getChildren().addAll(Tile.createContent(smallMapSize), backToMenuBtn, resetBtn);

        tilesPane.getChildren().add(base);

        singleGame3 = new Scene(tilesPane);
    }

    private void createMedMap() {
        StackPane midMapStackPane = new StackPane();
        midMapStackPane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));

        Pane midMapPane = new Pane();
        midMapPane.setPrefSize(1200, 750);

        Buttons resetBtn = new Buttons(200, 60);
        menuBtnCreator(resetBtn, "reset", 875, 530);
        resetBtn.changeFontSize(32);

        Buttons backToMenuBtn = new Buttons(200, 60);
        menuBtnCreator(backToMenuBtn, "back", 875, 630);
        backToMenuBtn.changeFontSize(32);

        resetBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event) -> {
                    midMapPane.getChildren().clear();
                    midMapPane.getChildren().addAll(
                            Tile.createContent(medMapSize),
                            resetBtn,
                            backToMenuBtn
                    );
                    Tile.eraseBoard(Tile.board10);
                    Tile.nextPlayerDisplay.setText("X");
                    System.gc();
                });

        backToMenuBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event) -> {
                    window.setScene(menuScene);
                    midMapPane.getChildren().clear();
                    midMapPane.getChildren().addAll(
                            Tile.createContent(medMapSize),
                            resetBtn,
                            backToMenuBtn
                    );
                    Tile.eraseBoard(Tile.board10);
                    System.gc();
                });

        midMapPane.getChildren().addAll(
                Tile.createContent(medMapSize),
                resetBtn,
                backToMenuBtn
        );

        midMapStackPane.getChildren().add(midMapPane);

        singleGame10 = new Scene(midMapStackPane);
    }

    private void createLargeMap() {
        Pane largeMapPane = new Pane();
        StackPane largeMapStackPane = new StackPane();
        largeMapStackPane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));


        Buttons resetBtn = new Buttons(200, 60);
        menuBtnCreator(resetBtn, "reset", 875, 530);
        resetBtn.changeFontSize(32);

        Buttons backToMenuBtn = new Buttons(200, 60);
        menuBtnCreator(backToMenuBtn, "back", 875, 630);
        backToMenuBtn.changeFontSize(32);

        resetBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event) -> {
                    largeMapPane.getChildren().clear();
                    largeMapPane.getChildren().addAll(
                            Tile.createContent(largeMapSize),
                            resetBtn,
                            backToMenuBtn
                    );
                    Tile.eraseBoard(Tile.board15);
                    Tile.nextPlayerDisplay.setText("X");
                    System.gc();
                });

        backToMenuBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event) -> {
                    window.setScene(menuScene);
                    largeMapPane.getChildren().clear();
                    largeMapPane.getChildren().addAll(
                            Tile.createContent(largeMapSize),
                            resetBtn,
                            backToMenuBtn
                    );
                    Tile.eraseBoard(Tile.board15);
                    System.gc();
                });

        largeMapPane.setPrefSize(1200, 750);

        largeMapPane.getChildren().addAll(
                Tile.createContent(largeMapSize),
                resetBtn,
                backToMenuBtn
        );

        largeMapStackPane.getChildren().add(largeMapPane);

        singleGame15 = new Scene(largeMapStackPane);
    }
}
