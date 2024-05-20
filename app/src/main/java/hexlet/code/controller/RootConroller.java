package hexlet.code.controller;

import hexlet.code.dto.BasePage;
import io.javalin.http.Context;

import static io.javalin.rendering.template.TemplateUtil.model;

public class RootConroller {
    public static void index(Context context) {
        var page = new BasePage();
        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("createUrl.jte", model("page", page));
    }
}
