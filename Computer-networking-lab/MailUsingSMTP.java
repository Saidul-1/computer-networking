import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import javax.net.ssl.*;

class MailUsingSMTP {
    private static DataOutputStream dos;
    private static BufferedReader br;

    public static void main(String args[]) throws Exception{
        String user = "s2110776106@ru.ac.bd";
        Scanner tmp = new Scanner(System.in);
        String pass = tmp.nextLine();
        String username = new String(Base64.getEncoder().encode(user.getBytes()));
        String password = new String(Base64.getEncoder().encode(pass.getBytes()));

        SSLSocket sckt = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);
        
        dos = new DataOutputStream(sckt.getOutputStream());
        br = new BufferedReader(new InputStreamReader(sckt.getInputStream()));

        send("EHLO smtp.gmail.com\r\n");
        readServerResponse(9);

        send("AUTH LOGIN\r\n");
            readServerResponse(1);

        send(username + "\r\n");
            readServerResponse(1);

        send(password + "\r\n");
            readServerResponse(1);

        send("MAIL FROM:<s2110776106@ru.ac.bd>\r\n");
            readServerResponse(1);

        send("RCPT TO:<saidul@gmail.com>\r\n");
            readServerResponse(1);

        send("DATA\r\n");
            readServerResponse(1);

        
        send("FROM: s211077610@ru.ac.bd\r\n");
        send("TO: saidul@gmail.com\r\n");
        send("Subject: 3Y2S2023\r\n");
        send("211077610\n" + LocalDateTime.now() + ".\r\n");
        send(".\r\n");
            readServerResponse(1);

        send("QUIT\r\n");
            readServerResponse(1);
    }
    private static void send(String s) throws Exception {
        dos.writeBytes(s);
        Thread.sleep(1000);
        System.out.println("CLIENT: " + s);
    }
    private static void readServerResponse(int lines) throws Exception {
        for (int i = 0; i < lines; i++) {
            System.out.println("Server: " + br.readLine());
        }
    }
}
