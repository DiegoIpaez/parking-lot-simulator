package com.dip.parkinglotsimulator.models;

public class Vehicle {
    private String licensePlate;
    private String brand;
    private String model;

    public Vehicle(String licensePlate, String brand, String model) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Vehículo [Patente: " + licensePlate + ", Marca: " + brand + ", Modelo: " + model + "]";
    }
}
