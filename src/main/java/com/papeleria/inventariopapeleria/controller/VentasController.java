package com.papeleria.inventariopapeleria.controller;

import com.papeleria.inventariopapeleria.dao.PrecioDolarDAO;
import com.papeleria.inventariopapeleria.dao.ProductoDAO;
import com.papeleria.inventariopapeleria.model.PrecioDolarr;
import com.papeleria.inventariopapeleria.model.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class VentasController {
    @FXML private TextField buscarField;
    @FXML private ListView<String> sugerenciasList;
    @FXML private TableView<Producto> ventasTable;
    @FXML private TableColumn<Producto, Long> idColumn;
    @FXML private TableColumn<Producto, String> nombreColumn;
    @FXML private TableColumn<Producto, String> codigoColumn;
    @FXML private TableColumn<Producto, Double> precioVentaColumn;
    @FXML private TableColumn<Producto, Integer> cantidadColumn;
    @FXML private TableColumn<Producto, Double> subtotalColumn;
    @FXML private Label precioDolarLabel;
    @FXML private Label totalLabel;

    private ProductoDAO productoDAO = new ProductoDAO();
    private List<Producto> productosSugeridos;
    private double precioDolar;

    @FXML
    public void initialize() {
        precioDolar = obtenerPrecioDolar();
        precioDolarLabel.setText(String.format("%.2f Bs", precioDolar));

        // Configurar las columnas de la tabla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigoUnico"));
        precioVentaColumn.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        // Calcular el subtotal dinámicamente
        subtotalColumn.setCellValueFactory(cellData -> {
            Producto producto = cellData.getValue();
            double subtotal = (producto.getPrecioVenta() * precioDolar) * producto.getCantidad();
            return javafx.beans.binding.Bindings.createObjectBinding(() -> subtotal);
        });

        // Actualizar el total general cuando cambie la tabla
        ventasTable.getItems().addListener((javafx.collections.ListChangeListener<Producto>) change -> actualizarTotal());
    }

    @FXML
    private void buscarProducto() {
        String texto = buscarField.getText();
        sugerenciasList.getItems().clear();
        if (!texto.isEmpty()) {
            productosSugeridos = productoDAO.searchByName(texto);
            for (Producto producto : productosSugeridos) {
                sugerenciasList.getItems().add(producto.getNombre());
            }
        }
    }

    @FXML
    private void agregarProducto(MouseEvent event) {
        if (event.getClickCount() == 2) { // Doble clic
            int index = sugerenciasList.getSelectionModel().getSelectedIndex();
            if (index >= 0 && index < productosSugeridos.size()) {
                Producto seleccionado = productosSugeridos.get(index);

                // Verificar si el producto ya está en la tabla
                Optional<Producto> existente = ventasTable.getItems().stream()
                        .filter(p -> p.getId().equals(seleccionado.getId())) // Compara por ID
                        .findFirst();

                if (existente.isPresent()) {
                    // Incrementar la cantidad si ya existe
                    Producto productoExistente = existente.get();
                    productoExistente.setCantidad(productoExistente.getCantidad() + 1);
                } else {
                    // Agregar el producto a la tabla si es nuevo
                    seleccionado.setCantidad(1); // Inicializar la cantidad en 1
                    ventasTable.getItems().add(seleccionado);
                }

                // Actualizar el total general
                actualizarTotal();
            }
        }
    }

    @FXML
    private void confirmarVenta() {
        for (Producto producto : ventasTable.getItems()) {
            producto.setCantidad(producto.getCantidad() - 1); // Reducir la cantidad
            productoDAO.update(producto);
        }
        ventasTable.getItems().clear();
        actualizarTotal(); // Reiniciar el total general
    }

    private double obtenerPrecioDolar() {
        LocalDate fechaLocal = LocalDate.now();
        PrecioDolarDAO precioDolarDAO = new PrecioDolarDAO();
        PrecioDolarr precioDolarr = precioDolarDAO.obtenerUltimoPrecio();
        if (precioDolarr != null) {
            return precioDolarr.getPrecioDolar();
        }
        return 0.0; // Valor predeterminado si no hay registros
    }

    private void actualizarTotal() {
        double total = ventasTable.getItems().stream()
                .mapToDouble(producto -> (producto.getPrecioVenta() * precioDolar) * producto.getCantidad())
                .sum();
        totalLabel.setText(String.format("%.2f Bs", total));
    }
}