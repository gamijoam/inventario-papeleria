package com.papeleria.inventariopapeleria.controller;

import com.papeleria.inventariopapeleria.dao.ProductoDAO;
import com.papeleria.inventariopapeleria.model.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.util.List;

public class VentasController {
    @FXML private TextField buscarField;
    @FXML private ListView<String> sugerenciasList; // Cambiado a ListView<String>
    @FXML private TableView<Producto> ventasTable;
    @FXML private TableColumn<Producto, Long> idColumn;
    @FXML private TableColumn<Producto, String> nombreColumn;
    @FXML private TableColumn<Producto, String> codigoColumn;
    @FXML private TableColumn<Producto, Double> precioVentaColumn;
    @FXML private TableColumn<Producto, Integer> cantidadColumn;

    private ProductoDAO productoDAO = new ProductoDAO();
    private List<Producto> productosSugeridos; // Lista para almacenar los objetos Producto

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigoUnico"));
        precioVentaColumn.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    }

    @FXML
    private void buscarProducto() {
        String texto = buscarField.getText();
        sugerenciasList.getItems().clear();
        if (!texto.isEmpty()) {
            productosSugeridos = productoDAO.searchByName(texto); // Guardar los productos encontrados
            for (Producto producto : productosSugeridos) {
                sugerenciasList.getItems().add(producto.getNombre()); // Mostrar solo el nombre
            }
        }
    }

    @FXML
    private void agregarProducto(MouseEvent event) {
        if (event.getClickCount() == 2) { // Doble clic
            int index = sugerenciasList.getSelectionModel().getSelectedIndex();
            if (index >= 0 && index < productosSugeridos.size()) {
                Producto seleccionado = productosSugeridos.get(index);
                if (seleccionado != null && seleccionado.getCantidad() > 0) {
                    ventasTable.getItems().add(seleccionado);
                }
            }
        }
    }

    @FXML
    private void confirmarVenta() {
        for (Producto producto : ventasTable.getItems()) {
            producto.setCantidad(producto.getCantidad() - 1); // Reduce la cantidad
            productoDAO.update(producto);
        }
        ventasTable.getItems().clear();
    }
}