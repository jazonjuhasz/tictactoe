package sample.fxui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    static String currentPlayerSing = "X";
    private Text text = new Text();
    boolean writeAble = text.getText().isEmpty();

    public static Parent createContent(int tilesCount) {
        double boardSize = 750.0 / tilesCount;

        Pane root = new Pane();
        root.setPrefSize(1200, 750);

        for (int i = 0; i < tilesCount; i++) {
            for (int j = 0; j < tilesCount; j++) {
                Tile tile = new Tile(tilesCount);
                tile.setTranslateX(j * boardSize);
                tile.setTranslateY(i * boardSize);
                root.getChildren().add(tile);
            }
        }

        return root;
    }

    public Tile(int tilesCount) {
        double boardSize = 750.0 / tilesCount;
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
