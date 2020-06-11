package sample.fxui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("START");
        while (true) {
            String message = in.readLine();
            System.out.println("Server catched a mail " + message);
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
        System.out.println("Server sent a msg " + msg);
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
        System.out.println("Server stopped");
    }
}
