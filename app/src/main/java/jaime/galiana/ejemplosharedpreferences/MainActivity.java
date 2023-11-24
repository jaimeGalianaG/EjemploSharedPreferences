package jaime.galiana.ejemplosharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtEdad;
    private Button btnGuardar;
    private Button btnBorrarTodo;
    private ImageButton btnBorrarNombre;
    private ImageButton btnBorrarEdad;
    private SharedPreferences sp;
    private Button btnJSon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVista();

        sp = getSharedPreferences(Constantes.PERSONA, MODE_PRIVATE);

        rellenarDatos();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtNombre.getText().toString().isEmpty() || txtEdad.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }else {
                    String nombre = txtNombre.getText().toString();
                    int edad = Integer.parseInt(txtEdad.getText().toString());

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(Constantes.NOMBRE, nombre);
                    editor.putInt(Constantes.EDAD, edad);
                    editor.apply();

                    txtNombre.setText("");
                    txtEdad.setText("");
                    Toast.makeText(MainActivity.this, "DATOS GUARDADOS", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnBorrarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();
                txtNombre.setText("");
                txtEdad.setText("");
            }
        });
        btnBorrarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.remove(Constantes.NOMBRE);
                editor.apply();
                txtNombre.setText("");
            }
        });
        btnBorrarEdad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.remove(Constantes.EDAD);
                editor.apply();
                txtEdad.setText("");
            }
        });
        btnJSon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JSonActivity.class);
                startActivity(intent);
            }
        });
    }

    private void rellenarDatos() {
        String nombre = sp.getString(Constantes.NOMBRE, "");
        int edad = sp.getInt(Constantes.EDAD, -1);

        if (!nombre.isEmpty()){
            txtNombre.setText(nombre);
        }
        if (edad != -1){
            txtEdad.setText(String.valueOf(edad));
        }
    }

    private void inicializarVista() {
        txtNombre = findViewById(R.id.txtNombreMain);
        txtEdad = findViewById(R.id.txtEdadMain);
        btnGuardar = findViewById(R.id.btnGuardarMain);

        btnBorrarTodo = findViewById(R.id.btnBorrarDatosMain);
        btnBorrarNombre = findViewById(R.id.btnBorrarNombreMain);
        btnBorrarEdad = findViewById(R.id.btnBorrarEdadMain);

        btnJSon = findViewById(R.id.btnJSON);
    }
}