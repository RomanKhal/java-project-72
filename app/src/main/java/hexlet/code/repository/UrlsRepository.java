package hexlet.code.repository;

import hexlet.code.model.Url;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UrlsRepository extends BaseRepository {

    public static long save(Url url) throws SQLException {
        String sql = "INSERT INTO url (name, created_at) VALUES (?, ?)";
        try (var con = dataSource.getConnection();
             var preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, url.getName());
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.execute();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                url.setId(generatedKeys.getLong(1));
                url.setCreatedAt(timestamp);
            }
        }
        return url.getId();
    }

    public static List<Url> index() throws SQLException {
        String sql = "SELECT u.url_id, u.name, u.created_at, max(c.created_at) as check_time, c.status_code "
                + "FROM url AS u LEFT JOIN url_check AS c "
                + "ON u.url_id = c.url_id "
                + "GROUP BY u.url_id, u.name, u.created_at, c.status_code";
        List<Url> result = new ArrayList<>();
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Url url = new Url();
                url.setId(resultSet.getLong("url_id"));
                url.setName(resultSet.getString("name"));
                url.setCreatedAt(resultSet.getTimestamp("created_at"));
                url.setLastCheck(resultSet.getTimestamp("check_time"));
                url.setCode(resultSet.getInt("status_code"));
                result.add(url);
            }
        }
        return result;
    }

    public static Optional<Url> search(String name) throws SQLException {
        var sql = "SELECT * FROM url WHERE name = ?";
        Url result = new Url();
        try (var con = dataSource.getConnection();
             var preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result.setId(resultSet.getLong("url_id"));
                result.setName((resultSet.getString("name")));
                result.setCreatedAt(resultSet.getTimestamp("created_at"));
                return Optional.of(result);
            }
        }
        return Optional.empty();
    }

    public static Optional<Url> find(Long id) throws SQLException {
        var sql = "SELECT * FROM url WHERE url_id = ?";
        Url result = new Url();
        try (var con = dataSource.getConnection();
             var preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result.setId(id);
                result.setName((resultSet.getString("name")));
                result.setCreatedAt(resultSet.getTimestamp("created_at"));
                return Optional.of(result);
            }
        }
        return Optional.empty();
    }
}
