package dao;

import dto.HabitacionDTO;
import util.ConexionDB;

import java.sql.*;
import java.util.*;

public class HabitacionDAO {

    // Agregar una nueva habitación
    public void agregarHabitacion(HabitacionDTO habitacion) {
        String sql = "INSERT INTO habitacion (id_habitacion, numero, tipo, precio) VALUES (?, ?, ?, ?)";
        try (Connection conexion = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
             
            stmt.setInt(1, habitacion.getIdHabitacion());
            stmt.setInt(2, habitacion.getNumeroHabitacion());
            stmt.setString(3, habitacion.getTipoHabitacion());
            stmt.setDouble(4, habitacion.getPrecioHabitacion());
            
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Habitación agregada con éxito.");
            } else {
                System.out.println("Error al agregar la habitación.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener la lista de todas las habitaciones
    public List<HabitacionDTO> obtenerHabitaciones() {
        List<HabitacionDTO> listaHabitaciones = new ArrayList<>();
        String sql = "SELECT * FROM habitacion";
        try (Connection conexion = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                int idHabitacion = rs.getInt("id_habitacion");
                int numero = rs.getInt("numero");
                String tipoHabitacion = rs.getString("tipo");
                double precioHabitacion = rs.getDouble("precio");
                
                listaHabitaciones.add(new HabitacionDTO(idHabitacion, numero, tipoHabitacion, precioHabitacion));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar las habitaciones.");
            e.printStackTrace();
        }
        return listaHabitaciones;
    }

    // Buscar una habitación por su identificador
    public HabitacionDTO buscarHabitacionPorId(int idHabitacion) {
        String sql = "SELECT * FROM habitacion WHERE id_habitacion = ?";
        try (Connection conexion = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
             
            stmt.setInt(1, idHabitacion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int numero = rs.getInt("numero");
                String tipoHabitacion = rs.getString("tipo");
                double precioHabitacion = rs.getDouble("precio");
                
                return new HabitacionDTO(idHabitacion, numero, tipoHabitacion, precioHabitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Actualizar una habitación existente
    public void modificarHabitacion(HabitacionDTO habitacion) {
        String sql = "UPDATE habitacion SET numero = ?, tipo = ?, precio = ? WHERE id_habitacion = ?";
        try (Connection conexion = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
             
            stmt.setInt(1, habitacion.getNumeroHabitacion());
            stmt.setString(2, habitacion.getTipoHabitacion());
            stmt.setDouble(3, habitacion.getPrecioHabitacion());
            stmt.setInt(4, habitacion.getIdHabitacion());
            
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Habitación modificada con éxito.");
            } else {
                System.out.println("Error al modificar la habitación.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar una habitación por su identificador
    public void eliminarHabitacion(int idHabitacion) {
        String sql = "DELETE FROM habitacion WHERE id_habitacion = ?";
        try (Connection conexion = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
             
            stmt.setInt(1, idHabitacion);
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Habitación eliminada con éxito.");
            } else {
                System.out.println("Error al eliminar la habitación.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}