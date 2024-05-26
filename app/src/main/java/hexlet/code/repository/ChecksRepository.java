package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChecksRepository extends BaseRepository{
    public static void save(UrlCheck check) throws SQLException {
        String sql = "insert into urls_checks ()";
        try (var con = dataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        }
    }

    public static List<UrlCheck> index() {
        return new ArrayList<>();
    }
}
