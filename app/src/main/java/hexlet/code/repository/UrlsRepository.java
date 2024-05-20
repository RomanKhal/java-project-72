package hexlet.code.repository;

import hexlet.code.model.Url;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UrlsRepository extends BaseRepository{

    public static void save(Url url) throws SQLException {
        String sql = "insert into urls (name, createdAt) values (?, ?)";
        try(var con = dataSource.getConnection();
        var preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, url.getName());
            preparedStatement.setTimestamp(2, url.getCreatedAt());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                url.setId(generatedKeys.getLong(1));
            }
        }
    }

    public static List<Url> index() throws SQLException {
        String sql = "select * from urls";
        List<Url> result = new ArrayList<>();
        try (var conn = dataSource.getConnection();
        var preparedStatement = conn.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Url url = new Url();
                url.setId(resultSet.getLong("id"));
                url.setName(resultSet.getString("name"));
                url.setCreatedAt(resultSet.getTimestamp("createdAt"));
                result.add(url);
            }
        }
        return result;
    }

    public static boolean isInDb (String name) throws SQLException {
        var sql = "select count (*) as count from urls where name = ?";
        boolean result = false;
        try (var con = dataSource.getConnection();
        var preparedStatement = con.prepareStatement(sql)){
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("count") != 0;
            }
        }
        return result;
    }

    public static Url find(Long id) throws SQLException {
        var sql = "select * from urls where id = ?";
        Url result = new Url();
        try (var con = dataSource.getConnection();
        var preparedStatement = con.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.setId(id);
                result.setName((resultSet.getString("name")));
                result.setCreatedAt(resultSet.getTimestamp("createdAt"));
            }
        }
        return result;
    }
}
