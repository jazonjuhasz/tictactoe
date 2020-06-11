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
            if (message != null) {

                String[] msgArray = message.split(" ");

                int i = Integer.parseInt(msgArray[0]);
                int j = Integer.parseInt(msgArray[1]);
                Tile.tilesArray[i][j].writeAble = false;
                Tile.changePlayer();
                Tile.movesCount++;
                Tile.nextPlayerDisplay.setText(Tile.currentPlayerSign);
                Tile.tilesArray[i][j].text.setText(msgArray[2]);
                switch (Tile.tilesCount) {
                    case 3:
                        Tile.board3[i][j] = msgArray[2];
                        break;
                    case 10:
                        Tile.board10[i][j] = msgArray[2];
                        break;
                    case 15:
                        Tile.board15[i][j] = msgArray[2];
                        break;
                }
                Tile.checkWinners();
                Tile.isMyTurn = true;
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
