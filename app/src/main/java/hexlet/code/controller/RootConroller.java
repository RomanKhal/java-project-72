package hexlet.code.controller;

import hexlet.code.dto.BasePage;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;

import java.io.IOException;
import java.sql.SQLException;

import static hexlet.code.App.readResourceFile;
import static hexlet.code.repository.BaseRepository.dataSource;
import static io.javalin.rendering.template.TemplateUtil.model;

public class RootConroller {
    public static void index(Context context) {
        var page = new BasePage();
        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("createUrl.jte", model("page", page));
    }

    public static void reInitDb(Context context) throws SQLException, IOException {
        String sql = "DROP TABLE if EXISTS url_checks; " +
                "DROP TABLE if EXISTS urls;";
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
            sql = readResourceFile("schema.sql");
            statement.execute(sql);
        }
        context.redirect(NamedRoutes.home());
    }
}
