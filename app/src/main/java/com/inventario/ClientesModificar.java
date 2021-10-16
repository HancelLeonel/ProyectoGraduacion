package com.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClientesModificar extends AppCompatActivity {

    EditText Codigo, Nombre, Direccion, Telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_modificar);

        Codigo = findViewById(R.id.et_codigo_c);
        Nombre = findViewById(R.id.et_nombre);
        Direccion = findViewById(R.id.et_direccion);
        Telefono = findViewById(R.id.et_telefono);
    }

    public void Consultar(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = Codigo.getText().toString();

        if(!codigo.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("Select nombre, direccion, telefono from clientes " +
                    "where codigo = " + codigo, null);
            if(fila.moveToFirst()){
                Nombre.setText(fila.getString(0));
                Direccion.setText(fila.getString(1));
                Telefono.setText(fila.getString(2));
            }else{

                Toast.makeText(this, "El Producto no existe", Toast.LENGTH_SHORT).show();
            }
            BaseDeDatos.close();
        }else{

            Toast.makeText(this, "Inserte un codigo", Toast.LENGTH_SHORT).show();
        }


    }


    public void Modificar(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = Codigo.getText().toString();
        String nombre = Nombre.getText().toString();
        String direccion = Direccion.getText().toString();
        String telefono = Telefono.getText().toString();

        if (!nombre.isEmpty() && !direccion.isEmpty() && !telefono.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("nombre", nombre);
            registro.put("direccion", direccion);
            registro.put("telefono", telefono);

            int cantidad = BaseDeDatos.update("clientes", registro, 
                    "codigo=" + codigo, null);
            BaseDeDatos.close();
            
            if(cantidad==0){
                Toast.makeText(this, "Cliente modificado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se modificó el cliente", Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();

        }


    }
}