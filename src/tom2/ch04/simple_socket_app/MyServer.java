package tom2.ch04.simple_socket_app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(3333);
        Socket s = ss.accept();

        DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String strOne="";
        String strTwo="";
        Boolean check = false;
        while (!check){
            strOne = dataInputStream.readUTF();
            System.out.println("Client says: " + strOne);
            strTwo = bufferedReader.readLine();
            if(strTwo.equals("stop")){
                check = true;
                break;}
            else {
                dataOutputStream.writeUTF(strTwo);
                dataOutputStream.flush();
            }
        }
        dataInputStream.close();
        s.close();
        ss.close();


    }
}
