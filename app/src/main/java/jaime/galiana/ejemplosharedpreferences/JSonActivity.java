package jaime.galiana.ejemplosharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import jaime.galiana.ejemplosharedpreferences.modelos.ContactosMatricula;

public class JSonActivity extends AppCompatActivity {

    private TextView lbResultado;
    private Button btnGuardar;
    private Button btnCargar;
    private List<ContactosMatricula> contactos;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        contactos = new ArrayList<>();

        crearContactos();

        sp = getSharedPreferences(Constantes.CONTACTOS, MODE_PRIVATE);

        inicializarVista();

        //guardar en json info
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datosJSon = new Gson().toJson(contactos);

                SharedPreferences.Editor editor = sp.edit();
                editor.putString(Constantes.DATOS, datosJSon);
                editor.apply();
            }
        });
        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Type tipo = new TypeToken<ArrayList<ContactosMatricula>>(){}.getType();
                ArrayList<ContactosMatricula> temp =
                        new Gson().fromJson(sp.getString(Constantes.DATOS, "[]"), tipo);
                contactos.clear();
                contactos.addAll(temp);
                lbResultado.setText("Elementos: "+contactos.size());
            }
        });

    }

    private void crearContactos() {
        for (int i = 0; i < 10; i++) {
            contactos.add(new ContactosMatricula("Nombre "+i, "Ciclo "+i, "Email "+i, "Telefono "+i));
        }
    }

    private void inicializarVista() {
        lbResultado = findViewById(R.id.lbResultadoJSON);
        btnGuardar = findViewById(R.id.btnGuardarJSON);
        btnCargar = findViewById(R.id.btnCargarJSON);
    }
}