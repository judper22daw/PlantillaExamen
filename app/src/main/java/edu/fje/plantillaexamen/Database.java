package edu.fje.plantillaexamen;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private final String BASE_DADES = "alumnesDatabase";
    private final String TAULA = "alumnes";

    public void dBCreate(Activity activity) {
        SQLiteDatabase baseDades = null;
        try {
            baseDades = activity.openOrCreateDatabase(BASE_DADES, MODE_PRIVATE, null);
            baseDades.execSQL("CREATE TABLE IF NOT EXISTS " + TAULA + " (nom TEXT, dni TEXT, nota TEXT);");
            Log.d("MiApp", "Tabla creada con éxito");
        } catch (SQLException e) {
            Log.e("MiApp", "Error al crear la tabla: " + e.getMessage());
        } finally {
            if (baseDades != null) {
                baseDades.close();
            }
        }
    }

    public void dbInsert(Activity activity, String nom, String dni, String nota) {
        SQLiteDatabase baseDades = null;
        try {
            baseDades = activity.openOrCreateDatabase(BASE_DADES, MODE_PRIVATE, null);
            String insertQuery = "INSERT INTO " + TAULA + " (nom, dni, nota) VALUES (?, ?, ?)";
            baseDades.execSQL(insertQuery, new Object[]{nom, dni, nota});
            Log.d("MiApp", "Datos insertados con éxito");
        } catch (SQLException e) {
            Log.e("MiApp", "Error al insertar datos: " + e.getMessage());
        } finally {
            if (baseDades != null) {
                baseDades.close();
            }
        }
    }

    public List<Alumne> dbFindAll(Activity activity) {
        List<Alumne> alumneList = new ArrayList<>();
        SQLiteDatabase baseDades = null;

        try {
            baseDades = activity.openOrCreateDatabase(BASE_DADES, MODE_PRIVATE, null);
            Cursor c = baseDades.rawQuery("SELECT nom, dni, nota FROM " + TAULA, null);

            int columnaNom = c.getColumnIndex("nom");
            int columnaDNI = c.getColumnIndex("dni");
            int columnaNota = c.getColumnIndex("nota");

            if (c != null && c.moveToFirst()) {
                do {
                    Alumne alumne = new Alumne(c.getString(columnaNom), c.getString(columnaDNI), c.getString(columnaNota));
                    alumneList.add(alumne);
                } while (c.moveToNext());
            }
            c.close();
            Log.d("MiApp", "Datos recuperados con éxito");
        } catch (SQLException e) {
            Log.e("MiApp", "Error al recuperar datos: " + e.getMessage());
        } finally {
            if (baseDades != null) {
                baseDades.close();
            }
        }

        return alumneList;
    }

    // Clase Alumne
    public static class Alumne {
        private String nom;
        private String dni;
        private String nota;

        public Alumne(String nom, String dni, String nota) {
            this.nom = nom;
            this.dni = dni;
            this.nota = nota;
        }

        // Método getter para 'nom'
        public String getNom() {
            return nom;
        }

        // Método getter para 'dni'
        public String getDni() {
            return dni;
        }

        // Método getter para 'nota'
        public String getNota() {
            return nota;
        }
    }
    public String getStudentsDataInJson(Activity activity) {
        List<Alumne> alumnes = dbFindAll(activity);
        StringBuilder jsonBuilder = new StringBuilder();

        jsonBuilder.append("[");
        for (int i = 0; i < alumnes.size(); i++) {
            Alumne alumne = alumnes.get(i);
            jsonBuilder.append("{")
                    .append("\"nom\": \"").append(alumne.getNom()).append("\", ")
                    .append("\"dni\": \"").append(alumne.getDni()).append("\", ")
                    .append("\"nota\": \"").append(alumne.getNota()).append("\"}");
            if (i < alumnes.size() - 1) {
                jsonBuilder.append(", ");
            }
        }
        jsonBuilder.append("]");

        return jsonBuilder.toString();
    }


}
