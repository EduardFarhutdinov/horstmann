package tom2.ch01.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveTypeStreams {
    public static void show(String title, IntStream stream)
    {
        final int SIZE = 1000;
        int[] firstElements = stream.limit(SIZE + 1).toArray();
        System.out.print(title + ": ");
        for (int i = 0; i < firstElements.length; i++)
        {
            if (i > 0) System.out.print(", ");
            if (i < SIZE) System.out.print(firstElements[i]);
            else System.out.print("...");
        }
        System.out.println();
    }
    public static void main(String[] args) throws IOException {
        IntStream is1 = IntStream.of(-1,2,4,56,78,343,12,-10);
        show("is1" , is1);
        IntStream is2 = IntStream.range(2, 200);
        show("is2", is2);
        IntStream is3 = IntStream.rangeClosed(5, 10);
        show("is3", is3);

        var sentence = "\uD835\uDD46 is the set of octonions.";
        System.out.println(sentence);
        IntStream codes = sentence.codePoints();
        System.out.println(codes.mapToObj(c -> String.format("%X ", c)).collect(
                Collectors.joining()));

        Path path = Paths.get("/home/home-pc/Рабочий стол/java_core_with_horstmann_tom1/tom1/src/tom2/ch01/streams/file.txt");
        var contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
//        IntStream codes = sentence.codePoints();
        Stream<String> words = Stream.of(contents.split("\\PL+"));
        IntStream is4 = words.mapToInt(String::length);
        show("is4", is4);
        Stream<Integer> integers = IntStream.range(0, 100).boxed();
        IntStream is5 = integers.mapToInt(Integer::intValue);
        show("is5", is5);
    }
}
