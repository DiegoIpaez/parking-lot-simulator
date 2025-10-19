package com.dip.parkinglotsimulator.models;

import java.time.Duration;
import java.time.LocalDateTime;

import com.dip.parkinglotsimulator.utils.Constants;

public class Ticket {
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
        double hours = Math.ceil(minutes / Constants.MINUTES_PER_HOUR);
        return hours * Constants.HOURLY_RATE;
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
        return vehicle.toString() +
                "\nHora de Entrada: " + entryTime +
                (exitTime != null
                        ? "\nHora de Salida: " + exitTime + "\nCosto Total: $" + calculateCost()
                        : "\n[Todavía Estacionado]");
    }
}
