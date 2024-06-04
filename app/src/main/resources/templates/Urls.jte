@import hexlet.code.dto.UrlsPage
@import hexlet.code.util.NamedRoutes
@import hexlet.code.util.Util
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
                <td>ID</td>
                <td>Имя</td>
                <td>Последняя проверка</td>
                <td>Код ответа</td>
            </tr>
            </thead>
            <tbody>
            @for(var url : page.getUrls())
                <tr>
                    <td>${url.getId()}</td>
                    <td><a href="${NamedRoutes.url(url.getId())}">${url.getName()}</a></td>
                    <td>${Util.asString(url.getLastCheck())}</td>
                    <td>${Util.asString(url.getCode())}</td>
                </tr>
            @endfor
            </tbody>
        </table>
    </div>
`
)