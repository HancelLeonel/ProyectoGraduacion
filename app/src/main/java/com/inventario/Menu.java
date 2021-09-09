package com.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void productos(View view){
        Intent intent = new Intent(this, Productos.class);
        startActivity(intent);
    }

    public void clientes(View view){
        Intent intent = new Intent(this, Clientes.class);
        startActivity(intent);
    }

}