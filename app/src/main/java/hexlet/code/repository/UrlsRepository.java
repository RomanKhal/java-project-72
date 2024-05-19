package hexlet.code.repository;

import hexlet.code.model.Url;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class UrlsRepository extends BaseRepository{

    public static void save(Url url) throws SQLException {
        String sql = "insert into urls (name, createdAt) values (?, ?)";
        try(var con = dataSource.getConnection();
        var preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, url.getName());
            preparedStatement.setString(2, String.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                url.setId(generatedKeys.getLong(1));
            }
        }
    }
}
