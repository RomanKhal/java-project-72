@import hexlet.code.dto.UrlPage
@import hexlet.code.util.NamedRoutes
@param UrlPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        @if(page.getFlash() != null)
            <p> ${page.getFlash()}</p>
        @endif
        <table class="table">
            <thead>
            <tr>
                <td>id</td>
                <td>name</td>
                <td>createdAt</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${page.getUrl().getId()}</td>
                <td>${page.getUrl().getName()}</td>
                <td>${page.getUrl().getCreatedAt().toString()}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="mx-auto p-4 py-md-5">
        <form action="${NamedRoutes.check(page.getUrl().getId())}" method="post">
            <button class="btn btn-primary" type="submit"> Check</button>
        </form>
    </div>

    @if(!page.getChecks().isEmpty())
        <div class="mx-auto p-4 py-md-5">
            <table class="table">
                <thead>
                <tr>
                    <td>id</td>
                    <td>status</td>
                    <td>title</td>
                    <td>h1</td>
                    <td>description</td>
                    <td>createdAt</td>
                </tr>
                </thead>
                <tbody>
                @for(var item : page.getChecks())
                    <tr>
                        <td>${item.getId()}</td>
                        <td>${item.getStatusCode()}</td>
                        <td>${item.getH1()}</td>
                        <td>${item.getTitle()}</td>
                        <td>${item.getDescription()}</td>
                        <td>${item.getCreatedAt().toString()}</td>
                    </tr>
                @endfor
                </tbody>
            </table>
        </div>
    @endif

`
)
    
