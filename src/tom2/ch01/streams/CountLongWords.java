package tom2.ch01.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class CountLongWords {
    public static void main(String[] args) throws IOException {
        var  contents  = new String (Files.readAllBytes(Path.of("/home/home-pc/Рабочий стол/java_core_with_horstmann_tom1/tom1/src/tom2/ch01/streams/file.txt")), StandardCharsets.UTF_8);
//        var  contents  = new String (Files.readAllBytes(Paths.get("/home/home-pc/Рабочий стол/java_core_with_horstmann_tom1/tom1/src/tom2/ch01/streams/file.txt")), StandardCharsets.UTF_8);
        List<String> words = List.of(contents.split("\\PL+"));

        long count = 0;

        for ( String w : words ){
            if(w.length() > 5 ){
                count++;
            }
        }
        System.out.println(count);

        count = words.stream().filter(w -> w.length() > 5).count();
        System.out.println(count);

        count = words.parallelStream().filter(w -> w.length() > 5).count();
        System.out.println(count);

//        Stream<String> wordss = Stream.of(contents.split("\\PL+")).filter(w -> w.length() >5).count();
        System.out.println(Stream.of(contents.split("\\PL+")).filter(w -> w.length() >5).count());

    }
}
