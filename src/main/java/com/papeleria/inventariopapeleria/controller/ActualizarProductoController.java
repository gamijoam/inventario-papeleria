package com.papeleria.inventariopapeleria.controller;



import com.papeleria.inventariopapeleria.dao.ProductoDAO;
import com.papeleria.inventariopapeleria.model.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ActualizarProductoController {
    @FXML private TableView<Producto> productosTable;
    @FXML private TableColumn<Producto, Long> idColumn;
    @FXML private TableColumn<Producto, String> nombreColumn;
    @FXML private TableColumn<Producto, String> codigoColumn;
    @FXML private TableColumn<Producto, Double> precioVentaColumn;
    @FXML private TableColumn<Producto, Integer> cantidadColumn;
    @FXML private TableColumn<Producto, String> categoriaColumn;
    @FXML private TableColumn<Producto, String> marcaColumn;
    @FXML private TextField nombreField;
    @FXML private TextField descripcionField;
    @FXML private TextField codigoField;
    @FXML private TextField precioVentaField;
    @FXML private TextField precioCostoField;
    @FXML private TextField categoriaField;
    @FXML private TextField marcaField;
    @FXML private TextField proveedorField;
    @FXML private TextField cantidadField;

    private ProductoDAO productoDAO = new ProductoDAO();
    private Producto productoSeleccionado;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigoUnico"));
        precioVentaColumn.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        productosTable.getItems().addAll(productoDAO.getAll());
    }

    @FXML
    private void seleccionarProducto(MouseEvent event) {
        productoSeleccionado = productosTable.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            nombreField.setText(productoSeleccionado.getNombre());
            descripcionField.setText(productoSeleccionado.getDescripcion());
            codigoField.setText(productoSeleccionado.getCodigoUnico());
            precioVentaField.setText(String.valueOf(productoSeleccionado.getPrecioVenta()));
            precioCostoField.setText(String.valueOf(productoSeleccionado.getPrecioCosto()));
            categoriaField.setText(productoSeleccionado.getCategoria());
            marcaField.setText(productoSeleccionado.getMarca());
            proveedorField.setText(productoSeleccionado.getProveedor());
            cantidadField.setText(String.valueOf(productoSeleccionado.getCantidad()));
        }
    }

    @FXML
    private void actualizarProducto() {
        if (productoSeleccionado != null) {
            productoSeleccionado.setNombre(nombreField.getText());
            productoSeleccionado.setDescripcion(descripcionField.getText());
            productoSeleccionado.setCodigoUnico(codigoField.getText());
            productoSeleccionado.setPrecioVenta(Double.parseDouble(precioVentaField.getText()));
            productoSeleccionado.setPrecioCosto(Double.parseDouble(precioCostoField.getText()));
            productoSeleccionado.setCategoria(categoriaField.getText());
            productoSeleccionado.setMarca(marcaField.getText());
            productoSeleccionado.setProveedor(proveedorField.getText());
            productoSeleccionado.setCantidad(Integer.parseInt(cantidadField.getText()));
            productoDAO.update(productoSeleccionado);
            productosTable.refresh();
        }
    }
}