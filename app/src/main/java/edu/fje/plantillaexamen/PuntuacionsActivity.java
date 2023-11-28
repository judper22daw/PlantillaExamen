package edu.fje.plantillaexamen;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class PuntuacionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacions);
    }

    public void volverAMain(View view) {
        finish();
    }
    @Override
    public void onBackPressed() {
        // Puedes agregar lógica adicional aquí si lo necesitas
        super.onBackPressed();
    }

}
