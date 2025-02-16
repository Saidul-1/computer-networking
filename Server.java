import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("Server is running...");

        Socket s1 = ss.accept();
        DataInputStream input = new DataInputStream(s1.getInputStream());
        DataOutputStream output = new DataOutputStream(s1.getOutputStream());

        while (true) { 
            String message = input.readUTF();
            System.out.println("Client: " + message);
            if(message.equalsIgnoreCase("hi")) {
                output.writeUTF("Hello!");
            }
            else if(message.equalsIgnoreCase("ok")) {
                for(int i = 0; i < 9; i++) {
                    output.writeUTF("OK!");
                }
            }
            else if(message.equalsIgnoreCase("date")) {
                output.writeUTF("The current date is: " + new java.util.Date());
            }
            else if(message.equalsIgnoreCase("exit")) {
                break;
            }
            else {
                output.writeUTF(message);
            }
            output.writeUTF("over");
        }
    }
}