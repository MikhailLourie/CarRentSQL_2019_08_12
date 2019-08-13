package service;

import dto.Car;
import dto.Driver;
import dto.Model;
import dto.Record;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class RentCompanyService implements IRentalRecord {
    private Connection connection;

    public RentCompanyService(Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public boolean addModel(String modelName, int tank, double dayPrice) throws SQLException {
        String sql = "INSERT IGNORE INTO model VALUES(" +
                "'" + modelName + "'" + "," +
                tank + "," +
                dayPrice + ")";
        return connection.createStatement().executeUpdate(sql) == 1;
    }

    @Override
    public Model getModel(String modelName) throws SQLException {
        String sql = "SELECT * FROM model WHERE id_model = '" + //запрос команды на базу
                modelName + "'";
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        if (!resultSet.next()) return null;

        return new Model(resultSet.getNString(1),
                resultSet.getInt(2),
                resultSet.getDouble(3));
    }

    @Override
    public Set<Model> getAllModels() throws SQLException {
        String sql = "SELECT * FROM model";
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        Set<Model> result = new HashSet<>();
        while (resultSet.next()) {
            result.add(new Model(
                    resultSet.getNString(1),
                    resultSet.getInt(2),
                    resultSet.getDouble(3)));
        }
        return result;
    }

    @Override
    public boolean addDriver(long license_id, String name) throws SQLException {
        String sql = "INSERT IGNORE INTO driver VALUES(" +
                license_id + "," +
                "'" + name + "'" + ")";
        return connection.createStatement().executeUpdate(sql) == 1;
    }

    @Override
    public Driver getDriver(long license_id) throws SQLException {
        String sql = "SELECT * FROM driver WHERE id_driver =" +  //запрос команды на базу
                license_id;
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        if (!resultSet.next()) return null;

        return new Driver(resultSet.getInt(1),
                resultSet.getString(2));
    }

    @Override
    public Set<Driver> getAllDrivers() throws SQLException {
        String sql = "SELECT * FROM driver";
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        Set<Driver> result = new HashSet<>();
        while (resultSet.next()) {
            result.add(new Driver(
                    resultSet.getInt(1),
                    resultSet.getString(2)));
        }
        return result;
    }

    @Override
    public boolean addCar(String regNumber, Model model) throws SQLException {
        String sql = "INSERT IGNORE INTO car VALUES(" +
                "'" + regNumber + "'" + "," +
                "'" + model.getModelName() + "'" + "," +
                0 + ")";
        return connection.createStatement().executeUpdate(sql) == 1;
    }

    @Override
    public Car getCar(String regNumber) throws SQLException {
        String sql = "SELECT * FROM car WHERE id_car = '" + //запрос команды на базу
                regNumber + "'";
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        if (!resultSet.next()) return null;

        return new Car(resultSet.getNString(1),
                getModel(resultSet.getNString(2)),
                resultSet.getBoolean(3));
    }

    @Override
    public int rentRecord(Car car, Driver driver, LocalDate rentDate, int rentDays) throws SQLException {
        if (car.isInUse()) return -1;
        String sql = "INSERT INTO record (id_car, id_driver, rent_date, rent_days) VALUES("+
                "'"+car.getRegNumber()+"'"+","+
                driver.getId()+","+
                "'"+rentDate+"'"+","+
                rentDays+")";

        Statement statement= connection.createStatement();
        statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);

        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        setCarInUse(car, true);

        return resultSet.getInt(1);
    }

    @Override
    public Set<Car> getAllCars() throws SQLException {
        String sql = "SELECT * FROM car";
        return getCars(sql);
    }

    @Override
    public Set<Car> getAllCarsNotInUse() throws SQLException {
        String sql = "SELECT * FROM car WHERE in_use =" + 1;
        return getCars(sql);
    }

    private Set<Car> getCars(String sql) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        Set<Car> result = new HashSet<>();
        while (resultSet.next()) {
            result.add(new Car(
                    resultSet.getString(1),
                    getModel(resultSet.getString(2)),
                    resultSet.getBoolean(3)));
        }
        return result;
    }

    @Override
    public int rentCar(Car car, Driver driver, LocalDate rentDate, int rentDays) throws SQLException {
        if (getCar(car.getRegNumber()).isInUse()) // проверка на то, что машина не используется
            return -1;
        String sql = "INSERT INTO record VALUES (id_car, id_driver, rent_date, rent_days) VALUES(" +
                "'" + car.getRegNumber() + "'" + "," +
                driver.getId() + "," +
                "'" + rentDate + "'" + "," +
                rentDays + ")";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS); // делает update базы и возвращает Id person
        ResultSet rs = statement.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    @Override
    public int returnCar(Car car, LocalDate returnDate, int tankPercent) throws SQLException {
        if (getCar(car.getRegNumber()).isInUse()) // проверка на то, что машина не используется
            return -1;
        String sql = "SELECT id_record, return_date FROM record WHERE id_car=" +
                "'" + car.getRegNumber() + "'" + "," + " AND " + "return_date=null";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.next()) return -1;
        int idRecord = resultSet.getInt(1);
        LocalDate rentDate = LocalDate.parse(resultSet.getString(2));
        int rentDays = resultSet.getInt(3);

        sql = "UPDATE record SET " +
                "return_date='" + returnDate + "'" + "," +
                "tank_persent=" + (100 - tankPercent) + "," +
                "cost= " + totalCost(rentDate, returnDate, rentDays, tankPercent, car) +
                "WHERE id_record=" + idRecord;
        statement.executeUpdate(sql);
        setCarInUse(car, false);
        return idRecord;
    }

    @Override
    public boolean setCarInUse(Car car, boolean inUse) throws SQLException {
        String sql = "UPDATE car SET in_use=" +
                (inUse ? 1 : 0) + " WHERE id_car=" +
                "'" + car.getRegNumber() + "'";
        return connection.createStatement().executeUpdate(sql) == 1;
    }

    @Override
    public Set<Record> getAllRecords(LocalDate from, LocalDate to) throws SQLException {
        String sql = "SELECT * FROM record";
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        Set<Record> result = new HashSet<>();
        while (resultSet.next()) {
            result.add(new Record(
                    resultSet.getInt(1),
                    getCar(resultSet.getString(2)),
                    getDriver(resultSet.getInt(3)),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getDouble(8)));
        }
        return result;
    }

    @Override
    public Set<Record> getAllOpenRecords() throws SQLException {
        String sql = "SELECT * FROM record";
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        Set<Record> result = new HashSet<>();
        while (resultSet.next()) {
            Date date = resultSet.getDate(5);

            result.add(new Record(
                    resultSet.getInt(1),
                    getCar(resultSet.getString(2)),
                    getDriver(resultSet.getInt(3)),
                    resultSet.getDate(4).toLocalDate(),
                    date == null ? null : date.toLocalDate(),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getDouble(8)));
        }
        return result;
    }

    @Override
    public boolean removeRecord(int id) throws SQLException {
    String sql = "DELETE from record where id_record="+id;
        return connection.createStatement().executeUpdate(sql) == 1;
    }

    @Override
    public Set<Record> getAllDelayedRecords() throws SQLException {
        String sql = "SELECT * FROM record WHERE DATEDIFF(" + "'" + LocalDate.now() + "'" + ", rent_date) > rent_days";
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        Set<Record> result = new HashSet<>();
        while (resultSet.next()) {
            Date date = resultSet.getDate(5);
            result.add(new Record(
                    resultSet.getInt(1),
                    getCar(resultSet.getString(2)),
                    getDriver(resultSet.getInt(3)),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getDouble(8)));
        }
        return null;
    }

    @Override
    public Model getMostProfitableModel(LocalDate from, LocalDate to) throws SQLException {
        String query = "SELECT id_model, sum(record.cost) as sum_model_cost FROM car " +
                "NATURAL JOIN record WHERE rent_date BETWEEN " + "'" + from + "'" + " AND " + "'" + to + "'" +
                "GROUP BY id_model " +
                "ORDER BY sum_model_cost " +
                "DESC LIMIT 1";
        ResultSet rs = connection.createStatement().executeQuery(query);
        return !rs.next() ? null : getModel(rs.getString("id_model"));
    }

    private double totalCost(LocalDate rentDate, LocalDate returnDate, int rentDays, int tankPercent, Car car) {
        int rentDaysReal = (int) (returnDate.toEpochDay() - rentDate.toEpochDay());
        double totalCost = rentDays * car.getModel().getDayPrice();
        if (rentDaysReal - rentDays > 0 || tankPercent != 100)
            totalCost = totalCost + (rentDaysReal - rentDays) * car.getModel().getDayPrice() * RentCompanyConfig.DELAY_COEFF
                    + (100 - tankPercent) * RentCompanyConfig.FUEL_PRICE;
        return totalCost;
    }

    public Set<Car> getCarsByTankValueAndDriver(String driverName, int tankModel) throws SQLException {
        String sql = "SELECT DISTINCT id_car, id_model, in_use FROM record natural join (select * from driver where name_driver =" +
                "'"+ driverName +"'"+") as drivers natural join (SELECT * from rent_company.car natural join model where tank_model>="+ tankModel +") as cars";
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        Set<Car> result = new HashSet<>();
        while (resultSet.next()) {
            result.add(new Car(
                    resultSet.getString(1),
                    getModel(resultSet.getString(2)),
                    resultSet.getBoolean(3)));
        }
        return result;
    }
}
