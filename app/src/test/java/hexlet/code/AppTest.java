package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.ChecksRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AppTest {
    Javalin app;
    static MockWebServer serverMock;
    static String testUrl;

    private static String getResource(String fileName) throws IOException {
        try (InputStream in = AppTest.class.getResourceAsStream("/fixtures/" + fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            return br.lines().collect(Collectors.joining("\n"));
        }
    }

    @BeforeAll
    public static void startServer() throws IOException {
        serverMock = new MockWebServer();
        serverMock.start();
        testUrl = serverMock.url("/mock").toString();

    }

    @AfterAll
    public static void stopServer() throws IOException {
        serverMock.shutdown();
    }

    /**
     *Инстанс сервера и заглушка.
     */
    @BeforeEach
    public void setApp() throws SQLException, IOException {
        app = App.getApp();
        serverMock.enqueue(new MockResponse().setBody(getResource("mockFile.html")).setResponseCode(200));
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.home());
            Assertions.assertEquals(200, response.code());
        });
    }

    @Test
    public void testAddUrl() {
        var actual = "url=" + testUrl;
        var expected = testUrl.replace("/mock", "");
        JavalinTest.test(app, (server, client) -> {
            try (var response = client.post(NamedRoutes.urls(), actual)) {
                Assertions.assertEquals(200, response.code());
                Assertions.assertTrue(UrlsRepository.search(expected).isPresent());
            }
        });
    }

    @Test
    public void testUrlCheck() throws SQLException {
        Url current = new Url(testUrl);
        long id = UrlsRepository.save(current);
        JavalinTest.test(app, (server, client) -> {
            try (Response response = client.post(NamedRoutes.check(id))) {
                Assertions.assertEquals(200, response.code());
                Assertions.assertEquals("Some title", ChecksRepository.index(id).get(0).getTitle());
            }
        });
    }

    @Test
    public void testUrlNotFound() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.url(1L));
            Assertions.assertEquals(404, response.code());
        });
    }

    @Test
    public void testUrls() {
        JavalinTest.test(app, (server, client) -> {
            Response response = client.get(NamedRoutes.urls());
            Assertions.assertEquals(200, response.code());
        });
    }

    @Test
    public void testMock() throws SQLException {
        UrlsRepository.save(new Url(testUrl));
        JavalinTest.test(app, (server, client) -> {
            try (var response = client.post(NamedRoutes.check(1L))) {
                Assertions.assertEquals(200, response.code());
                List<UrlCheck> index = ChecksRepository.index(1L);
                Assertions.assertFalse(index.isEmpty());
                UrlCheck urlCheck = index.get(0);
                Assertions.assertEquals("Some h1", urlCheck.getH1());
                Assertions.assertEquals("some description", urlCheck.getDescription());
                Assertions.assertEquals("Some title", urlCheck.getTitle());
            }
        });
    }
}
