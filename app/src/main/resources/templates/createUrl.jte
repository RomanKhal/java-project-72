@import hexlet.code.util.NamedRoutes

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <form action="${NamedRoutes.urls()}" method="post">
            <div class="mb-3">
                <label for="exampleURL" class="form-label">URL</label>
                <input type="url" name="name" class="form-control" id="exampleURL" aria-describedby="URLHelp">
                <div id="URLHelp" class="form-text">Enter your URL here.</div>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
`
)