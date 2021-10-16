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

        et_codigo = findViewById(R.id.et_codigo_c);
        et_nombre = findViewById(R.id.et_nombre);
        et_direccion = findViewById(R.id.et_direccion);
        et_telefono = findViewById(R.id.et_telefono);
    }

    public void Insertar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo =et_codigo.getText().toString();
        String nombre = et_nombre.getText().toString();
        String direccion = et_direccion.getText().toString();
        String telefono = et_telefono.getText().toString();

        if(!codigo.isEmpty() && !nombre.isEmpty() && !direccion.isEmpty() && !telefono.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("nombre", nombre);
            registro.put("direccion", direccion);
            registro.put("telefono", telefono);

            BaseDeDatos.insert("clientes", null, registro);
            BaseDeDatos.close();

            et_codigo.setText("");
            et_nombre.setText("");
            et_direccion.setText("");
            et_telefono.setText("");
            Toast.makeText(this, "Cliente insertado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }


    }
}