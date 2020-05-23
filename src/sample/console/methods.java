package sample.console;

public interface methods {
    public void makeBoard();
    public void print();
    public void changePlayer();
    public boolean isBoardFull();
    public boolean doWeHaveAWinner();
    public boolean winnerRows();
    public boolean winnerCols();
    public boolean winnerCross();
    public boolean winnerCheckerAssistant(char i1, char i2, char i3);
}
