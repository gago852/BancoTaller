package com.gago.bancotaller;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcCuentas;
    ArrayList<Cuenta> listaCuentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rcCuentas = findViewById(R.id.rcCuentas);

        Intent intentRetorno = getIntent();

        listaCuentas = intentRetorno.getParcelableArrayListExtra("cuentas") == null
                ? new ArrayList<Cuenta>() : intentRetorno.<Cuenta>getParcelableArrayListExtra("cuentas");
        rcCuentas.setLayoutManager(new LinearLayoutManager(this));

        if (listaCuentas.isEmpty()) {
            ArrayList<Cuenta> listaVacia = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                listaVacia.add(new Cuenta("texto de ejemplo 1", "texto de ejemplo2", "texto de ejemplo3", i));
            }
            CuentaAdapter cuentaAdapterVacia = new CuentaAdapter(listaVacia);
            rcCuentas.setAdapter(cuentaAdapterVacia);
        } else {
            CuentaAdapter cuentaAdapter = new CuentaAdapter(listaCuentas);
            rcCuentas.setAdapter(cuentaAdapter);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filtrar_usuario) {
            return true;
        }
        if (id == R.id.action_saldo) {
            Collections.sort(listaCuentas, new Comparator<Cuenta>() {
                @Override
                public int compare(Cuenta o1, Cuenta o2) {
                    if (o1.getSaldo() < o2.getSaldo()){
                        return -1;
                    }
                    if (o1.getSaldo() > o2.getSaldo()){
                        return 1;
                    }
                    return 0;
                }
            });
            CuentaAdapter cuentaAdapter= new CuentaAdapter(listaCuentas);
            rcCuentas.setAdapter(cuentaAdapter);
        }
        if (id == R.id.action_agregar_cuenta) {
            Intent i = new Intent(this, AgregarCuentaActivity.class);
            i.putParcelableArrayListExtra("cuentas", listaCuentas);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
