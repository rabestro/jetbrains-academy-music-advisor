package advisor;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.logging.Logger;

/*
https://accounts.spotify.com/authorize?client_id=81bcb1ba1c224b74b5f2bed6bb185cad&redirect_uri=http://localhost:8080&response_type=code
 */
public class Authorization implements Runnable {
    private static final Logger log = Logger.getLogger(Authorization.class.getName());

    private static final String SPOTIFY_URL = "https://accounts.spotify.com/authorize";
    private static final String CLIENT_ID = "81bcb1ba1c224b74b5f2bed6bb185cad";
    private static final String REDIRECT_URI = "http%3A%2F%2Flocalhost:8080";
    private static final String URI_TEMPLATE = "{0}?client_id={1}&redirect_uri={2}&response_type=code";

    @Override
    public void run() {
        log.entering(Authorization.class.getName(), "run");

        try {
            final var server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
            server.createContext("/",
                    exchange -> {
                        String query = exchange.getRequestURI().getQuery();
                        String hello = "hello, world!\nQuery is " + query;
                        exchange.sendResponseHeaders(200, hello.length());
                        exchange.getResponseBody().write(hello.getBytes());
                        exchange.getResponseBody().close();
                    }
            );
            log.info("Server started...");
            server.start();

            final var client = HttpClient.newBuilder().build();
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080?code=test"))
                    .GET()
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.fine(response::body);
            final var uri = URI.create(MessageFormat.format(URI_TEMPLATE, SPOTIFY_URL, CLIENT_ID, REDIRECT_URI));
            log.config(uri::toString);

            request = HttpRequest.newBuilder().uri(uri).GET().build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.fine(response::body);

/*            request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .uri(URI.create("SPOTIFY_URL"))
                    .POST(HttpRequest.BodyPublishers.ofString(
                            "client_id=81bcb1ba1c224b74b5f2bed6bb185cad&redirect_uri=http://localhost:8080&response_type=code"))
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            */

            Thread.sleep(10000);
            server.stop(1);
            log.info("Server stopped...");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        log.exiting(Authorization.class.getName(), "run");
    }

}
