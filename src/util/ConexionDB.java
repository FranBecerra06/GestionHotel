package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class ConexionDB {

	private static final String URL = "jdbc:mysql://localhost:3306/hotel?useSSL=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static Connection obtenerConexion() {

		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexion exitosa con la base de datos.");

		} catch (SQLException e) {

			System.out.println("No se ha podido conectar con la base de datos.");
			e.printStackTrace();

		}
		return conexion;

	}

}
