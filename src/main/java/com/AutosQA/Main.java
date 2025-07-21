package com.AutosQA;

import com.AutosQA.db.Conexion;
import com.autos.db.CrearTabla;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        CrearTabla.crearTablaAuto(); // Crear tabla

        AutoDAO dao = new AutoDAO();
        dao.guardarAuto(new Auto("Toyota", "Yaris", 2020L));
        dao.guardarAuto(new Auto("Ford", "Fiesta", 2019L));

        dao.listarAutos().forEach(a -> System.out.println(
                a.getId() + " - " + a.getMarca() + " - " + a.getModelo() + " - " + a.getFabricacion()
        ));
    }
}