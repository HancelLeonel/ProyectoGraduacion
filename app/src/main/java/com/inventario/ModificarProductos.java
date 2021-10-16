package com.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarProductos extends AppCompatActivity {

    EditText Codigo;
    EditText nombre;
    EditText descripcion;
    EditText precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_productos);

        Codigo = findViewById(R.id.et_codigo_c);
        nombre = findViewById(R.id.et_nombre);
        descripcion = findViewById(R.id.et_direccion);
        precio = findViewById(R.id.et_telefono);
    }

    public void buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String codigo = Codigo.getText().toString();

        if (!codigo.isEmpty()){
            Cursor fila = BaseDeDatabase.rawQuery("select nombre, descripcion, precio " +
                    "from productos where codigo =" + codigo, null);
            if (fila.moveToFirst()){
                nombre.setText(fila.getString(0));
                descripcion.setText(fila.getString(2));
                precio.setText(fila.getString(1));
                BaseDeDatabase.close();
            }else{
                Toast.makeText(this, "No existe el producto", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        }else{
            Toast.makeText(this, "Introducir el codigo", Toast.LENGTH_SHORT).show();
        }

    }

    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        String code = Codigo.getText().toString();
        String name = nombre.getText().toString();
        String price = precio.getText().toString();
        String description = descripcion.getText().toString();

        if (!code.isEmpty() && !description.isEmpty() && !name.isEmpty() && !price.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", code);
            registro.put("nombre", name);
            registro.put("descripcion", description);
            registro.put("precio", price);

            int cantidad = BaseDatabase.update("productos", registro, "codigo=" + code, null);
            BaseDatabase.close();

            if(cantidad ==1){
                Toast.makeText(this, "Articulo modificado correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "El Articulo no existe", Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
}