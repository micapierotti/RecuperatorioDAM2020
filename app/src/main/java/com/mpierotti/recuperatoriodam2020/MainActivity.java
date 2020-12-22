package com.mpierotti.recuperatoriodam2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button bt_mapa;
    private EditText ciudad;
    private Spinner spinner;
    private List<String> resultado = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_mapa = findViewById(R.id.bt_mapa);
        ciudad = findViewById(R.id.editTextCiudad);
        spinner = findViewById(R.id.spinner);

        resultado.add(ciudad.getText().toString());
        resultado.add(spinner.getSelectedItem().toString());

        bt_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapaActivity.class);
                i.putStringArrayListExtra("ciudad", (ArrayList<String>) resultado);
                startActivity(i);
            }
        });
    }
}