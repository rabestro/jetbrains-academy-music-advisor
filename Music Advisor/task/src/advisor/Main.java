package advisor;

import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.entering(Main.class.getName(), "main", args);
        new Authorization().run();
        log.exiting(Main.class.getName(), "main");
    }

}
