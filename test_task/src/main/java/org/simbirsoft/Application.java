package org.simbirsoft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simbirsoft.service.Parser;
import org.simbirsoft.service.WebReader;


/**
 * Главный класс для запуска приложения
 */
public class Application {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Starting application!");

        //Массив с тремя примерами для работы приложения
        String[] pageExamples = {
                "https://www.simbirsoft.com/",
                "https://translate.yandex.ru/",
                "https://github.com/"
        };

        //Перебираем все страницы из примера, сохраняем страницу и парсим из нее текст
        for (String pageExample : pageExamples) {
            String fileName = WebReader.savePageAsFile(pageExample);
            if (fileName != null) {
                Parser.countWords(fileName);
            }
        }


        logger.info("End work!");

    }
}
