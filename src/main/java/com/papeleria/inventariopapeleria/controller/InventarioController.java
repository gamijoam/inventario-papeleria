package com.papeleria.inventariopapeleria.controller;



import com.papeleria.inventariopapeleria.dao.ProductoDAO;
import com.papeleria.inventariopapeleria.model.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class InventarioController {
    @FXML private TableView<Producto> inventarioTable;
    @FXML private TableColumn<Producto, Long> idColumn;
    @FXML private TableColumn<Producto, String> nombreColumn;
    @FXML private TableColumn<Producto, String> codigoColumn;
    @FXML private TableColumn<Producto, Double> precioVentaColumn;
    @FXML private TableColumn<Producto, Integer> cantidadColumn;
    @FXML private TableColumn<Producto, String> categoriaColumn;
    @FXML private TableColumn<Producto, String> marcaColumn;
    @FXML private TableColumn<Producto, String> proveedorColumn;

    private ProductoDAO productoDAO = new ProductoDAO();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigoUnico"));
        precioVentaColumn.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        proveedorColumn.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        inventarioTable.getItems().addAll(productoDAO.getAll());
    }
}