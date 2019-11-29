package tom2.ch01.streams;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreatingStreams  {

    public static <T> void show(String title, Stream<T> stream){
        final int SIZE = 10;
        List<T> firstElements = stream
                .limit(SIZE + 1)
                .collect(Collectors.toList());
        System.out.print(title + ": ");
        for (int i = 0; i < firstElements.size(); i++)
        {
            if (i > 0) System.out.print(", ");
            if (i < SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {

            Path path = Paths.get("//home/home-pc/Рабочий стол/java_core_with_horstmann_tom1/tom1/src/tom2/ch01/streams/file.txt");
            var contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

            Stream<String> words = Stream.of(contents.split("\\PL+")).map(String::toUpperCase).takeWhile(s -> s.length() != 0);
            show("words", words);
            Stream<String> song = Stream.of("gently", "down", "the", "stream");
            show("song", song);
            Stream<String> silence = Stream.empty();
            show("silence", silence);


        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.TEN,
                n -> n.add(BigInteger.ONE));
        show("integers", integers);
    }

}
