package org.simbirsoft.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger("parser");

    public static void countWords(String fileName) {

        logger.info("Start parsing file " + fileName);

        try {
            Document doc = Jsoup.parse(new File(fileName),
                    StandardCharsets.UTF_8.name()
            );

            String textNodes = doc.body().text();

            Map<String, Long> collect = Arrays.stream(textNodes.split("[ ,.\"!?;:()\\[\\]\\n\\r\\t]"))
                    .filter(s -> !s.equals(""))
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

            collect.forEach((word, num) -> logger.info(word + "-" + num));

            logger.info("Done parsing file " + fileName);

        } catch (IOException e) {
            logger.error("Error while count words in the file " + fileName);
        }

    }

}
