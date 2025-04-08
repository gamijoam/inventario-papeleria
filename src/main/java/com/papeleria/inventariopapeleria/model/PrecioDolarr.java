package com.papeleria.inventariopapeleria.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="preciodolar")
public class PrecioDolarr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrecioDolar;
    private double precioDolar;
    private Date fechaDolar;

    public PrecioDolarr() {
    }

    public int getIdPrecioDolar() {
        return idPrecioDolar;
    }

    public void setIdPrecioDolar(int idPrecioDolar) {
        this.idPrecioDolar = idPrecioDolar;
    }

    public double getPrecioDolar() {
        return precioDolar;
    }

    public void setPrecioDolar(double precioDolar) {
        this.precioDolar = precioDolar;
    }

    public Date getFechaDolar() {
        return fechaDolar;
    }

    public void setFechaDolar(Date fechaDolar) {
        this.fechaDolar = fechaDolar;
    }
}
