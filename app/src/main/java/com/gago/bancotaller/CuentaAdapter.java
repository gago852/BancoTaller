package com.gago.bancotaller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CuentaAdapter extends RecyclerView.Adapter<CuentaAdapter.ViewHolderCuenta> {

    ArrayList<Cuenta> lista;

    public CuentaAdapter(ArrayList<Cuenta> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolderCuenta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_layout, null, false);
        return new ViewHolderCuenta(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCuenta holder, int position) {
        holder.txtNombre.setText(lista.get(position).getNombreCliente());
        holder.txtNroCuenta.setText(lista.get(position).getNroCuenta());
        holder.txtTipoCuenta.setText(lista.get(position).getTipoCuenta());
        holder.txtSaldo.setText(String.valueOf(lista.get(position).getSaldo()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolderCuenta extends RecyclerView.ViewHolder {

        TextView txtNombre, txtTipoCuenta, txtSaldo, txtNroCuenta;

        public ViewHolderCuenta(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtNroCuenta = itemView.findViewById(R.id.txtNroCuenta);
            txtTipoCuenta = itemView.findViewById(R.id.txtTipoCuenta);
            txtSaldo = itemView.findViewById(R.id.txtSaldo);
        }
    }
}
