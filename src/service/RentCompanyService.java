package service;

import dto.Car;
import dto.Driver;
import dto.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Set;

public class RentCompanyService implements IRentalRecord{
   private Connection connection;
   private Statement statement = null;

    public RentCompanyService(Connection connection) throws SQLException {
        this.connection = connection;
        statement = connection.createStatement();
    }

    @Override
    public boolean addModel(String modelName, int tank, double dayPrice) {
        // TODO (LML, 2019-08-12)
        return false;
    }

    @Override
    public Model getModel(String modelName) throws SQLException {
        String sql = "SELECT * FROM model WHERE id_model = '"+ //запрос команды на базу
                modelName + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        if(!resultSet.next()) return null;

        return new Model(resultSet.getNString(1),
                        resultSet.getInt(2),
                        resultSet.getDouble(3));
    }

    @Override
    public Set<Model> getAllModels() {
        // TODO (LML, 2019-08-12)
        return null;
    }

    @Override
    public boolean addDriver(long license_id, String name) {
        // TODO (LML, 2019-08-12)
        return false;
    }

    @Override
    public Driver getDriver(long license_id) {
        // TODO (LML, 2019-08-12)
        return null;
    }

    @Override
    public Set<Driver> getAllDrivers() {
        // TODO (LML, 2019-08-12)
        return null;
    }

    @Override
    public boolean addCar(String regNumber, Model model) {
        // TODO (LML, 2019-08-12)
        return false;
    }

    @Override
    public Car getCar(String regNumber) {
        // TODO (LML, 2019-08-12)
        return null;
    }

    @Override
    public Set<Car> getAllCars() {
        // TODO (LML, 2019-08-12)
        return null;
    }

    @Override
    public Set<Car> getAllCarsNotInUse() {
        // TODO (LML, 2019-08-12)
        return null;
    }

    @Override
    public int rentCar(Car car, Driver driver, LocalDate rentDate, int rentDays) {
        // TODO (LML, 2019-08-12)
        return 0;
    }

    @Override
    public int returnCar(Car car, LocalDate returnDate, int tankPercent) {
        // TODO (LML, 2019-08-12)
        return 0;
    }
}
