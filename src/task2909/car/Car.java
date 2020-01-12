package task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int CABRIOLET = 2;
    static public final int SEDAN = 1;
    static public final int TRUCK = 0;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    static public final int MAX_TRUCK_SPEED = 80;
    static public final int MAX_SEDAN_SPEED = 120;
    static public final int MAX_CABRIOLET_SPEED = 90;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    protected Car(int numberOfPassengers) {
    }


    public void fill(double numberOfLiters) throws Exception {
        if (numberOfLiters < 0)
            throw new Exception();
        fuel += numberOfLiters;
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        double consumption;
        if (isSummer(date, SummerStart, SummerEnd)){
            consumption = getSummerConsumption(length);
        } else {
            consumption = getWinterConsumption(length);
        }
        return consumption;
    }

    private boolean canPassengersBeTransferred(){
        if(isDriverAvailable() && fuel > 0){
            return true;
        }
        else {
            return false;
        }
    }
    public int getNumberOfPassengersCanBeTransferred() {
        if (canPassengersBeTransferred())
            return numberOfPassengers;
        else
            return 0;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
        }
        fastenDriverBelt();
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }


    public abstract int getMaxSpeed();

    public static Car create(int type, int numberOfPassengers){
        Car car=null;
        switch (type){
            case 0:car = new Truck(numberOfPassengers);
                break;
            case 1:car = new Sedan(numberOfPassengers);
                break;
            case 2:car = new Cabriolet(numberOfPassengers);
        }
        return car;
    }
    public boolean isSummer(Date date , Date summerStart, Date summerEnd){
        if (date.after(summerStart)&&date.before(summerEnd)){
            return true;
        }
        return false;
    }
    public double getWinterConsumption(int length){
        return (winterFuelConsumption*length)+winterWarmingUp;
    }
    public double getSummerConsumption(int length){
        return (summerFuelConsumption*length);
    }
}




/*package com.javarush.task.task29.task2909.car;

import java.util.Date;

public class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public static Car create(int type, int numberOfPassengers){
        Car car = null;
        if(type == TRUCK){
            car =  new Truck(numberOfPassengers);
        } else if(type == CABRIOLET){
            car =  new Cabriolet(numberOfPassengers);
        } else if(type == SEDAN){
            car = new Sedan(numberOfPassengers);
        }
        return car;
    }

    public void fill(double numberOfLiters) throws Exception {
        if (numberOfLiters < 0)
            throw new Exception();
        fuel += numberOfLiters;
    }

    public boolean isSummer(Date date , Date summerStart, Date summerEnd){
        if (date.after(summerStart)&&date.before(summerEnd)){
            return true;
        }
        return false;
    }

    public double getWinterConsumption(int length){
        double consumption;
        consumption = length * winterFuelConsumption + winterWarmingUp;
        return consumption;
    }

    public double getSummerConsumption(int length){
        double consumption;
        consumption = length * summerFuelConsumption;
        return consumption;
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        if (isSummer(date,SummerStart,SummerEnd)) {
            return getWinterConsumption(length);
        } else {
            return getSummerConsumption(length);
        }
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (!isDriverAvailable())
            return 0;
        if (fuel <= 0)
            return 0;

        return numberOfPassengers;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
            fastenDriverBelt();
        } else {
            fastenDriverBelt();
        }
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    public int getMaxSpeed() {
        if (type == TRUCK)
            return 80;
        if (type == SEDAN)
            return 120;
        return 90;
    }
}*/