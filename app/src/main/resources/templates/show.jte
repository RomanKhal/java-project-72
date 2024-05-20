@import hexlet.code.dto.UrlPage
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
`
)
    
