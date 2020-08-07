package org.simbirsoft.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Читает web страницы,сохраняет в отдельный файл старницу.
 * Выполняет другие действия с Web элементами
 */
public class WebReader {

    private static final Logger logger = LogManager.getLogger("reader");

    /**
     * Сохраняет веб-страницу в виде файла
     *
     * @param address Адрес веб-страницы
     * @return Имя файла
     */
    public static String savePageAsFile(String address) {

        logger.info("Start Download " + address);

        try {

            URL url = new URL(address);

            //Считываем веб-старницу
            ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());

            //Создаём имя файла для сохранения
            String fileName = url.getHost().replace(".", "") + ".html";
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);

            //записываем в файл информацию с веб страницы
            fileOutputStream
                    .getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

            logger.info("Download Successfully " + address);
            return fileName;
        } catch (IOException e) {
            logger.error("Error while downloading! " + address);
            return null;
        }
    }


}
