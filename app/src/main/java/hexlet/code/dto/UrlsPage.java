package hexlet.code.dto;

import hexlet.code.model.Url;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Getter
public class UrlsPage extends BasePage {
    private List<Url> urls;
}
