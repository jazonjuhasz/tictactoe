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

import java.io.IOException;

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
    int btnMiddleX = (menuWidth - menuBtnWidth) / 2;
    public static Server server;
    public static Client client;

    Thread serverThread = new Thread() {
        public void run() {
            Logic.isMultiplayer = true;
            Logic.isServer = true;
            server = new Server();
            try {
                server.start(8080);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    Thread clientThread = new Thread() {
        public void run() {
            Logic.isMultiplayer = true;
            Logic.isServer = false;
            Logic.isMyTurn = false;
            client = new Client();
            try {
                client.startConnection("localhost", 8080);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public static void menuBtnCreator(Button name, String text, int widthOnPane, int heightOnPane) {
        name.createButton(name);
        name.text.setText(text);
        name.setTranslateX(widthOnPane);
        name.setTranslateY(heightOnPane);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        //main menu, buttons
        {
            StackPane menu = new StackPane();
            menu.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));

            Pane menuBase = new Pane();
            menuBase.setPrefSize(menuWidth, menuHeight);

            // main page
            Button singleplayerBtn = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(singleplayerBtn, "hotseat", btnMiddleX, 100);

            Button multiPlayerBtn = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(multiPlayerBtn, "lan", btnMiddleX, 250);

            Button exitButton = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(exitButton, "exit", btnMiddleX, 600);
            exitButton.changeBorderColor(exitButton.border, Color.RED);

            // singleplayer page
            Button smallGameButton = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(smallGameButton, "small", btnMiddleX, 100);

            Button midGameButton = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(midGameButton, "medium", btnMiddleX, 250);

            Button largeGameButton = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(largeGameButton, "large", btnMiddleX, 400);

            Button backBtn = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(backBtn, "back", btnMiddleX, 600);

            // multiplayer page
            Button createGameBtn = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(createGameBtn, "create", btnMiddleX, 100);

            Button joinGameBtn = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(joinGameBtn, "join", btnMiddleX, 250);

            Button smallGameButtonLanServer = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(smallGameButtonLanServer, "small", btnMiddleX, 100);

            Button midGameButtonLanServer = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(midGameButtonLanServer, "medium", btnMiddleX, 250);

            Button largeGameButtonLanServer = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(largeGameButtonLanServer, "large", btnMiddleX, 400);

            Button smallGameButtonLanClient = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(smallGameButtonLanClient, "small", btnMiddleX, 100);

            Button midGameButtonLanClient = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(midGameButtonLanClient, "medium", btnMiddleX, 250);

            Button largeGameButtonLanClient = new Button(menuBtnWidth, menuBtnHeight);
            menuBtnCreator(largeGameButtonLanClient, "large", btnMiddleX, 400);

            menuBase.getChildren().addAll(
                    singleplayerBtn,
                    multiPlayerBtn,
                    exitButton
            );

            // hot-seat (click) for each
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
                            smallGameButtonLanServer,
                            smallGameButtonLanClient,
                            midGameButton,
                            midGameButtonLanServer,
                            midGameButtonLanClient,
                            largeGameButton,
                            largeGameButtonLanServer,
                            largeGameButtonLanClient,
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

            // lan (click) for each
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

            createGameBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> menuBase.getChildren().removeAll(
                            createGameBtn,
                            joinGameBtn
                    ));
            createGameBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> menuBase.getChildren().addAll(
                            smallGameButtonLanServer,
                            midGameButtonLanServer,
                            largeGameButtonLanServer
                    ));

            joinGameBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> menuBase.getChildren().removeAll(
                            createGameBtn,
                            joinGameBtn
                    ));
            joinGameBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> menuBase.getChildren().addAll(
                            smallGameButtonLanClient,
                            midGameButtonLanClient,
                            largeGameButtonLanClient
                    ));

            smallGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> createSmallMap("hotseat", "none"));
            smallGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame3));

            midGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> createMedMap("hotseat", "none"));
            midGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame10));

            largeGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> createLargeMap("hotseat", "none"));
            largeGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame15));

            //lan stuff
            smallGameButtonLanServer.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> createSmallMap("multi", "server"));
            smallGameButtonLanServer.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame3));

            midGameButtonLanServer.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> createMedMap("multi", "server"));
            midGameButtonLanServer.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame10));

            largeGameButtonLanServer.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> createLargeMap("multi", "server"));
            largeGameButtonLanServer.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame15));

            smallGameButtonLanClient.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> createSmallMap("multi", "client"));
            smallGameButtonLanClient.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame3));

            midGameButtonLanClient.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> createMedMap("multi", "client"));
            midGameButtonLanClient.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> window.setScene(singleGame10));

            largeGameButtonLanClient.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (event) -> createLargeMap("multi", "client"));
            largeGameButtonLanClient.addEventHandler(MouseEvent.MOUSE_CLICKED,
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

    private void createSmallMap(String mode, String role) {

        if (mode.equals("multi")) {
            if (role.equals("server")) {
                serverThread.start();
            }
            if (role.equals("client")) {
                clientThread.start();
            }
        }

        StackPane tilesPane = new StackPane();
        tilesPane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));

        Pane base = new Pane();
        base.setPrefSize(1200, 750);

        Button resetBtn = new Button(200, 60);
        menuBtnCreator(resetBtn, "reset", 875, 530);
        resetBtn.changeFontSize(32);

        Button backToMenuBtn = new Button(200, 60);
        menuBtnCreator(backToMenuBtn, "back", 875, 630);
        backToMenuBtn.changeFontSize(32);

        resetBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event) -> {
                    if (!Logic.isMultiplayer) {
                        base.getChildren().clear();
                        base.getChildren().addAll(
                                Logic.createContent(smallMapSize),
                                resetBtn,
                                backToMenuBtn
                        );
                        Logic.eraseBoard(Logic.board3);
                        Logic.nextPlayerDisplay.setText("X");
                        System.gc();
                    }
                    if (Logic.isMultiplayer) {
                        if(Logic.isServer) {
                            server.sendMessage("if you can hear me, please reset your game area");
                            Logic.multiReset();
                        }
                        if(!Logic.isServer) {
                            client.sendMessage("if you can hear me, please reset your game area");
                            Logic.multiReset();
                        }
                    }
                });

        backToMenuBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event) -> {
                    if (!Logic.isMultiplayer) {
                        base.getChildren().clear();
                        base.getChildren().addAll(
                                Logic.createContent(smallMapSize),
                                resetBtn,
                                backToMenuBtn
                        );
                        Logic.eraseBoard(Logic.board3);
                        Logic.nextPlayerDisplay.setText("X");
                        System.gc();
                    }
                    if (Logic.isMultiplayer) {
                        if(Logic.isServer) {
                            try {
                                server.sendMessage("if you can hear me, please reset your game area");
                            } catch (NullPointerException e) {
                                window.setScene(menuScene);
                            }
                            Logic.multiReset();
                        }
                        if(!Logic.isServer) {
                            try {
                                client.sendMessage("if you can hear me, please reset your game area");
                            } catch (NullPointerException e) {
                                window.setScene(menuScene);
                            }
                            Logic.multiReset();
                        }
                    }
                    window.setScene(menuScene);
                });


        base.getChildren().addAll(Logic.createContent(smallMapSize), backToMenuBtn, resetBtn);

        tilesPane.getChildren().add(base);

        singleGame3 = new Scene(tilesPane);
    }

    private void createMedMap(String mode, String role) {

        if (mode.equals("multi")) {
            if (role.equals("server")) {
                serverThread.start();
            }
            if (role.equals("client")) {
                clientThread.start();
            }
        }

        StackPane midMapStackPane = new StackPane();
        midMapStackPane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));

        Pane midMapPane = new Pane();
        midMapPane.setPrefSize(1200, 750);

        Button resetBtn = new Button(200, 60);
        menuBtnCreator(resetBtn, "reset", 875, 530);
        resetBtn.changeFontSize(32);

        Button backToMenuBtn = new Button(200, 60);
        menuBtnCreator(backToMenuBtn, "back", 875, 630);
        backToMenuBtn.changeFontSize(32);

        resetBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event) -> {
                    if (!Logic.isMultiplayer) {
                        midMapPane.getChildren().clear();
                        midMapPane.getChildren().addAll(
                                Logic.createContent(medMapSize),
                                resetBtn,
                                backToMenuBtn
                        );
                        Logic.eraseBoard(Logic.board10);
                        Logic.nextPlayerDisplay.setText("X");
                        System.gc();
                    }
                    if (Logic.isMultiplayer) {
                        if(Logic.isServer) {
                            server.sendMessage("if you can hear me, please reset your game area");
                            Logic.multiReset();
                        }
                        if(!Logic.isServer) {
                            client.sendMessage("if you can hear me, please reset your game area");
                            Logic.multiReset();
                        }
                    }
                });

        backToMenuBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event) -> {
                    if (!Logic.isMultiplayer) {
                        midMapPane.getChildren().clear();
                        midMapPane.getChildren().addAll(
                                Logic.createContent(medMapSize),
                                resetBtn,
                                backToMenuBtn
                        );
                        Logic.eraseBoard(Logic.board10);
                        Logic.nextPlayerDisplay.setText("X");
                        System.gc();
                    }
                    if (Logic.isMultiplayer) {
                        if(Logic.isServer) {
                            try {
                                server.sendMessage("if you can hear me, please reset your game area");
                            } catch (NullPointerException e) {
                                window.setScene(menuScene);
                            }
                            Logic.multiReset();
                        }
                        if(!Logic.isServer) {
                            try {
                                client.sendMessage("if you can hear me, please reset your game area");
                            } catch (NullPointerException e) {
                                window.setScene(menuScene);
                            }
                            Logic.multiReset();
                        }
                    }
                    window.setScene(menuScene);
                });

        midMapPane.getChildren().addAll(
                Logic.createContent(medMapSize),
                resetBtn,
                backToMenuBtn
        );

        midMapStackPane.getChildren().add(midMapPane);

        singleGame10 = new Scene(midMapStackPane);
    }

    private void createLargeMap(String mode, String role) {

        if (mode.equals("multi")) {
            if (role.equals("server")) {
                serverThread.start();
            }
            if (role.equals("client")) {
                clientThread.start();
            }
        }

        Pane largeMapPane = new Pane();
        StackPane largeMapStackPane = new StackPane();
        largeMapStackPane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));

        Button resetBtn = new Button(200, 60);
        menuBtnCreator(resetBtn, "reset", 875, 530);
        resetBtn.changeFontSize(32);

        Button backToMenuBtn = new Button(200, 60);
        menuBtnCreator(backToMenuBtn, "back", 875, 630);
        backToMenuBtn.changeFontSize(32);

        resetBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event) -> {
                    if (!Logic.isMultiplayer) {
                        largeMapPane.getChildren().clear();
                        largeMapPane.getChildren().addAll(
                                Logic.createContent(largeMapSize),
                                resetBtn,
                                backToMenuBtn
                        );
                        Logic.eraseBoard(Logic.board15);
                        Logic.nextPlayerDisplay.setText("X");
                        System.gc();
                    }
                    if (Logic.isMultiplayer) {
                        if(Logic.isServer) {
                            server.sendMessage("if you can hear me, please reset your game area");
                            Logic.multiReset();
                        }
                        if(!Logic.isServer) {
                            client.sendMessage("if you can hear me, please reset your game area");
                            Logic.multiReset();
                        }
                    }
                });

        backToMenuBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event) -> {
                    if (!Logic.isMultiplayer) {
                        largeMapPane.getChildren().clear();
                        largeMapPane.getChildren().addAll(
                                Logic.createContent(largeMapSize),
                                resetBtn,
                                backToMenuBtn
                        );
                        Logic.eraseBoard(Logic.board15);
                        Logic.nextPlayerDisplay.setText("X");
                        System.gc();
                    }
                    if (Logic.isMultiplayer) {
                        if(Logic.isServer) {
                            try {
                                server.sendMessage("if you can hear me, please reset your game area");
                            } catch (NullPointerException e) {
                                window.setScene(menuScene);
                            }
                            Logic.multiReset();
                        }
                        if(!Logic.isServer) {
                            try {
                                client.sendMessage("if you can hear me, please reset your game area");
                            } catch (NullPointerException e) {
                                window.setScene(menuScene);
                            }
                            Logic.multiReset();
                        }
                    }
                    window.setScene(menuScene);
                });

        largeMapPane.setPrefSize(1200, 750);

        largeMapPane.getChildren().addAll(
                Logic.createContent(largeMapSize),
                resetBtn,
                backToMenuBtn
        );

        largeMapStackPane.getChildren().add(largeMapPane);

        singleGame15 = new Scene(largeMapStackPane);
    }
}
