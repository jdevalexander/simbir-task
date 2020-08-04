package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser {

    public static void countWords() {

        try {
            Document doc = Jsoup.parse(
                    new File("NIO_test_simbirsoft.html"),
                    Charset.forName("windows-1251").name());
//            connect("https://www.ya.ru/").get();
//            doc.charset(StandardCharsets.UTF_8);
            System.out.println("BOOOOOOOOOOOOOOOOOOOOOOOOOM - " + doc.charset());

            String textNodes = doc.body().text();

            Map<String, Long> collect = Arrays.stream(textNodes.split("[ ,.\"!?;:()\\[\\]\\n\\r\\t]"))
                    .filter(s -> !s.equals(""))
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

            collect.forEach((word, num) -> System.out.printf("%s - %d%n", word, num));

            System.out.println("Done Jsoup");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
