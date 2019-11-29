package tom2.ch01.optional;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) throws IOException {
        var context = new String(Files.readAllBytes(Path.of("/home/home-pc/Рабочий стол/java_core_with_horstmann_tom1/tom1/src/tom2/ch01/streams/file.txt")), StandardCharsets.UTF_8);
        List<String> wordList = List.of(context.split("\\PL+"));

        Optional<String> optionalValue = wordList.stream()
                .filter(s -> s.contains("of"))
                .findFirst();
        System.out.println(optionalValue.orElse("No word") + " contains of");

        Optional<String> optionalString = Optional.empty();
        String result = optionalString.orElse("N/A");
        System.out.println("result: " + result);
        result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
        System.out.println("result: " + result);

        try {

            result = optionalString.orElseThrow(IllegalStateException::new);
            System.out.println("result :" + result);
        }catch ( Throwable t ){
            t.printStackTrace();
        }

        var optionalValues = wordList.stream()
                .filter(s -> s.contains("Now"))
                .findFirst();
        optionalValues.ifPresent(s -> System.out.println(s + " contains Now"));

        var results = new HashSet<String>();
        optionalValues.ifPresent(results::add);
        Optional<Boolean> added = optionalValues.map(results::add);
        System.out.println(added);


        System.out.println(inverse(4.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(-1.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(0.0).flatMap(OptionalTest::squareRoot));
        Optional<Double> result2 = Optional.of(-4.0)
                .flatMap(OptionalTest::inverse).flatMap(OptionalTest::squareRoot);
        System.out.println(result2);
    }

    public static Optional<Double> inverse (Double x ){
        return x == 0? Optional.empty() : Optional.of(1/x);
    }

    public static Optional<Double> squareRoot(Double x)
    {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
