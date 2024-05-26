package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

public class AppTest {
    Javalin app;

    @BeforeEach
    public final void setApp() throws SQLException, IOException {
        app = App.getApp();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.home());
            Assertions.assertEquals(response.code(), 200);
        });
    }

    @Test
    public void testAddUrl() throws SQLException {
        Url url = new Url("http://www.example.com:8000/1111");
        UrlsRepository.save(url);
        JavalinTest.test(app, (server, client) ->{
            var response = client.get(NamedRoutes.url(url.getId()));
            Assertions.assertEquals(response.code(), 200);
        });
    }

    @Test
    public void testUrlNotFound() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.url(100L));
            Assertions.assertEquals(response.code(), 404);
        });
    }

    @Test
    public void testUrls() {
        JavalinTest.test(app, (server, client) -> {
            Response response = client.get(NamedRoutes.urls());
            Assertions.assertEquals(response.code(), 200);
        });
    }
}
