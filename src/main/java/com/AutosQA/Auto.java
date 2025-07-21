package com.AutosQA;

public class Auto {
    private Long id;
    private String marca;
    private String modelo;
    private Long fabricacion;

    // Constructor
    public Auto(Long id, String marca, String modelo, Long fabricacion) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.fabricacion = fabricacion;
    }

    public Auto(String marca, String modelo, Long fabricacion) {
        this(null, marca, modelo, fabricacion);
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Long getFabricacion() { return fabricacion; }
    public void setFabricacion(Long fabricacion) { this.fabricacion = fabricacion; }
}