package advisor;

import java.util.Scanner;

public class Main {
    static boolean auth = false;

    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        application:
        for (; ; ) {
            final var command = scanner.nextLine();
            switch (command) {
                case "auth":
                    System.out.println("https://accounts.spotify.com/authorize?"
                            + "client_id=81bcb1ba1c224b74b5f2bed6bb185cad"
                            + "&redirect_uri=http://localhost:8080&response_type=code\n" +
                            "---SUCCESS---");
                    auth = true;
                    break;
                case "new":
                    print("---NEW RELEASES---\n" +
                            "Mountains [Sia, Diplo, Labrinth]\n" +
                            "Runaway [Lil Peep]\n" +
                            "The Greatest Show [Panic! At The Disco]\n" +
                            "All Out Life [Slipknot]");
                    break;
                case "featured":
                    print("---FEATURED---\n" +
                            "Mellow Morning\n" +
                            "Wake Up and Smell the Coffee\n" +
                            "Monday Motivation\n" +
                            "Songs to Sing in the Shower");
                    continue;
                case "categories":
                    print("---CATEGORIES---\n" +
                            "Top Lists\n" +
                            "Pop\n" +
                            "Mood\n" +
                            "Latin");
                    continue;
                case "playlists Mood":
                    print("---MOOD PLAYLISTS---\n" +
                            "Walk Like A Badass  \n" +
                            "Rage Beats  \n" +
                            "Arab Mood Booster  \n" +
                            "Sunday Stroll");
                    continue;
                case "exit":
                    System.out.println("---GOODBYE!---");
                    break application;
            }
        }
    }

    static void print(String message) {
        System.out.println(auth ? message : "Please, provide access for application.");
    }
}
