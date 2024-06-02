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
import org.junit.jupiter.api.*;

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
        serverMock.enqueue(new MockResponse().setBody(getResource("mockFile.html")).setResponseCode(200));
        serverMock.start();
        testUrl = serverMock.url("/").toString();
    }

    @AfterAll
    public static void stopServer() throws IOException {
        serverMock.shutdown();
    }

    @BeforeEach
    public void setApp() throws SQLException, IOException {
        app = App.getApp();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.home());
            Assertions.assertEquals(200, response.code());
        });
    }

    @Test
    public void testAddLink() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.post(NamedRoutes.urls(), "name=" + testUrl);
            Assertions.assertEquals(200, response.code());
            Assertions.assertTrue(UrlsRepository.isInDb(testUrl));
        });
    }

    @Test
    public void testAddUrl() throws SQLException {
        Url current = new Url(testUrl);
        UrlsRepository.save(current);
        JavalinTest.test(app, (server, client) -> {
            Response response = client.get(NamedRoutes.url(1L));
            Assertions.assertEquals(200, response.code());
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
            var response = client.post(NamedRoutes.check(1L));
            Assertions.assertEquals(200, response.code());
            List<UrlCheck> index = ChecksRepository.index(1L);
            Assertions.assertFalse(index.isEmpty());
            UrlCheck urlCheck = index.get(0);
            Assertions.assertEquals("Some h1", urlCheck.getH1());
            Assertions.assertEquals("some description", urlCheck.getDescription());
            Assertions.assertEquals("Some title", urlCheck.getTitle());
        });
    }
}
