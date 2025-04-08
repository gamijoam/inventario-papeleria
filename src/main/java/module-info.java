module com.papeleria.inventariopapeleria {
    requires javafx.web;

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
    opens com.papeleria.inventariopapeleria to javafx.fxml, org.hibernate.orm.core;
    opens com.papeleria.inventariopapeleria.model to org.hibernate.orm.core,javafx.base;
    opens com.papeleria.inventariopapeleria.controller to javafx.fxml, org.hibernate.orm.core;

    // Exporta los paquetes necesarios
    exports com.papeleria.inventariopapeleria;
    exports com.papeleria.inventariopapeleria.controller; // Añadido para que MainController sea accesible
}