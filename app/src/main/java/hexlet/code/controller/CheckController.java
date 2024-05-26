package hexlet.code.controller;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.Unirest;

import java.sql.SQLException;
import java.util.Optional;

public class CheckController {
    public static void addCheck(Context context) throws SQLException {
        var id = context.pathParamAsClass("id", Long.class).get();
        Url url = UrlsRepository.find(id).orElseThrow(() -> new NotFoundResponse("Not found url id = " + id));
        Unirest.post(url.getName()).header("title", );
        context.redirect(NamedRoutes.url(id));
    }
}
