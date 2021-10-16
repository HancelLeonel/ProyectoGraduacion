package com.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ClientesConsultar extends AppCompatActivity {

    EditText et_codigo;
    TextView tv_nombre, tv_direccion, tv_telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_consultar);

        et_codigo = findViewById(R.id.et_codigo_c);
        tv_nombre = findViewById(R.id.tv_nombre);
        tv_direccion = findViewById(R.id.tv_direccion);
        tv_telefono = findViewById(R.id.tv_telefono);
    }


    public void Consultar(View view){

    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
    SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

    String codigo = et_codigo.getText().toString();

    if(!codigo.isEmpty()){
        Cursor fila = BaseDeDatos.rawQuery("Select nombre, direccion, telefono from clientes " +
                "where codigo = " + codigo, null);
        if(fila.moveToFirst()){
        tv_nombre.setText(fila.getString(0));
        tv_direccion.setText(fila.getString(1));
        tv_telefono.setText(fila.getString(2));
        }else{

            Toast.makeText(this, "El Cliente no existe", Toast.LENGTH_SHORT).show();
        }
    BaseDeDatos.close();
    }else{

        Toast.makeText(this, "Inserte un codigo", Toast.LENGTH_SHORT).show();
    }


    }
}