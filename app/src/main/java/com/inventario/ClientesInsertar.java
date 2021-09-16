package com.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClientesInsertar extends AppCompatActivity {

    EditText et_codigo, et_nombre, et_direccion, et_telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_insertar);

        et_codigo = findViewById(R.id.et_codigo);
        et_nombre = findViewById(R.id.et_nombre);
        et_direccion = findViewById(R.id.et_direccion);
        et_telefono = findViewById(R.id.et_telefono);
    }

    public void Agregar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String nombre = et_nombre.getText().toString();
        String descripcion = et_direccion.getText().toString();
        String precio = et_telefono.getText().toString();

        if (!codigo.isEmpty() && !nombre.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("nombre", nombre);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            BaseDeDatos.insert("clientes", null, registro);

            BaseDeDatos.close();
            et_codigo.setText("");
            et_direccion.setText("");
            et_nombre.setText("");
            et_telefono.setText("");
            Toast.makeText(this, "Cliente insertado", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "Por favor llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
}