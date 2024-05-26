package gg.jte.generated.ondemand;
import hexlet.code.dto.UrlsPage;
import hexlet.code.util.NamedRoutes;
public final class JteUrlsGenerated {
	public static final String JTE_NAME = "Urls.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,6,6,8,8,9,9,9,10,10,14,20,20,22,23,23,23,23,23,23,23,23,23,23,23,23,24,24,24,26,26,30,30,30,30,30,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"mx-auto p-4 py-md-5\">\n        ");
				if (page.getFlash() != null) {
					jteOutput.writeContent("\n            <p> ");
					jteOutput.setContext("p", null);
					jteOutput.writeUserContent(page.getFlash());
					jteOutput.writeContent("</p>\n        ");
				}
				jteOutput.writeContent("\n        <table class=\"table\">\n            <thead>\n            <tr>\n");
				jteOutput.writeContent("\n                <td>name</td>\n                <td>createdAt</td>\n            </tr>\n            </thead>\n            <tbody>\n            ");
				for (var url : page.getUrls()) {
					jteOutput.writeContent("\n                <tr>\n");
					jteOutput.writeContent("\n                    <td><a");
					var __jte_html_attribute_0 = NamedRoutes.url(url.getId());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a></td>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getCreatedAt().toString());
					jteOutput.writeContent("</td>\n                </tr>\n            ");
				}
				jteOutput.writeContent("\n            </tbody>\n        </table>\n    </div>\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
