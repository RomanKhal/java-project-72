package hexlet.code.controller;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.ChecksRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.sql.SQLException;
import java.util.Objects;


public class CheckController {
    public static void create(Context context) throws SQLException {
        var id = context.pathParamAsClass("id", Long.class).get();
        Url url = UrlsRepository.find(id).orElseThrow(() -> new NotFoundResponse("Not found url id = " + id));
        HttpResponse<String> getRequest = Unirest.get(url.getName()).asString();
        Document parse = Jsoup.parse(getRequest.getBody());
        int status = getRequest.getStatus();
        String title = parse.title();
        String h1 = Objects.requireNonNull(parse.selectFirst("h1")).text();
        String description = Objects
                .requireNonNull(parse.getElementsByAttributeValue("name", "description"))
                .attr("content");
        UrlCheck urlCheck = new UrlCheck(status, title, h1, description, id);
        ChecksRepository.save(urlCheck);
        context.sessionAttribute("flash", "Страница успешно проверена");
        context.redirect(NamedRoutes.url(id));
    }
}
