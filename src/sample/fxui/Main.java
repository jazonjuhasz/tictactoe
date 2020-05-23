package sample.fxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.Stack;


public class Main extends Application {

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(1200, 750);
        Button button1 = new Button();
        button1.setTranslateX(1000);
        button1.setTranslateY(0);
        //button1.setMinWidth(120);
        button1.setText("Just a button");
        root.getChildren().add(button1);

        button1.setOnMouseClicked(event -> {
            //tilesCount = 10;
            button1.setText("Another");
        });

        for (int i = 0; i < tilesCount; i++) {
            for (int j = 0; j < tilesCount; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * boardSize);
                tile.setTranslateY(i * boardSize);
                root.getChildren().add(tile);
            }
        }

        return root;
    }

    private class Tile extends StackPane {
        private Text text = new Text();
        boolean writeAble = text.getText().isEmpty();

        public Tile() {
            Rectangle border = new Rectangle(boardSize, boardSize);
            border.setFill(Color.WHITESMOKE);
            border.setStroke(Color.BLACK);

            //setting mark size depending on tiles count
            switch (tilesCount) {
                case 15:
                    text.setFont(Font.font(18));
                    break;
                case 10:
                    text.setFont(Font.font(28));
                    break;
                case 5:
                    text.setFont(Font.font(36));
                    break;
                case 3:
                    text.setFont(Font.font(48));
                    break;
            }

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);


            //some effect
            setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if (writeAble) {
                        writeAble = false;
                        draw();
                        changePlayer();
                    }
                }
            });
            Glow glow = new Glow();
            setOnMouseEntered(event -> {
                glow.setLevel(0.9);
                setEffect(glow);
            });
            setOnMouseExited(event -> {
                glow.setLevel(0);
                setEffect(glow);
            });
        }


        private void draw() {
            text.setText(currentPlayerSing);
        }

        private void changePlayer() {
            if (currentPlayerSing == "X") {
                currentPlayerSing = "O";
            } else {
                currentPlayerSing = "X";
            }
        }

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setResizable(false);
        primaryStage.show();*/

        /*primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setResizable(false);
        primaryStage.show();*/

        StackPane tilesPane = new StackPane();
        tilesPane.getChildren().add(createContent());
        Scene gameScene = new Scene(tilesPane);
        primaryStage.setScene(gameScene);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    String currentPlayerSing = "X";
    int tilesCount = 15;
    // double boardSize = tilesCount * (50.0 / tilesCount);
    double boardSize = 750.0 / tilesCount;

    public static void main(String[] args) {
        launch(args);
    }
}
