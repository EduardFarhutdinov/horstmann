package tom2.ch04;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SocketTest {

    public static void main(String[] args) throws IOException {
        var s = new Socket("time-a.nist.gov",133);
        s.setSoTimeout(10000);

        try { InputStream in = s.getInputStream();

            try(var sc = new Scanner(in,StandardCharsets.UTF_8)){
                while (sc.hasNextLine()){
                    String line = sc.nextLine();
                    System.out.println(line);
                }
            }
        }catch ( SocketTimeoutException e ){
            e.printStackTrace();
        }
//        try(var s = new Socket("time-a.nist.gov",133);
//            var in = new Scanner(s.getInputStream(), StandardCharsets.UTF_8)){
//            while (in.hasNextLine()){
//                String line = in.nextLine();
//                System.out.println(line);
//            }
//        }
    }
}
