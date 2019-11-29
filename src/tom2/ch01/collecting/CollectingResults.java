package tom2.ch01.collecting;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResults {

    public static Stream<String> noVowels() throws IOException
    {
        /*
        Cодержимое файла выглядит так
        Now you don’t have to scan the loop for evidence of filtering and counting.
         */

        var contents = Files.readString(
                Paths.get("/home/home-pc/Рабочий стол/java_core_with_horstmann_tom1/tom1/src/tom2/ch01/collecting/file.txt"));
        List<String> wordList = List.of(contents.split("\\PL+"));
        Stream<String> words = wordList.stream();
        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    public static <T> void show(String label, Set<T> set)
    {
        System.out.print(label + ": " + set.getClass().getName());
        System.out.println("["
                + set.stream().limit(15).map(Object::toString).collect(Collectors.joining(", "))
                + "]");
    }



    public static void main(String[] args) throws IOException {

        Iterator<Integer> iter = Stream.iterate(0,n -> n + 1).limit(10).iterator();
        while (iter.hasNext()){
            System.out.print(iter.next());
        }
        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.print("Object array:" + numbers.toString());

        try
        {
            var number = (Integer) numbers[0]; // OK
            System.out.println("number: " + number);
            System.out.println("The following statement throws an exception:");
            var numbers2 = (Integer[]) numbers; // Throws exception
        }
        catch (ClassCastException ex)
        {
            System.out.println(ex);
        }

        Integer[] numbers3 = Stream.iterate(0, n -> n + 1)
                .limit(10)
                .toArray(Integer[]::new);
        System.out.println("Integer array: " + numbers3);


        Set<String> noVowelSet = noVowels().collect(Collectors.toSet());
        show("noVowelSet", noVowelSet);

        TreeSet<String> noVowelTreeSet = noVowels().collect(
                Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet", noVowelTreeSet);

        String result = noVowels().limit(noVowels().count()).collect(Collectors.joining(","));
        System.out.println("Joining: " + result);


        IntSummaryStatistics summary = noVowels().collect(
                Collectors.summarizingInt(String::length));
        double averageWordLength = summary.getAverage();
        double maxWordLength = summary.getMax();
        System.out.println("Average word length: " + averageWordLength);
        System.out.println("Max word length: " + maxWordLength);

        System.out.println("forEach:");
        noVowels().limit(10).forEach(System.out::println);
    }
}
