package de.hannes.datadogpoc.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PostgresService {

    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.url}")
    private String url;

    public ResultSet flightbookingQuery(String query) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String selectDatabaseQuery = "USE flightbooking";
        statement.executeUpdate(selectDatabaseQuery);
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.close();
        statement.close();
        return resultSet;
    }
}

