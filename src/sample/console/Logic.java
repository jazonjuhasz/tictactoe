package sample.console;

public class Logic implements methods {

    public int boardSize;
    public char[][] board;
    public char playerChar = 'X';

    public Logic(int boardSize) {
        this.boardSize = boardSize;
        this.board = new char[boardSize][boardSize];
        makeBoard();
    }

    @Override
    public void makeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' ';
            }
        }
        for(int i = 0; i < boardSize; i++) {
            board[0][i] = (char) (i + '0');
            board[i][0] = (char) (i + '0');
        }

    }

    @Override
    public void print() {
        System.out.println("-----------------------------------------");
        for (int i = 0; i < boardSize; i++) {
            System.out.print("| ");

            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-----------------------------------------");
        }
        System.out.println("Player " + playerChar + "'s turn");

    }

    @Override
    public void changePlayer() {
        switch (playerChar) {
            case 'O':
                playerChar = 'X';
                break;
            case 'X':
                playerChar = 'O';
                break;
        }
    }

    @Override
    public boolean isBoardFull() {
        return false;
    }

    @Override
    public boolean doWeHaveAWinner() {
        return false;
    }

    @Override
    public boolean winnerRows() {
        return false;
    }

    @Override
    public boolean winnerCols() {
        return false;
    }

    @Override
    public boolean winnerCross() {
        return false;
    }

    @Override
    public boolean winnerCheckerAssistant(char i1, char i2, char i3) {
        return false;
    }
}
