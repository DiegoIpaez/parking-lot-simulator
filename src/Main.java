import java.util.Scanner;
import models.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLot();
        int option;
        do {
            System.out.println("\n===== SIMULADOR DE ESTACIONAMIENTO =====");
            System.out.println("1. Ingresar vehículo");
            System.out.println("2. Retirar vehículo");
            System.out.println("3. Mostrar vehículos estacionados");
            System.out.println("4. Mostrar historial de tickets");
            System.out.println("5. Mostrar lugares disponibles");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Patente: ");
                    String plate = scanner.nextLine();
                    System.out.print("Marca: ");
                    String brand = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String model = scanner.nextLine();
                    Vehicle vehicle = new Vehicle(plate, brand, model);
                    parkingLot.parkVehicle(vehicle);
                    break;
                case 2:
                    System.out.print("Ingrese la patente: ");
                    String licensePlate = scanner.nextLine();
                    parkingLot.removeVehicle(licensePlate);
                    break;
                case 3:
                    parkingLot.showParkedVehicles();
                    break;
                case 4:
                    parkingLot.showCompletedTickets();
                    break;
                case 5:
                    System.out.println("Lugares disponibles: " + parkingLot.availableSpots());
                    break;
                case 0:
                    System.out.println("👋 Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 0);

        scanner.close();
    }
}
