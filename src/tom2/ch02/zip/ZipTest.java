package tom2.ch02.zip;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipTest {
    public static void main(String[] args) throws IOException {
        String zipname ="/home/home-pc/Рабочий стол/java_core_with_horstmann_tom1/tom1/src/tom2/ch02/zip/123.zip";
        showContents2(zipname);
    }
    public static void showContent1 (String zipname) throws IOException {
        try(var zin = new ZipInputStream(new FileInputStream(zipname)))
        {
            ZipEntry entry ;

            while ((entry = zin.getNextEntry())!= null){
                System.out.println(entry.getName());
                var in = new Scanner(zin, StandardCharsets.UTF_8);
                while (in.hasNextLine()){
                    System.out.println(" " + in.nextLine());

                    zin.closeEntry();
                }
            }
        }
    }
    public static void showContents2(String zipname) throws IOException
    {
        FileSystem fs = FileSystems.newFileSystem(Paths.get(zipname), null);
        Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<Path>()
        {
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs)
                    throws IOException
            {
                System.out.println(path);
                for (String line : Files.readAllLines(path, Charset.forName("UTF-8")))
                    System.out.println("   " + line);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
