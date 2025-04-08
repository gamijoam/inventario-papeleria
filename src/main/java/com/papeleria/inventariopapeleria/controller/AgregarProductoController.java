package com.papeleria.inventariopapeleria.controller;

import com.papeleria.inventariopapeleria.dao.ProductoDAO;
import com.papeleria.inventariopapeleria.dao.VariacionColorDAO;
import com.papeleria.inventariopapeleria.model.Producto;
import com.papeleria.inventariopapeleria.model.VariacionColor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;

public class AgregarProductoController {
    @FXML private TextField nombreField;
    @FXML private TextField descripcionField;
    @FXML private TextField codigoField;
    @FXML private TextField precioVentaField;
    @FXML private TextField precioCostoField;
    @FXML private TextField categoriaField;
    @FXML private TextField marcaField;
    @FXML private TextField proveedorField;
    @FXML private TextField cantidadField;
    @FXML private TextField colorField;
    @FXML private ListView<String> coloresList;

    private ProductoDAO productoDAO = new ProductoDAO();
    private VariacionColorDAO variacionColorDAO = new VariacionColorDAO();

    @FXML
    private void agregarColor() {
        String color = colorField.getText();
        if (!color.isEmpty()) {
            coloresList.getItems().add(color);
            colorField.clear();
        }
    }

    @FXML
    private void guardarProducto() {
        double numero1;
        double numero2;
        double numero3;

        try{
            numero1 = Double.parseDouble(precioVentaField.getText());
            try{
                numero2 = Double.parseDouble(precioCostoField.getText());
                try {
                    numero3 = Double.parseDouble(cantidadField.getText());
                    if (coloresList.getItems().isEmpty()) {
                        // Si no hay colores, guardar un solo producto sin variaciones
                        Producto producto = new Producto();
                        producto.setNombre(nombreField.getText());
                        producto.setDescripcion(descripcionField.getText());
                        producto.setCodigoUnico(generarCodigoBusqueda(nombreField.getText()));
                        producto.setPrecioVenta(Double.parseDouble(precioVentaField.getText()));
                        producto.setPrecioCosto(Double.parseDouble(precioCostoField.getText()));
                        producto.setCategoria(categoriaField.getText());
                        producto.setMarca(marcaField.getText());
                        producto.setProveedor(proveedorField.getText());
                        producto.setCantidad(Integer.parseInt(cantidadField.getText()));
                        productoDAO.save(producto);
                    } else {
                        // Si hay colores, crear un producto por cada color
                        for (String color : coloresList.getItems()) {
                            Producto producto = new Producto();
                            producto.setNombre(nombreField.getText() + "-" + color);
                            producto.setDescripcion(descripcionField.getText());
                            producto.setCodigoUnico(generarCodigoBusqueda(nombreField.getText()) + "-" + color);
                            producto.setPrecioVenta(Double.parseDouble(precioVentaField.getText()));
                            producto.setPrecioCosto(Double.parseDouble(precioCostoField.getText()));
                            producto.setCategoria(categoriaField.getText());
                            producto.setMarca(marcaField.getText());
                            producto.setProveedor(proveedorField.getText());
                            producto.setCantidad(Integer.parseInt(cantidadField.getText()));

                            productoDAO.save(producto);
                        }
                    }
                    nombreField.getScene().getWindow().hide();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setContentText("ESTA INGRESANDO UNA LETA EN UN DATO CAMPO NUMERICO");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("ESTA INGRESANDO UNA LETA EN UN DATO CAMPO NUMERICO");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("ESTA INGRESANDO UNA LETA EN UN DATO CAMPO NUMERICO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }

    private String generarCodigoBusqueda(String texto) {
        String[] palabras = texto.toUpperCase().split("\\s+");
        StringBuilder codigo = new StringBuilder();
        boolean primeraPalabraProcesada = false;

        for (String palabra : palabras) {
            // Ignorar palabras como "DE"
            if (palabra.equalsIgnoreCase("DE")) {
                continue;
            }
            // Tomar las primeras 3 letras (o menos si la palabra es más corta)
            int longitud = Math.min(3, palabra.length());
            // Agregar guion antes de la segunda palabra significativa
            if (primeraPalabraProcesada && codigo.length() > 0) {
                codigo.append("-");
            }
            codigo.append(palabra.substring(0, longitud));

            // Si ya procesamos la primera palabra significativa, paramos después de la segunda
            if (primeraPalabraProcesada) {
                break;
            }
            primeraPalabraProcesada = true;
        }

        return codigo.toString();
    }

    private String generarCodigoColor(String color) {
        String colorUpper = color.toUpperCase();
        return colorUpper.length() >= 3 ? colorUpper.substring(0, 3) : colorUpper;
    }
}