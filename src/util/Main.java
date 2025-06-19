package util;

import dao.ClienteDAO;
import dao.HabitacionDAO;
import dao.ReservaDAO;
import dto.ClienteDTO;
import dto.HabitacionDTO;
import dto.ReservaDTO;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        ReservaDAO reservaDAO = new ReservaDAO();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("--- MENÚ HOTEL ---");
            System.out.println("1. Insertar cliente");
            System.out.println("2. Listar clientes por nombre");
            System.out.println("3. Insertar habitación");
            System.out.println("4. Listar habitaciones por precio");
            System.out.println("5. Insertar reserva");
            System.out.println("6. Listar reservas activas");
            System.out.println("7. Mostrar reservas con Iterator");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del cliente: ");
                    String nombre = sc.nextLine();
                    System.out.print("Email del cliente: ");
                    String email = sc.nextLine();
                    // Se asume id 0 para auto-incrementar
                    clienteDAO.insertarCliente(new ClienteDTO(0, nombre, email));
                    break;
                case 2:
                    List<ClienteDTO> clientes = clienteDAO.obtenerClientes();
                    // Ordenamos la lista por nombre
                    Collections.sort(clientes, Comparator.comparing(ClienteDTO::getNombre));
                    clientes.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Número de habitación: ");
                    int numero = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo: ");
                    String tipo = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    habitacionDAO.agregarHabitacion(new HabitacionDTO(0, numero, tipo, precio));
                    break;
                case 4:
                    List<HabitacionDTO> habitaciones = habitacionDAO.obtenerHabitaciones();
                    // Ordenamos por precio
                    Collections.sort(habitaciones, Comparator.comparingDouble(HabitacionDTO::getPrecioHabitacion));
                    habitaciones.forEach(System.out::println);
                    break;
                case 5:
                    System.out.print("ID cliente: ");
                    int idCliente = sc.nextInt();
                    System.out.print("ID habitación: ");
                    int idHabitacion = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Fecha inicio (yyyy-mm-dd): ");
                    LocalDate inicio = LocalDate.parse(sc.nextLine());
                    System.out.print("Fecha fin (yyyy-mm-dd): ");
                    LocalDate fin = LocalDate.parse(sc.nextLine());
                    reservaDAO.insertar(new ReservaDTO(0, idCliente, idHabitacion, inicio, fin));
                    break;
                case 6:
                    List<ReservaDTO> activas = reservaDAO.listarReservasActivas();
                    activas.forEach(System.out::println);
                    break;
                case 7:
                    reservaDAO.mostrarReservasConIterator();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}