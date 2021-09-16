package com.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Clientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
    }

    public void consultar(View view){
        Intent intent = new Intent(this, ClientesConsultar.class);
        startActivity(intent);

    }

    public void modificar(View view){
        Intent intent = new Intent(this, ClientesModificar.class);
        startActivity(intent);
    }

    public void insertar(View view){
        Intent intent = new Intent(this, ClientesInsertar.class);
        startActivity(intent);
    }
}