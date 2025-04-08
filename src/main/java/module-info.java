module com.papeleria.inventariopapeleria {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.base;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;
    requires MaterialFX;

    // Abre paquetes a JavaFX y Hibernate para reflexión
    opens com.papeleria.inventariopapeleria to javafx.fxml, org.hibernate.orm.core, com.jfoenix;
    opens com.papeleria.inventariopapeleria.model to org.hibernate.orm.core, javafx.base, com.jfoenix;
    opens com.papeleria.inventariopapeleria.controller to javafx.fxml, org.hibernate.orm.core, com.jfoenix;

    // Exporta los paquetes necesarios
    exports com.papeleria.inventariopapeleria;
    exports com.papeleria.inventariopapeleria.controller;
}