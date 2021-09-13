package com.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Productos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
    }

    public void insertar(View view){
        Intent intent = new Intent(this, InsertarProductos.class);
        startActivity(intent);

    }

    public void Query(View view){
        Intent intent = new Intent(this, ConsultarProducto.class);
        startActivity(intent);
    }

    public  void Consultar(View view){
        Intent intent = new Intent(this, ModificarProductos.class);
        startActivity(intent);

    }
}