package com.dip.parkinglotsimulator.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dip.parkinglotsimulator.utils.Constants;

public class ParkingLot {
    private int maxCapacity;
    private List<Ticket> activeTickets;
    private List<Ticket> completedTickets;

    public ParkingLot() {
        this.maxCapacity = Constants.MAX_CAPACITY;
        this.activeTickets = new ArrayList<Ticket>();
        this.completedTickets = new ArrayList<Ticket>();
    }

    public boolean parkVehicle(Vehicle vehicle) {
        int totalActiveTickets = activeTickets.size();
        boolean isParkingFull = totalActiveTickets >= maxCapacity;

        if (isParkingFull) {
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
                System.out.println("💸 Vehículo retirado. Total a pagar: $" + totalCost);
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
        } else {
            activeTickets.forEach(ticket -> System.out.println(ticket.getVehicle()));
        }
    }

    public void showCompletedTickets() {
        System.out.println("\n=== Tickets completados (Historial) ===");
        if (completedTickets.isEmpty())
            System.out.println("Aún no hay registros.");
        else
            completedTickets.forEach(System.out::println);
    }

    public int availableSpots() {
        return maxCapacity - activeTickets.size();
    }
}
