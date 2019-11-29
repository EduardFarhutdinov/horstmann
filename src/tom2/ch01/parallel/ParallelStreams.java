package tom2.ch01.parallel;

import java.io.IOException;
import java.lang.invoke.StringConcatException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ParallelStreams {
    public static void main(String[] args) throws IOException {
//        var contents = new String (Files.readAllBytes(Paths.get("/home/home-pc/Рабочий стол/java_core_with_horstmann_tom1/tom1/src/tom2/ch01/parallel/file.txt")), StandardCharsets.UTF_8);
//        var contents = new String (Files.readAllBytes(Paths.get("/home/home-pc/Рабочий стол/java_core_with_horstmann_tom1/tom1/src/tom2/ch01/collecting/alice30.txt")), StandardCharsets.UTF_8);
//        List<String> wordList = List.of(contents.split("\\PL+"));
//
//        // Very bad code ahead
//        var shortWords = new int[100];
//        wordList.parallelStream().forEach(s ->
//        {
//            if (s.length() < 10) shortWords[s.length()]++;
//        });
//        System.out.println(Arrays.toString(shortWords));
//
//        Map<Integer,Long> shortWordCounts = wordList.parallelStream()
//                .filter(s -> s.length() <3)
//                .collect(groupingBy(String::length, counting()));
//        System.out.println(shortWordCounts.entrySet());
//
//        Map<Integer,List<String>> result = wordList.parallelStream().collect(
//                Collectors.groupingByConcurrent(String::length)
//        );
//
//        System.out.println(result.entrySet());

        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
       String result = letters.stream()
                .reduce("",String::concat);
        System.out.println(result);
    }
}
