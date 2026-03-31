package utils;

public class Config {

    public static String BASE_URL =
        System.getenv().getOrDefault("BASE_URL", "http://localhost:3000");
}
