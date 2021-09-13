package com.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultarProducto extends AppCompatActivity {

    EditText et_codigo;
    TextView tv_nombre, tv_descripcion, tv_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_producto);

        et_codigo = findViewById(R.id.codigo);
        tv_nombre = findViewById(R.id.tv_nombre);
        tv_descripcion = findViewById(R.id.tv_descripcion);
        tv_precio = findViewById(R.id.tv_precio);
    }

    public void Query(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if (!codigo.isEmpty()){
            Cursor fila = BaseDeDatabase.rawQuery("select nombre, descripcion, precio " +
                    "from productos where codigo =" + codigo, null);
            if (fila.moveToFirst()){
                tv_nombre.setText(fila.getString(0));
                tv_precio.setText(fila.getString(2));
                tv_descripcion.setText(fila.getString(1));
                BaseDeDatabase.close();
            }else{
                Toast.makeText(this, "No existe el producto", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        }else{
            Toast.makeText(this, "Introducir el codigo", Toast.LENGTH_SHORT).show();
        }
    }

    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper
                (this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if (!codigo.isEmpty()){
            int cantidad = BaseDeDatabase.delete("productos", "codigo=" + codigo, null);
            BaseDeDatabase.close();

            et_codigo.setText("");
            tv_descripcion.setText("");
            tv_precio.setText("");
            tv_nombre.setText("");

            if (cantidad==1){
                Toast.makeText(this, "Articulo eliminado", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "Articulo no existe", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Debe ingresar un codigo", Toast.LENGTH_SHORT).show();
        }



    }
}