package com.AutosQA.model;

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
}