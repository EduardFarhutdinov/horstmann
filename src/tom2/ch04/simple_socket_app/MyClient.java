package tom2.ch04.simple_socket_app;

import java.io.*;
import java.net.Socket;

public class MyClient {

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost",3333);
        DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String str="";
        String str2="";

        while (!str.equals("stop")){
            str = bufferedReader.readLine();
            dataOutputStream.writeUTF(str);
            dataOutputStream.flush();
            str2 = dataInputStream.readUTF();
            System.out.println("Server says: " + str2);
        }

        dataOutputStream.close();
        s.close();
    }
}
