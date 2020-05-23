package sample.console;

import sample.console.Logic;

import java.util.Scanner;

public class nonFXMain {
    public static void main(String[] args) {
        Logic logic = new Logic(4);
        logic.print();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int i = Integer.parseInt(scanner.nextLine());
            int j = Integer.parseInt(scanner.nextLine());
            logic.board[i][j] = logic.playerChar;
            logic.changePlayer();
            logic.print();
        }

    }
}
