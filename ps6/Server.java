import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            List<Long> clientTimes = new ArrayList<>();

            System.out.println("Server is running. Waiting for clients...");
            while (clientTimes.size() < 3) {
                Socket clientSocket = serverSocket.accept();

                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

                long clientTime = inputStream.readLong();
                clientTimes.add(clientTime);

                outputStream.writeUTF("Time received by the server."+new Date(clientTime));

                clientSocket.close();
            }

            long sum = 0;
            for (long time : clientTimes) {
                sum += time;
            }
            long averageTime = sum / clientTimes.size();

            for (long clientTime : clientTimes) {
                Socket clientSocket = serverSocket.accept();

                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

                outputStream.writeLong(averageTime);

                clientSocket.close();
            }

            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

