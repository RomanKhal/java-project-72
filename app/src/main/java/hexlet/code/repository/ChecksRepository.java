package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChecksRepository extends BaseRepository {
    public static void save(UrlCheck check) throws SQLException {
        String sql = "insert into url_checks (status_code, title, h1, description, url_id, created_at)"
                + " values (?,?,?,?,?,?)";
        try (var con = dataSource.getConnection();
             var preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, check.getStatusCode());
            preparedStatement.setString(2, check.getTitle());
            preparedStatement.setString(3, check.getH1());
            preparedStatement.setString(4, check.getDescription());
            preparedStatement.setLong(5, check.getUrlId());
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            preparedStatement.setTimestamp(6, timestamp);
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                check.setId(generatedKeys.getLong("id"));
                check.setCreatedAt(timestamp);
            }
        }
    }

    public static List<UrlCheck> index(Long urlId) throws SQLException {
        String sql = "select * from url_checks where url_id=? order by id desc ";
        List<UrlCheck> result = new ArrayList<>();
        try (var con = dataSource.getConnection();
             var preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setLong(1, urlId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                int status = resultSet.getInt("status_code");
                String title = resultSet.getString("title");
                String h1 = resultSet.getString("h1");
                String description = resultSet.getString("description");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                result.add(new UrlCheck(id, status, title, h1, description, id, createdAt));
            }
        }
        return result;
    }
}
