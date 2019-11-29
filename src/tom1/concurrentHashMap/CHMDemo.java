package tom1.concurrentHashMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CHMDemo {

    public static ConcurrentHashMap<String,Long> map = new ConcurrentHashMap<>();

    public static void process(Path file){
        try(var in = new Scanner(file)){
            while (in.hasNext()){
                String word = in.next();
                map.merge(word,1L,Long::sum);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public static Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)){
            return entries.collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
//        int processors = Runtime.getRuntime().availableProcessors();
//        ExecutorService executorService = Executors.newFixedThreadPool(processors);
////        Path pathToRoot = Path.of(".");
//        Path pathToRoot = Path.of("/home/home-pc/Рабочий стол/java_core_with_horstmann_tom1/tom1/src/tom1.blockingQueueTest/td/");
//        for ( Path p : descendants(pathToRoot) )
//        {
//            if (p.getFileName().toString().endsWith(".java")){
//                executorService.execute(() -> process(p));
//            }
//        }
//            executorService.shutdown();
//            executorService.awaitTermination(10, TimeUnit.MINUTES);
//            map.forEach((k,v) -> {
//                if (v >= 10){
//                    System.out.println(k + " occurs " + v + " times");
//                }
//            });


             int[] arr ={2,2,3,4};
//            Arrays.parallelSetAll(arr, i -> i % 10);
            Arrays.parallelPrefix(arr, (x,y)-> x* y);
            Arrays.stream(arr).forEach(e -> System.out.print(e + " "));

    }
}
