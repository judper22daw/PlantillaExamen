package edu.fje.plantillaexamen;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class VisualitzaActivity extends AppCompatActivity {

    private ListView listViewAlumnes;
    private ArrayList<String> alumnesList;
    private ArrayAdapter<String> adapter;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualitza);

        listViewAlumnes = findViewById(R.id.listViewAlumnes);
        alumnesList = new ArrayList<>();

        db = new Database();
        List<Database.Alumne> alumnes = db.dbFindAll(this);

        for (Database.Alumne alumne : alumnes) {
            String infoAlumne = "Nom: " + alumne.getNom() + ", DNI: " + alumne.getDni() + ", Nota: " + alumne.getNota();
            alumnesList.add(infoAlumne);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alumnesList);
        listViewAlumnes.setAdapter(adapter);
    }
}
