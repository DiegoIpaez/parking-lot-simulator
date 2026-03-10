package com.dip.parkinglotsimulator;

import java.util.Scanner;

import com.dip.parkinglotsimulator.models.*;
import com.dip.parkinglotsimulator.utils.PersistenceManager;

public class Main {

    private static final String DATA_FILE = "parking_lot.dat";
    private static final int OPTION_PARK_VEHICLE = 1;
    private static final int OPTION_REMOVE_VEHICLE = 2;
    private static final int OPTION_SHOW_PARKED_VEHICLES = 3;
    private static final int OPTION_SHOW_TICKET_HISTORY = 4;
    private static final int OPTION_SHOW_AVAILABLE_SPOTS = 5;
    private static final int OPTION_EXIT = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = PersistenceManager.load(DATA_FILE, new ParkingLot());
        int option = -1;

        do {
            System.out.println("\n===== SIMULADOR DE ESTACIONAMIENTO =====");
            System.out.println(OPTION_PARK_VEHICLE + ". Ingresar vehículo");
            System.out.println(OPTION_REMOVE_VEHICLE + ". Retirar vehículo");
            System.out.println(OPTION_SHOW_PARKED_VEHICLES + ". Mostrar vehículos estacionados");
            System.out.println(OPTION_SHOW_TICKET_HISTORY + ". Mostrar historial de tickets");
            System.out.println(OPTION_SHOW_AVAILABLE_SPOTS + ". Mostrar lugares disponibles");
            System.out.println(OPTION_EXIT + ". Salir");
            System.out.println("\nSeleccione una opción:");

            String input = scanner.nextLine();
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException error) {
                System.out.println("⚠️ Opción inválida, ingrese un número.");
                continue;
            }

            switch (option) {
                case OPTION_PARK_VEHICLE:
                    if (parkingLot.isFull()) {
                        System.out.println("\n🚫 Estacionamiento lleno.");
                        break;
                    }
                    System.out.print("Patente:\n");
                    String plate = scanner.nextLine();
                    System.out.print("Marca:\n");
                    String brand = scanner.nextLine();
                    System.out.print("Modelo:\n");
                    String model = scanner.nextLine();
                    Vehicle vehicle = new Vehicle(plate, brand, model);
                    parkingLot.parkVehicle(vehicle);
                    PersistenceManager.save(parkingLot, DATA_FILE);
                    break;
                case OPTION_REMOVE_VEHICLE:
                    System.out.print("Ingrese la patente:\n");
                    String licensePlate = scanner.nextLine();
                    parkingLot.removeVehicle(licensePlate);
                    PersistenceManager.save(parkingLot, DATA_FILE);
                    break;
                case OPTION_SHOW_PARKED_VEHICLES:
                    parkingLot.showParkedVehicles();
                    break;
                case OPTION_SHOW_TICKET_HISTORY:
                    parkingLot.showCompletedTickets();
                    break;
                case OPTION_SHOW_AVAILABLE_SPOTS:
                    System.out.println("Lugares disponibles: " + parkingLot.availableSpots());
                    break;
                case OPTION_EXIT:
                    System.out.println("\n👋 Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != OPTION_EXIT);

        scanner.close();
    }
}
