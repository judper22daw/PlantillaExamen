package edu.fje.plantillaexamen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AlumnesActivity extends AppCompatActivity {

    private EditText editTextNom, editTextDNI, editTextNota;
    private Button buttonAfegir;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnes);

        editTextNom = findViewById(R.id.editTextNom);
        editTextDNI = findViewById(R.id.editTextDNI);
        editTextNota = findViewById(R.id.editTextNota);
        buttonAfegir = findViewById(R.id.buttonAfegir);

        db = new Database();
        db.dBCreate(this);

        buttonAfegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = editTextNom.getText().toString();
                String dni = editTextDNI.getText().toString();
                String nota = editTextNota.getText().toString();

                // Inserta los datos en la base de datos
                db.dbInsert(AlumnesActivity.this, nom, dni, nota);

                // Opcionalmente, puedes iniciar otra actividad o actualizar la UI
                // Ejemplo: Iniciar VisualitzaActivity
                // Intent intent = new Intent(AlumnesActivity.this, VisualitzaActivity.class);
                // startActivity(intent);
            }
        });
    }
}
