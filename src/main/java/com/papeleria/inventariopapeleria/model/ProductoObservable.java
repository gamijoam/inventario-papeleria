package com.papeleria.inventariopapeleria.model;

import javafx.beans.property.*;

public class ProductoObservable {
    // Propiedades observables
    private final LongProperty id;
    private final StringProperty nombre;
    private final StringProperty descripcion;
    private final StringProperty codigoUnico;
    private final DoubleProperty precioVenta;
    private final DoubleProperty precioCosto;
    private final StringProperty categoria;
    private final StringProperty marca;
    private final StringProperty proveedor;
    private final IntegerProperty cantidad;
    private final StringProperty color;

    // Constructor vacío para inicializar las propiedades
    public ProductoObservable() {
        this.id = new SimpleLongProperty();
        this.nombre = new SimpleStringProperty();
        this.descripcion = new SimpleStringProperty();
        this.codigoUnico = new SimpleStringProperty();
        this.precioVenta = new SimpleDoubleProperty();
        this.precioCosto = new SimpleDoubleProperty();
        this.categoria = new SimpleStringProperty();
        this.marca = new SimpleStringProperty();
        this.proveedor = new SimpleStringProperty();
        this.cantidad = new SimpleIntegerProperty();
        this.color = new SimpleStringProperty();
    }

    // Constructor a partir de un Producto existente
    public ProductoObservable(Producto producto) {
        this.id = new SimpleLongProperty(producto.getId());
        this.nombre = new SimpleStringProperty(producto.getNombre());
        this.descripcion = new SimpleStringProperty(producto.getDescripcion());
        this.codigoUnico = new SimpleStringProperty(producto.getCodigoUnico());
        this.precioVenta = new SimpleDoubleProperty(producto.getPrecioVenta());
        this.precioCosto = new SimpleDoubleProperty(producto.getPrecioCosto());
        this.categoria = new SimpleStringProperty(producto.getCategoria());
        this.marca = new SimpleStringProperty(producto.getMarca());
        this.proveedor = new SimpleStringProperty(producto.getProveedor());
        this.cantidad = new SimpleIntegerProperty(producto.getCantidad());
        this.color = new SimpleStringProperty(producto.getColor());
    }

    // Getters y Setters para las propiedades observables

    // ID
    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public LongProperty idProperty() {
        return id;
    }

    // Nombre
    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    // Descripción
    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    // Código Único
    public String getCodigoUnico() {
        return codigoUnico.get();
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico.set(codigoUnico);
    }

    public StringProperty codigoUnicoProperty() {
        return codigoUnico;
    }

    // Precio de Venta
    public double getPrecioVenta() {
        return precioVenta.get();
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta.set(precioVenta);
    }

    public DoubleProperty precioVentaProperty() {
        return precioVenta;
    }

    // Precio de Costo
    public double getPrecioCosto() {
        return precioCosto.get();
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto.set(precioCosto);
    }

    public DoubleProperty precioCostoProperty() {
        return precioCosto;
    }

    // Categoría
    public String getCategoria() {
        return categoria.get();
    }

    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }

    public StringProperty categoriaProperty() {
        return categoria;
    }

    // Marca
    public String getMarca() {
        return marca.get();
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public StringProperty marcaProperty() {
        return marca;
    }

    // Proveedor
    public String getProveedor() {
        return proveedor.get();
    }

    public void setProveedor(String proveedor) {
        this.proveedor.set(proveedor);
    }

    public StringProperty proveedorProperty() {
        return proveedor;
    }

    // Cantidad
    public int getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    // Color
    public String getColor() {
        return color.get();
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public StringProperty colorProperty() {
        return color;
    }

    // Método para convertir ProductoObservable a Producto (entidad)
    public Producto toProducto() {
        Producto producto = new Producto();
        producto.setId(getId());
        producto.setNombre(getNombre());
        producto.setDescripcion(getDescripcion());
        producto.setCodigoUnico(getCodigoUnico());
        producto.setPrecioVenta(getPrecioVenta());
        producto.setPrecioCosto(getPrecioCosto());
        producto.setCategoria(getCategoria());
        producto.setMarca(getMarca());
        producto.setProveedor(getProveedor());
        producto.setCantidad(getCantidad());
        producto.setColor(getColor());
        return producto;
    }
}