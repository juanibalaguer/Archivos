package com.example.archivos;

import java.io.Serializable;

public class Auto implements Serializable {

    private String marca;
    private int modelo;
    private double precio;

    public Auto(String marca, int modelo, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }
    public Auto() {}

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
