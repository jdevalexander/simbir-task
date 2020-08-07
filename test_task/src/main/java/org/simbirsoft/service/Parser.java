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

/**
 * Parser - класс для обработки web страниц, текстовых файлов
 */
public class Parser {

    private static final Logger logger = LogManager.getLogger("parser");

    /**
     * Подсчет кол-ва слов в тексте, сохраненной web страницы в виде файла
     * @param fileName Имя файла
     */
    public static void countWords(String fileName) {

        logger.info("Start parsing file " + fileName);

        try {
            //Парсинг файла
            Document doc = Jsoup.parse(new File(fileName),
                    StandardCharsets.UTF_8.name()
            );

            //Вытаскиваем тескт из html ддокумента
            String textNodes = doc.body().text();

            //разделяем слова в тексте с помощью разделителей
            Map<String, Long> collect = Arrays.stream(textNodes.split("[ ,.\"!?;:()\\[\\]\\n\\r\\t]"))
                    .filter(s -> !s.equals(""))
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

            //выводим расчитанные данные
            collect.forEach((word, num) -> logger.info(word + "-" + num));

            logger.info("Done parsing file " + fileName);

        } catch (IOException e) {
            logger.error("Error while count words in the file " + fileName);
        }

    }

}
