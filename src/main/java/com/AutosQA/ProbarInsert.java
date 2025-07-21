package com.AutosQA;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProbarInsert {
    public static void insertarEjemplo() {
        try (Connection conn = Conexion.getConnection()) {
            String sql = "INSERT INTO autos (color, marca, modelo, fabricacion) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Azul");
            ps.setString(2, "Nissan");
            ps.setString(3, "Versa");
            ps.setLong(4, 2021L);

            int filas = ps.executeUpdate();
            System.out.println(" Auto insertado: " + filas + " fila(s) afectada(s).");

        } catch (Exception e) {
            System.out.println(" Error insertando auto: " + e.getMessage());
        }
    }
}