package hexlet.code.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

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
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
