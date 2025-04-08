package com.papeleria.inventariopapeleria.controller;

import com.papeleria.inventariopapeleria.dao.PrecioDolarDAO;
import com.papeleria.inventariopapeleria.model.PrecioDolarr;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class PrecioDolar {

    @FXML
    private MFXTextField txtPrecioDolar;

    @FXML
    private Label lblPrecioActual;

    @FXML
    private Label lblUltimaActualizacion;

    @FXML
    private MFXButton btnGuardar;


    private final PrecioDolarDAO precioDolarDAO = new PrecioDolarDAO();
    LocalDate fechaLocal = LocalDate.now();
    @FXML
    public void initialize() {
        // Inicialización si es necesario
    }

    @FXML
    protected void actualizarPrecio() {
        String nuevoPrecio = txtPrecioDolar.getText();

        // Validar que el campo no esté vacío
        if (nuevoPrecio == null || nuevoPrecio.trim().isEmpty()) {
            mostrarAlertaError("Campo Vacío", "Por favor, ingresa un valor.");
            return;
        }

        // Validar que el texto sea un número
        try {
            double precioDouble = Double.parseDouble(nuevoPrecio);

            // Validar que no se haya registrado un precio para el día actual

            if (precioDolarDAO.existePrecioParaFecha(fechaLocal)) {
                mostrarAlertaError("Registro Duplicado", "Ya se ha registrado un precio para hoy.");
                return;
            }

            // Guardar el precio en la base de datos
            PrecioDolarr precioDolarr = new PrecioDolarr();
            precioDolarr.setPrecioDolar(precioDouble);
            precioDolarr.setFechaDolar(java.sql.Date.valueOf(fechaLocal));
            precioDolarDAO.save(precioDolarr);

            // Actualizar la interfaz gráfica
            lblPrecioActual.setText(precioDouble + " Bs");
            lblUltimaActualizacion.setText("Hoy");
            txtPrecioDolar.clear();

            mostrarAlertaInformacion("Éxito", "El precio del dólar ha sido registrado correctamente.");

        } catch (NumberFormatException e) {
            mostrarAlertaError("Valor Inválido", "Por favor, ingresa un número válido.");
        }
    }
    public void eliminarPrecio() {
        if(precioDolarDAO.existePrecioParaFecha(fechaLocal)){
            PrecioDolarr precioDolarr = precioDolarDAO.obtenerIdPorFecha(java.sql.Date.valueOf(fechaLocal));
            precioDolarDAO.delete(precioDolarr);
            mostrarAlertaInformacion("Exito","El precio del dolar ha sido eliminado correctamente");
        }else {
            mostrarAlertaError("Error","No hay precio por eliminar");
        }
    }
    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarAlertaInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}