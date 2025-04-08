package com.papeleria.inventariopapeleria.controller;

import com.papeleria.inventariopapeleria.dao.PrecioDolarDAO;
import com.papeleria.inventariopapeleria.dao.ProductoDAO;
import com.papeleria.inventariopapeleria.model.PrecioDolarr;
import com.papeleria.inventariopapeleria.model.Producto;
import com.papeleria.inventariopapeleria.model.ProductoObservable;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class VentasController {
    @FXML private TextField buscarField;
    @FXML private ListView<String> sugerenciasList;
    @FXML private TableView<ProductoObservable> ventasTable;
    @FXML private TableColumn<ProductoObservable, Long> idColumn;
    @FXML private TableColumn<ProductoObservable, String> nombreColumn;
    @FXML private TableColumn<ProductoObservable, String> codigoColumn;
    @FXML private TableColumn<ProductoObservable, Double> precioVentaColumn;
    @FXML private TableColumn<ProductoObservable, Integer> cantidadColumn;
    @FXML private TableColumn<ProductoObservable, Double> subtotalColumn;
    @FXML private Label precioDolarLabel;
    @FXML private Label totalLabel;

    private ProductoDAO productoDAO = new ProductoDAO();
    private List<Producto> productosSugeridos;
    private double precioDolar;

    @FXML
    public void initialize() {
        ventasTable.setEditable(true);
        precioDolar = obtenerPrecioDolar();
        precioDolarLabel.setText(String.format("%.2f Bs", precioDolar));

        // Configurar las columnas de la tabla
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        codigoColumn.setCellValueFactory(cellData -> cellData.getValue().codigoUnicoProperty());
        precioVentaColumn.setCellValueFactory(cellData -> cellData.getValue().precioVentaProperty().asObject());

        // Cantidad (editable)
        cantidadColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        cantidadColumn.setOnEditCommit(event -> {
            ProductoObservable producto = event.getRowValue();
            try {
                int nuevaCantidad = Integer.parseInt(String.valueOf(event.getNewValue()));
                if (nuevaCantidad > 0) {
                    producto.setCantidad(nuevaCantidad); // Actualizar la propiedad observable
                } else {
                    throw new NumberFormatException("La cantidad debe ser mayor a cero.");
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Entrada Inválida");
                alert.setContentText("Por favor, ingrese un número válido mayor a cero.");
                alert.showAndWait();
            }

            // Actualizar el total manualmente
            actualizarTotal();
        });

        // Configurar la columna de subtotal
        subtotalColumn.setCellValueFactory(cellData -> {
            ProductoObservable producto = cellData.getValue();
            return Bindings.createDoubleBinding(() ->
                            producto.getCantidad() * producto.getPrecioVenta() * precioDolar,
                    producto.cantidadProperty(),
                    producto.precioVentaProperty()
            ).asObject();
        });

// Formatear el subtotal para mostrar dos decimales en la celda
        subtotalColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f Bs", item)); // Limitar a dos decimales y agregar "Bs"
                }
            }
        });
        actualizarTotal();
    }

    @FXML
    private void agregarProducto(MouseEvent event) {
        if (event.getClickCount() == 2) { // Doble clic
            int index = sugerenciasList.getSelectionModel().getSelectedIndex();
            if (index >= 0 && index < productosSugeridos.size()) {
                Producto seleccionado = productosSugeridos.get(index);

                Optional<ProductoObservable> existente = ventasTable.getItems().stream()
                        .filter(p -> p.getId() == seleccionado.getId())
                        .findFirst();

                if (existente.isPresent()) {
                    return; // No hacer nada si el producto ya existe
                } else {
                    ProductoObservable productoObservable = seleccionado.toProductoObservable();
                    productoObservable.setCantidad(1); // Inicializar la cantidad en 1
                    ventasTable.getItems().add(productoObservable);
                }

                // Actualizar el total manualmente
                actualizarTotal();
            }
        }
    }

    @FXML
    private void confirmarVenta() {
        for (ProductoObservable producto : ventasTable.getItems()) {
            Producto productoEntity = producto.toProducto(); // Convertir a Producto
            if (productoEntity.getCantidad() > 0) {
                productoEntity.setCantidad(productoEntity.getCantidad() - 1); // Reducir la cantidad
                productoDAO.update(productoEntity);
            }
        }
        ventasTable.getItems().clear();

        // Actualizar el total manualmente
        actualizarTotal();
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

    // Método para actualizar el total manualmente
    private void actualizarTotal() {
        double total = ventasTable.getItems().stream()
                .mapToDouble(producto -> producto.getCantidad() * producto.getPrecioVenta() * precioDolar)
                .sum();
        totalLabel.setText(String.format("%.2f Bs", total)); // Actualizar el texto del total
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
}