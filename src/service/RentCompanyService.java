package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RentCompanyService{
   private Connection connection;
   private Statement statement = null;

    public RentCompanyService(Connection connection) throws SQLException {
        this.connection = connection;
        statement = connection.createStatement();
    }
}
