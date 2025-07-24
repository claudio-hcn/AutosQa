package com.AutosQA.model;

import java.util.Objects;

public class Auto {
    
    private Integer id;
    private String marca;
    private String modelo;
    private Integer fabricacion;
    
    // Constructor vacío
    public Auto() {}

    /* // Constructor
        public Auto(Integer id, String marca, String modelo, Integer fabricacion) {
            this.id = id;
            this.marca = marca;
            this.modelo = modelo;
            this.fabricacion = fabricacion;
        }*/
        
    // Constructor con validaciones    
    public Auto(Integer id, String marca, String modelo, Integer fabricacion) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca no puede estar vacía.");
        }
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío.");
        }
        if (fabricacion == null) {
            throw new IllegalArgumentException("El año de fabricación es obligatorio.");
        }
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.fabricacion = fabricacion;
    }

    public Auto(String marca, String modelo,Integer fabricacion) {
        this(null, marca, modelo, fabricacion);
    }

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getFabricacion() { return fabricacion; }
    public void setFabricacion(Integer fabricacion) { this.fabricacion = fabricacion; }
    @Override
    public String toString() {
        return "Auto{id=" + id + ", marca='" + marca + "', modelo='" + modelo + "', fabricacion=" + fabricacion + "}";
    }
    /* refactorizacion: mejora para que la comparación de objetos sea por contenido y no por referencia (memoria) */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return Objects.equals(id, auto.id) &&
            Objects.equals(marca, auto.marca) &&
            Objects.equals(modelo, auto.modelo) &&
            Objects.equals(fabricacion, auto.fabricacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marca, modelo, fabricacion);
    }
}