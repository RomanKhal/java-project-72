package gg.jte.generated.ondemand;
import hexlet.code.util.NamedRoutes;
public final class JtecreateUrlGenerated {
	public static final String JTE_NAME = "createUrl.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,2,2,4,4,6,6,6,6,6,6,6,6,6,15,15,15,15,15,15,15,15};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"mx-auto p-4 py-md-5\">\n        <form");
				var __jte_html_attribute_0 = NamedRoutes.urls();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\">\n            <div class=\"mb-3\">\n                <label for=\"exampleURL\" class=\"form-label\">URL</label>\n                <input type=\"url\" name=\"name\" class=\"form-control\" id=\"exampleURL\" aria-describedby=\"URLHelp\">\n                <div id=\"URLHelp\" class=\"form-text\">Enter your URL here.</div>\n            </div>\n            <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\n        </form>\n    </div>\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
