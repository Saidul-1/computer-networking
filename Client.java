import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket s2 = new Socket("localhost", 7777);
        System.out.println("Server is connected");
        DataInputStream input = new DataInputStream(s2.getInputStream());
        DataOutputStream output = new DataOutputStream(s2.getOutputStream());
        Scanner userInput = new Scanner(System.in);

        while(true) {
            String message = userInput.nextLine();
            output.writeUTF(message);
           
            while(true) {
                String response = input.readUTF(); 
                if(response.equalsIgnoreCase("over")) break;
                System.out.println("Server: " + response);
            }
        }
    }
}