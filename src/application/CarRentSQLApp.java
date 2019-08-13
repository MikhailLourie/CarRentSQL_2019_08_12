package application;

import dto.Car;
import dto.Driver;
import service.RentCompanyService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

public class CarRentSQLApp {

    public static void main(String[] args) throws SQLException {
        try (
                Connection connection = // создание соединения с БД
                        DriverManager.getConnection("jdbc:mysql://localhost/rent_company?useSSL=false", "root", "password");) {// write your code here// write your code here

            RentCompanyService service =
                    new RentCompanyService(connection);
            //FOR MODEL
            // System.out.println(service.getModel("Ford")); //for getModel
            //   System.out.println(service.addModel("Honda", 25, 80.)); // addModel (усли 2 раза добавим, то на второй - false
//            for (Model model : service.getAllModels()) { // проверка на добавление всех моделей
//                System.out.println(model);

//            FOR DRIVER
            System.out.println(service.getDriver(111)); // for getDriver
             System.out.println(service.addDriver(999, "Dimon")); // for addDriver
            for (Driver driver : service.getAllDrivers()) { // добавление всех драйверов
                System.out.println(driver);}

            //FOR CAR
            // System.out.println(service.getCar("AAA-111"));
            //System.out.println(service.addCar("EEE-777", service.getModel("Ford")));
//            for (Car car : service.getAllCars()) {
//                System.out.println(car);
//            for (Car car : service.getAllCarsNotInUse()) {
//                System.out.println(car);

            //RECORDS
            int rentId = service.rentRecord(service.getCar("DDD-444"),
                    service.getDriver(333),
                    LocalDate.of(2019, 8, 1),
                    10);
            System.out.println(rentId);
//
            int returnId = service.returnCar(service.getCar("DDD-444"),
                    LocalDate.of(2019, 8, 11), 90);

            for (Car car : service.getCarsByTankValueAndDriver("Dasha", 30)) {
                System.out.println(car);
            }
        }
    }
}

