package service;

import dto.Car;
import dto.Driver;
import dto.Model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

public interface IRentalRecord {
    boolean addModel(String modelName, int tank, double dayPrice);
    Model getModel(String modelName) throws SQLException;
    Set<Model> getAllModels();

    boolean addDriver(long license_id, String name);
    Driver getDriver(long license_id);
    Set<Driver> getAllDrivers();

    boolean addCar(String regNumber, Model model);
    Car getCar(String regNumber);
    Set<Car> getAllCars();
    Set<Car> getAllCarsNotInUse();
    int rentCar(Car car, Driver driver, LocalDate rentDate, int rentDays);
    int returnCar(Car car, LocalDate returnDate, int tankPercent);
}
