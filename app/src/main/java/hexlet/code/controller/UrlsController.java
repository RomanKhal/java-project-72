package hexlet.code.controller;

import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void addUrl(Context context) throws SQLException, URISyntaxException {
        var name = context.formParamAsClass("name", String.class).get();
        try {
            URL url = new URI(name).toURL();
            String path = url.getPath();
            name = url.toString().replace(path, "");
            if (UrlsRepository.isInDb(name)) throw new RuntimeException();
            Url current = new Url(name);
            UrlsRepository.save(current);
            context.sessionAttribute("flash", "Страница успешно добавлена");
            context.redirect(NamedRoutes.urls());
        } catch (MalformedURLException e) {
            context.sessionAttribute("flash", "Некорректный URL");
            context.redirect(NamedRoutes.home());
        } catch (RuntimeException e) {
            context.sessionAttribute("flash", "Страница уже существует");
            context.redirect(NamedRoutes.home());
        }
    }

    public static void index(Context context) throws SQLException {
        UrlsPage page = new UrlsPage(UrlsRepository.index());
        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("Urls.jte", model("page", page));
    }

    public static void getUrl(Context context) throws SQLException {
        var id = context.pathParamAsClass("id", Long.class).get();
        Url url = UrlsRepository.find(id);
        var page = new UrlPage(url);
        context.render("show.jte", model("page", page));
    }
}
