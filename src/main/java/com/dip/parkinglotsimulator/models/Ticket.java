package com.dip.parkinglotsimulator.models;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

import com.dip.parkinglotsimulator.utils.Constants;

public class Ticket implements Serializable {
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
    }

    public void registerExit() {
        this.exitTime = LocalDateTime.now();
    }

    public double calculateCost() {
        if (exitTime == null)
            return 0;

        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        double costPerMinute = Constants.HOURLY_RATE / (double) Constants.MINUTES_PER_HOUR;
        double totalCost = minutes * costPerMinute;
        double roundedCost = Math.round(totalCost * 100.0) / 100.0;
        return roundedCost;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    @Override
    public String toString() {
        double totalCost = calculateCost();
        String formattedCost = String.format("%.2f", totalCost);
        return vehicle.toString() +
                "\nHora de Entrada: " + entryTime +
                (exitTime != null
                        ? "\nHora de Salida: " + exitTime + "\nCosto Total: $" + formattedCost
                        : "\n[Todavía Estacionado]");
    }

}
