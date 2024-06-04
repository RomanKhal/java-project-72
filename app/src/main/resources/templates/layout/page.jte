@import gg.jte.Content
@param Content content

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>Work on fourth project</title>
</head>
<body>
<%--${flash}--%>
<div class="mx-auto p-4 py-md-5">
<ul class="nav nav-underline">
    <li class="nav-item">
        <a class="navbar-brand" href="/">Анализатор страниц</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/">Main</a>
    </li>
    <li class="nav-item">
        <a class="nav-link " href="/urls">URls list</a>
    </li>
</ul>
</div>
${content}
</body>
</html>
