package com.gago.bancotaller;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Cuenta implements Parcelable {
    private String nroCuenta;
    private String nombreCliente;
    private String tipoCuenta;
    private double saldo;

    public Cuenta(String nroCuenta, String nombreCliente, String tipoCuenta, double saldo) {
        this.nroCuenta = nroCuenta;
        this.nombreCliente = nombreCliente;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
    }

    protected Cuenta(Parcel in) {
        nroCuenta = in.readString();
        nombreCliente = in.readString();
        tipoCuenta = in.readString();
        saldo = in.readDouble();
    }

    public static final Creator<Cuenta> CREATOR = new Creator<Cuenta>() {
        @Override
        public Cuenta createFromParcel(Parcel in) {
            return new Cuenta(in);
        }

        @Override
        public Cuenta[] newArray(int size) {
            return new Cuenta[size];
        }
    };

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nroCuenta);
        dest.writeString(nombreCliente);
        dest.writeString(tipoCuenta);
        dest.writeDouble(saldo);
    }

    @NonNull
    @Override
    public String toString() {
        return "Estudiante{" +
                "numero de cuenta='" + nroCuenta + '\'' +
                ", nombre='" + nombreCliente + '\'' +
                ", tipo de cuenta='" + tipoCuenta + '\'' +
                ", saldo='" + saldo + '\'' +
                '}';
    }
}
