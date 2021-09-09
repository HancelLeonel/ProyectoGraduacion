package com.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarProductos extends AppCompatActivity {

    private EditText et_codigo, et_nombre, et_descripcion, et_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_productos);

        et_codigo = findViewById(R.id.codigo);
        et_precio = findViewById(R.id.precio);
        et_nombre = findViewById(R.id.nombre);
        et_descripcion = findViewById(R.id.descripcion);
    }

    public void Agregar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String nombre = et_nombre.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if (!codigo.isEmpty() && !nombre.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("nombre", nombre);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            BaseDeDatos.insert("productos", null, registro);

            BaseDeDatos.close();
            et_codigo.setText("");
            et_descripcion.setText("");
            et_nombre.setText("");
            et_precio.setText("");
            Toast.makeText(this, "Producto insertado", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "Por favor llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
}