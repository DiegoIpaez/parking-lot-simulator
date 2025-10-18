package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utils.Constants;

public class ParkingLot {
    private int maxCapacity;
    private List<Ticket> activeTickets;
    private List<Ticket> completedTickets;

    public ParkingLot() {
        this.maxCapacity = Constants.MAX_CAPACITY;
        this.activeTickets = new ArrayList<>();
        this.completedTickets = new ArrayList<>();
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (activeTickets.size() >= maxCapacity) {
            System.out.println("🚫 Estacionamiento lleno.");
            return false;
        }

        for (Ticket t : activeTickets) {
            if (t.getVehicle().getLicensePlate().equalsIgnoreCase(vehicle.getLicensePlate())) {
                System.out.println("⚠️ Este vehículo ya se encuentra estacionado.");
                return false;
            }
        }

        Ticket newTicket = new Ticket(vehicle);
        activeTickets.add(newTicket);
        System.out.println("✅ Vehículo estacionado correctamente: " + vehicle.getLicensePlate());
        return true;
    }

    public boolean removeVehicle(String licensePlate) {
        Iterator<Ticket> it = activeTickets.iterator();
        while (it.hasNext()) {
            Ticket t = it.next();
            if (t.getVehicle().getLicensePlate().equalsIgnoreCase(licensePlate)) {
                t.registerExit();
                completedTickets.add(t);
                it.remove();
                System.out.println("💸 Vehículo retirado. Total a pagar: $" + t.calculateCost());
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
            activeTickets.forEach(t -> System.out.println(t.getVehicle()));
        }
    }

    public void showCompletedTickets() {
        System.out.println("\n=== Tickets completados (Historial) ===");
        if (completedTickets.isEmpty()) {
            System.out.println("Aún no hay registros.");
        } else {
            completedTickets.forEach(System.out::println);
        }
    }

    public int availableSpots() {
        return maxCapacity - activeTickets.size();
    }
}
