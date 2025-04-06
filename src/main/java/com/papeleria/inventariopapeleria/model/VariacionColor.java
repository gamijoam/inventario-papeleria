package com.papeleria.inventariopapeleria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "variaciones_color")
public class VariacionColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // Constructores, getters y setters
    public VariacionColor() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
}