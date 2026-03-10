package com.dip.parkinglotsimulator.models;

import java.io.Serializable;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

import com.dip.parkinglotsimulator.utils.Constants;
import com.dip.parkinglotsimulator.utils.DateFormatter;

public class ParkingLot implements Serializable {
    private int maxCapacity;
    private List<Ticket> activeTickets;
    private List<Ticket> completedTickets;

    public ParkingLot() {
        this.maxCapacity = Constants.MAX_CAPACITY;
        this.activeTickets = new ArrayList<Ticket>();
        this.completedTickets = new ArrayList<Ticket>();
    }

    public boolean isFull() {
        int totalActiveTickets = activeTickets.size();
        return totalActiveTickets >= maxCapacity;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (isFull()) {
            System.out.println("🚫 Estacionamiento lleno.");
            return false;
        }

        String newPlate = vehicle.getLicensePlate();
        for (Ticket activeTicket : activeTickets) {
            String activePlate = activeTicket.getVehicle().getLicensePlate();

            boolean isRegistered = activePlate.equalsIgnoreCase(newPlate);
            if (isRegistered) {
                System.out.println("⚠️ Este vehículo ya se encuentra estacionado.");
                return false;
            }
        }

        Ticket newTicket = new Ticket(vehicle);
        activeTickets.add(newTicket);

        System.out.println("✅ Vehículo estacionado correctamente: " + newPlate);
        return true;
    }

    public boolean removeVehicle(String licensePlate) {
        Iterator<Ticket> it = activeTickets.iterator();
        while (it.hasNext()) {
            Ticket activeTicket = it.next();
            String activeLicensePlate = activeTicket.getVehicle().getLicensePlate();

            if (activeLicensePlate.equalsIgnoreCase(licensePlate)) {
                activeTicket.registerExit();
                completedTickets.add(activeTicket);
                it.remove();

                double totalCost = activeTicket.calculateCost();
                System.out.printf("💸 Vehículo retirado. Total a pagar: $%.2f%n", totalCost);
                return true;
            }
        }
        System.out.println("🚗 Vehículo no encontrado.");
        return false;
    }

    public void showParkedVehicles() {
        System.out.println("\n=== Vehículos actualmente estacionados ===");

        if (activeTickets.isEmpty()) {
            System.out.println("No hay vehículos estacionados actualmente.");
            return;
        }

        System.out.printf("%-10s %-10s %-10s %-20s%n",
                "Patente", "Marca", "Modelo", "Hora Entrada");
        System.out.println("---------------------------------------------------------");
        for (Ticket ticket : activeTickets) {
            System.out.printf("%-10s %-10s %-10s %-20s%n",
                    ticket.getVehicle().getLicensePlate(),
                    ticket.getVehicle().getBrand(),
                    ticket.getVehicle().getModel(),
                    ticket.getEntryTime().format(DateFormatter.STANDARD_DATE_TIME_FORMATTER));
        }
    }

    public void showCompletedTickets() {
        System.out.println("\n=== Tickets completados (Historial) ===");

        if (completedTickets.isEmpty()) {
            System.out.println("Aún no hay registros.");
            return;
        }

        System.out.printf("%-10s %-10s %-10s %-20s %-20s %-10s%n", "Patente", "Marca", "Modelo", "Hora Entrada",
                "Hora Salida", "Costo");
        System.out.println("----------------------------------------------------------------------------------");

        for (Ticket ticket : completedTickets) {
            System.out.printf("%-10s %-10s %-10s %-20s %-20s $%-9.2f%n",
                    ticket.getVehicle().getLicensePlate(),
                    ticket.getVehicle().getBrand(),
                    ticket.getVehicle().getModel(),
                    ticket.getEntryTime().format(DateFormatter.STANDARD_DATE_TIME_FORMATTER),
                    DateFormatter.formatDateTime(ticket.getExitTime()),
                    ticket.calculateCost());
        }
    }

    public int availableSpots() {
        return maxCapacity - activeTickets.size();
    }
}
