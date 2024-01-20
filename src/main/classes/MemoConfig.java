package main.classes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class MemoConfig {
    private static final Properties properties = new Properties();

    static {
        Optional<String> env = Optional.ofNullable(System.getenv("APP_ENV"));
        env.ifPresentOrElse(appEnv -> {
            // Gets the appropriate config file
            try (InputStream input = MemoConfig.class.getClassLoader().getResourceAsStream("resources/" + appEnv + "/config.properties")) {
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        },
        () -> {
            // Defaults to production for now since that's just the .jar
            try (InputStream input = MemoConfig.class.getClassLoader().getResourceAsStream("resources/prod/config.properties")) {
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static String getBasePath() {
        return properties.getProperty("file.path");
    }
}
