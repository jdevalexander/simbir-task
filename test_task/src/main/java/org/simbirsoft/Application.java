package org.simbirsoft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simbirsoft.service.Parser;
import org.simbirsoft.service.Reader;

public class Application {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Starting application!");

        String[] pageExamples = {
                "https://www.simbirsoft.com/",
                "https://translate.yandex.ru/",
                "https://github.com/"
        };

        for (String pageExample : pageExamples) {
            String fileName = Reader.savePageAsFile(pageExample);
            if (fileName != null) {
                Parser.countWords(fileName);
            }
        }


        logger.info("End work!");

    }
}
