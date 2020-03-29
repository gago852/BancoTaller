package com.gago.bancotaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AgregarCuentaActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText edNombre, edNroCuenta, edSaldo;
    RadioGroup radioGroup;
    RadioButton rbAhorro, rbCorriente;
    Button btGuardar, btCancelar, btRegresar;

    ArrayList<Cuenta> listaCuentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cuenta);

        Intent intent = getIntent();

        listaCuentas = intent.getParcelableArrayListExtra("cuentas") == null
                ? new ArrayList<Cuenta>() : intent.<Cuenta>getParcelableArrayListExtra("cuentas");

        edNombre = findViewById(R.id.idEdNombre);
        edNroCuenta = findViewById(R.id.idEdNroCuenta);
        edSaldo = findViewById(R.id.idEdSaldo);
        radioGroup = findViewById(R.id.radioGroup);
        rbAhorro = findViewById(R.id.idRbAhorro);
        rbCorriente = findViewById(R.id.idRbCorriente);
        btGuardar = findViewById(R.id.idBtGuardar);
        btCancelar = findViewById(R.id.idBtCancelar);
        btRegresar = findViewById(R.id.idBtRegresar);

        btGuardar.setOnClickListener(this);
        btRegresar.setOnClickListener(this);
        btCancelar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idBtGuardar:
                String tipoCuenta;
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.idRbAhorro:
                        tipoCuenta = getString(R.string.tipoAhorro);
                        break;
                    case R.id.idRbCorriente:
                        tipoCuenta = getString(R.string.tipoCorriente);
                        break;
                    default:
                        tipoCuenta = getString(R.string.tipoCorriente);
                        break;
                }
                double saldo = edSaldo.getText().toString().isEmpty() ? 0 : Double.parseDouble(edSaldo.getText().toString());
                Cuenta cuenta = new Cuenta(edNroCuenta.getText().toString(), edNombre.getText().toString()
                        , tipoCuenta, saldo);
                listaCuentas.add(cuenta);
                limpiarCampo();
                Toast.makeText(getApplicationContext(),"Guardaro",Toast.LENGTH_SHORT).show();
                break;
            case R.id.idBtCancelar:
                limpiarCampo();
                break;
            case R.id.idBtRegresar:
                Intent i = new Intent(this, MainActivity.class);
                i.putParcelableArrayListExtra("cuentas", listaCuentas);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
        }
    }

    private void limpiarCampo(){
        edNombre.setText("");
        edSaldo.setText("");
        edNroCuenta.setText("");
    }
}
