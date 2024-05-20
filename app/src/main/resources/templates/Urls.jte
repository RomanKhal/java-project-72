@import hexlet.code.dto.UrlsPage
@import hexlet.code.util.NamedRoutes
@param UrlsPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        @if(page.getFlash() != null)
            <p> ${page.getFlash()}</p>
        @endif
        <table class="table">
            <thead>
            <tr>
<%--                <td>id</td>--%>
                <td>name</td>
                <td>createdAt</td>
            </tr>
            </thead>
            <tbody>
            @for(var url : page.getUrls())
                <tr>
<%--                    <td>${url.getId()}</td>--%>
                    <td><a href="${NamedRoutes.url(url.getId())}">${url.getName()}</a></td>
                    <td>${url.getCreatedAt().toString()}</td>
                </tr>
            @endfor
            </tbody>
        </table>
    </div>
`
)