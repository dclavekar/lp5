import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Date;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server
            for (int i = 0; i < 3; i++) {
                Socket socket = new Socket("localhost", 12345);

                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                long clientTime = System.currentTimeMillis();

                outputStream.writeLong(clientTime);

                String acknowledgment = inputStream.readUTF();
                System.out.println(acknowledgment);

                socket.close();
            }

            Socket timeSocket = new Socket("localhost", 12345);

            DataInputStream timeInputStream = new DataInputStream(timeSocket.getInputStream());

            long averageTime = timeInputStream.readLong();

            long adjustedTime = System.currentTimeMillis() + (averageTime - System.currentTimeMillis());

            System.out.println("Adjusted time: " + adjustedTime);

            timeSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

