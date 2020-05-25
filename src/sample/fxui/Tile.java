package sample.fxui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Arrays;

public class Tile extends StackPane {
    static String[][] board3 = new String[3][3];
    static String[][] board10 = new String[10][10];
    static String[][] board15 = new String[15][15];
    int tilesCount;
    int i;
    int j;

    static String currentPlayerSign = "X";
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
                // add some kind of id
                tile.i = i;
                tile.j = j;
            }
        }
        return root;
    }

    public Tile(int tilesCount) {
        double boardSize = 750.0 / tilesCount;
        this.tilesCount = tilesCount;
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
                    // update board w id
                    tileTextToArray();
                    System.out.println(Arrays.deepToString(board3));
                }

            }

        });
        // Glow glow = new Glow();

        setOnMouseEntered(event -> {
            //glow.setLevel(0.9);
            //setEffect(glow);
            border.setFill(Color.WHITESMOKE.brighter());
        });

        setOnMouseExited(event -> {
            //glow.setLevel(0);
            //setEffect(glow);
            border.setFill(Color.WHITESMOKE);
        });
    }


    private void draw() {
        text.setText(currentPlayerSign);
    }

    private void changePlayer() {
        if (currentPlayerSign == "X") {
            currentPlayerSign = "O";
        } else {
            currentPlayerSign = "X";
        }
    }

    private void tileTextToArray() {
        switch (tilesCount) {
            case 3:
                board3[i][j] = this.text.getText();
                break;
            case 10:
                board10[i][j] = this.text.getText();
                break;
            case 15:
                board15[i][j] = this.text.getText();
                break;
        }
    }
}
