package com.AutosQA.db;

import java.sql.Connection;
import java.sql.Statement;
import com.AutosQA.db.Conexion;

public class CrearTabla {
    public static void crearTabla() {
        String sql = """
            CREATE TABLE IF NOT EXISTS auto (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                marca VARCHAR(100),
                modelo VARCHAR(100),
                fabricacion BIGINT
            );
        """;

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla 'auto' creada o ya existe.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}