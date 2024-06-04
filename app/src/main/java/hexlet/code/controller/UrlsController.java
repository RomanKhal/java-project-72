package hexlet.code.controller;

import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.ChecksRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void create(Context context) throws SQLException, URISyntaxException {
        var name = context.formParamAsClass("url", String.class).get().trim();
        try {
            URL url = new URI(name).toURL();
            String protocol = url.getProtocol();
            String authority = url.getAuthority();
            String savedName = protocol + "://" + authority;
            if (UrlsRepository.search(savedName).isPresent()) throw new BadRequestResponse();
            Url current = new Url(savedName);
            UrlsRepository.save(current);
            context.sessionAttribute("flash", "Страница успешно добавлена");
            context.redirect(NamedRoutes.urls());
        } catch (MalformedURLException e) {
            context.sessionAttribute("flash", "Некорректный URL");
            context.redirect(NamedRoutes.home());
        } catch (BadRequestResponse e) {
            context.sessionAttribute("flash", "Страница уже существует");
            context.redirect(NamedRoutes.home());
        }
    }

    public static void index(Context context) throws SQLException {
        UrlsPage page = new UrlsPage(UrlsRepository.index());
        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("Urls.jte", model("page", page));
    }

    public static void show(Context context) throws SQLException {
        var id = context.pathParamAsClass("id", Long.class).get();
        Url url = UrlsRepository.find(id).orElseThrow(() -> new NotFoundResponse("Not found url id = " + id));
        List<UrlCheck> urlChecks = ChecksRepository.index(id);
        var page = new UrlPage(url, urlChecks);
        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("show.jte", model("page", page));
    }
}
