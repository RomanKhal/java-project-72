package hexlet.code.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class Url {
    private long id;
    private String name;
    private Timestamp createdAt;
    private Timestamp lastCheck;
    private int code;

    public Url(String name) {
        this.name = name;
    }
}
