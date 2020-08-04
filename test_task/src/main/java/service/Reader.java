package service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Reader {


    public static void savePage() {
        URL url = null;
        try {

            url = new URL("https://www.simbirsoft.com/");

            ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream("NIO_test_simbirsoft.html");
            fileOutputStream
                    .getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

            System.out.println("Download Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while downloading!");
        }
    }


}
