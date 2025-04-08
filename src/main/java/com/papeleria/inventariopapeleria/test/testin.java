package com.papeleria.inventariopapeleria.test;

import java.time.LocalDate;
import java.util.Date;

public class testin {
    public static void main(String[] args) {
        LocalDate fechaLocal = LocalDate.now();
        java.sql.Date fecha = java.sql.Date.valueOf(fechaLocal);
        System.out.println(fecha);
    }
}
