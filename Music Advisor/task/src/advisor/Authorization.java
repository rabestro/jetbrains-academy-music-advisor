package advisor;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Authorization implements Runnable {
    @Override
    public void run() {
        try {
            final var server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
