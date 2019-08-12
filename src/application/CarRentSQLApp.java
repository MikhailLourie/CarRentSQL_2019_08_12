package application;

import service.RentCompanyService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CarRentSQLApp {

    public static void main(String[] args) throws SQLException {
        try(
                Connection connection = // создание соединения с БД
                        DriverManager.getConnection("jdbc:mysql://localhost/rent_company?useSSL=false", "root", "password");) {// write your code here// write your code here

            RentCompanyService service =
                    new RentCompanyService(connection);
            System.out.println(service.getModel("Ford"));
        }
    }
}
