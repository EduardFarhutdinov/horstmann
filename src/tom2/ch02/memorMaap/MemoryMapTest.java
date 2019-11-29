package tom2.ch02.memorMaap;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

public class MemoryMapTest {
    public static long checksumInputStream(Path fileName) throws IOException {
        try(InputStream in = Files.newInputStream(fileName)){
            var crc = new CRC32();

            int c ;
            while ((c = in.read()) != -1){
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static long checksumBufferedInputStream(Path fileName) throws IOException {
        try(var in = new BufferedInputStream(Files.newInputStream(fileName))){
            var crc = new CRC32();

            int c ;

            while ((c = in.read())!= -1){
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static long checksumRandomAccessFile(Path fileName) throws IOException {
        try(var file = new RandomAccessFile(fileName.toFile(),"r")){
            long length = file.length();
            var crc = new CRC32();

            for (long i = 0;i < length;i++){
                file.seek(i);
                int c = file.readByte();
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static long checksumMappedFile(Path fileName) throws IOException{

        try(FileChannel channel = FileChannel.open(fileName)){
            var crc = new CRC32();
            int length = (int) channel.size();
            MappedByteBuffer byteBuffer =  channel.map(FileChannel.MapMode.READ_ONLY,0,length);

            for (int i = 0;i < length; i++ ){
                int c = byteBuffer.get(i);
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Input Stream:");
        long start = System.currentTimeMillis();
        Path filename = Paths.get("/home/home-pc/Рабочий стол/java_core_with_horstmann_tom1/tom1/src/tom2/ch02/memorMaap/test.txt");
        long crcValue = checksumInputStream(filename);
        long end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");

        System.out.println("Buffered Input Stream:");
        start = System.currentTimeMillis();
        crcValue = checksumBufferedInputStream(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");

        System.out.println("Random Access File:");
        start = System.currentTimeMillis();
        crcValue = checksumRandomAccessFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");

        System.out.println("Mapped File:");
        start = System.currentTimeMillis();
        crcValue = checksumMappedFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");
    }
}
