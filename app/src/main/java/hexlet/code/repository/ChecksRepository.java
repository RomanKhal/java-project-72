package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChecksRepository extends BaseRepository {
    public static void save(UrlCheck check) throws SQLException {
        String sql = "insert into url_checks (statusCode, title, h1, description, urlId) values (?,?,?,?,?)";
        try (var con = dataSource.getConnection();
             var preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, check.getStatusCode());
            preparedStatement.setString(2, check.getTitle());
            preparedStatement.setString(3, check.getH1());
            preparedStatement.setString(4, check.getDescription());
            preparedStatement.setLong(5, check.getUrlId());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                check.setId(generatedKeys.getLong("id"));
            }
        }
    }

    public static List<UrlCheck> index(Long id) {
        String sql = "select (id, statusCode, title, h1, description, createdAt) from url_checks where urlId=? order by createdAt desc";
        return new ArrayList<>();
    }
}
