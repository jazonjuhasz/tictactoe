package sample.fxui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true) {
            String message = in.readLine();
            System.out.println("Client catched a mail "+ message);
            if (message.length() < 8) {

                String[] msgArray = message.split(" ");

                int i = Integer.parseInt(msgArray[0]);
                int j = Integer.parseInt(msgArray[1]);
                Logic.tilesArray[i][j].writeAble = false;
                Logic.changePlayer();
                Logic.movesCount++;
                Logic.nextPlayerDisplay.setText(Logic.currentPlayerSign);
                Logic.tilesArray[i][j].text.setText(msgArray[2]);
                switch (Logic.tilesCount) {
                    case 3:
                        Logic.board3[i][j] = msgArray[2];
                        break;
                    case 10:
                        Logic.board10[i][j] = msgArray[2];
                        break;
                    case 15:
                        Logic.board15[i][j] = msgArray[2];
                        break;
                }
                Logic.checkWinners();
                Logic.isMyTurn = true;
            }
            if (message.equals("if you can hear me, please reset your game area")) {
                Logic.multiReset();
            }
        }
    }

    public void sendMessage(String msg) {
        out.println(msg);
        System.out.println("Client sent a msg " + msg);
        /*String response = in.readLine();
        return response;*/
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
