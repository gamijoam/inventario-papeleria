package com.papeleria.inventariopapeleria.controller;



import com.papeleria.inventariopapeleria.dao.ProductoDAO;
import com.papeleria.inventariopapeleria.model.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class EliminarProductoController {
    @FXML private TableView<Producto> productosTable;
    @FXML private TableColumn<Producto, Long> idColumn;
    @FXML private TableColumn<Producto, String> nombreColumn;
    @FXML private TableColumn<Producto, String> codigoColumn;
    @FXML private TableColumn<Producto, Double> precioVentaColumn;
    @FXML private TableColumn<Producto, Integer> cantidadColumn;
    @FXML private TableColumn<Producto, String> categoriaColumn;
    @FXML private TableColumn<Producto, String> marcaColumn;

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
        productosTable.getItems().addAll(productoDAO.getAll());
    }

    @FXML
    private void eliminarProducto() {
        Producto seleccionado = productosTable.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            productoDAO.delete(seleccionado);
            productosTable.getItems().remove(seleccionado);
        }
    }
}