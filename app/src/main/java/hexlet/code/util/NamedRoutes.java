package hexlet.code.util;

public class NamedRoutes {
    public static String home() {
        return "/";
    }

    public static String urls() {
        return "/urls";
    }

    public static String url(Long id) {
        return url(String.valueOf(id));
    }
    public static String url(String id) {
        return "/urls/" + id;
    }
}
