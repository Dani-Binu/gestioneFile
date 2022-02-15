package com.example.gestionefile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLeggi;
    Button btnScrivi;
    TextView txtContenuto;
    EditText editText;
    GestoreFile gf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLeggi = findViewById(R.id.leggi);
        btnScrivi = findViewById(R.id.scrivi);
        txtContenuto = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

        gf = new GestoreFile();
        btnLeggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mess = gf.readFile("fileDaLeggere.txt", getApplicationContext());
                txtContenuto.setText(mess);
                Toast.makeText(getApplicationContext(), "testo letto correttamente", Toast.LENGTH_LONG).show();
            }
        });
        btnScrivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gf.WriteFile("fileDaLeggere.txt", editText.getText().toString(), getApplicationContext());
            }
        });
    }
}