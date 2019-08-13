package service;

import dto.Car;
import dto.Driver;
import dto.Model;
import dto.Record;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

public interface IRentalRecord {
    boolean addModel(String modelName, int tank, double dayPrice) throws SQLException;
    Model getModel(String modelName) throws SQLException;
    Set<Model> getAllModels() throws SQLException;

    boolean addDriver(long license_id, String name) throws SQLException;
    Driver getDriver(long license_id) throws SQLException;
    Set<Driver> getAllDrivers() throws SQLException;

    boolean addCar(String regNumber, Model model) throws SQLException;
    Car getCar(String regNumber) throws SQLException;

    int rentRecord(Car car, Driver driver, LocalDate rentDate, int rentDays) throws SQLException;
    Set<Car> getAllCars() throws SQLException;
    Set<Car> getAllCarsNotInUse() throws SQLException;
    int rentCar(Car car, Driver driver, LocalDate rentDate, int rentDays) throws SQLException;
    int returnCar(Car car, LocalDate returnDate, int tankPercent) throws SQLException;
    boolean setCarInUse(Car car, boolean inUse) throws SQLException;
    Set<Record> getAllRecords(LocalDate from, LocalDate to) throws SQLException;
    Set<Record>getAllOpenRecords() throws SQLException;
    boolean removeRecord (int id) throws SQLException;
    Set<Record> getAllDelayedRecords() throws SQLException;
    Model getMostProfitableModel(LocalDate from,  LocalDate to) throws SQLException;
}
