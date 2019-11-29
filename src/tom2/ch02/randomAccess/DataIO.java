package tom2.ch02.randomAccess;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DataIO {
    public static String readFixedString(int size, DataInput dataInput) throws IOException {
        var b = new StringBuilder(size);
        int i = 0 ;
        var done = false;

        while (!done && i < size){
            char ch = dataInput.readChar();
            i++;
            if( ch == 0)
                done = true;
            else
                b.append(ch);

        }
        int a = dataInput.skipBytes(2* (size - i) );
        System.out.println(a);
        return b.toString();

    }

    public static void writeFixedString(String s, int size, DataOutput out) throws IOException {
        for(int i = 0; i < size ; i++){
            char c = 0;
            if(i < s.length())
                c = s.charAt(i);
                out.writeChar(c);

        }
    }
}