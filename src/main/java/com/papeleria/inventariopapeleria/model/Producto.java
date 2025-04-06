package com.papeleria.inventariopapeleria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    @Column(name = "codigo_unico", unique = true)
    private String codigoUnico;
    @Column(name = "precio_venta")
    private double precioVenta;
    @Column(name = "precio_costo")
    private double precioCosto;
    private String categoria;
    private String marca;
    private String proveedor;
    private int cantidad;
    private String color; // Nuevo campo opcional si eliminas VariacionColor

    // Constructores, getters y setters
    public Producto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getCodigoUnico() { return codigoUnico; }
    public void setCodigoUnico(String codigoUnico) { this.codigoUnico = codigoUnico; }
    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }
    public double getPrecioCosto() { return precioCosto; }
    public void setPrecioCosto(double precioCosto) { this.precioCosto = precioCosto; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}