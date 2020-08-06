package org.simbirsoft.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Reader {

    private static final Logger logger = LogManager.getLogger("reader");

    public static String savePageAsFile(String address) {

        logger.info("Start Download " + address);

        try {

            URL url = new URL(address);

            ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
            String fileName = url.getHost().replace(".", "") + ".html";
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
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
