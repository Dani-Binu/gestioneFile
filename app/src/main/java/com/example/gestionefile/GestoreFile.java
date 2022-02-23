package com.example.gestionefile;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.style.EasyEditSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class GestoreFile {

    String nomeFile;
    final String TAG = "FileManager";

    public GestoreFile(String nomeFile){
        this.nomeFile = nomeFile;
    }
    public GestoreFile(){

    }

    public String readFile(String nf, Context c){
        String line = "";
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(c.openFileInput(nf)));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            Log.e(TAG, "errore nella lettura");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String WriteFile(String nomeFile, String data, Context c){
        FileOutputStream fos = null;
        //String data = "stringa di prova";
        String esito;
        try {
            //apertura file
            fos = c.openFileOutput(nomeFile, Context.MODE_PRIVATE);
            //scrittura(serializzazione della stringa)
            fos.write(data.getBytes(StandardCharsets.UTF_8));
            //chiusura esplicita
            fos.close();
            Toast.makeText(c.getApplicationContext(), "file scritto con successo", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            esito = "errore nella creazione";
            Log.e(TAG, "errore nella creazione");
        } catch (IOException e) {
            e.printStackTrace();
            esito = "errore nella scrittura";
            Log.e(TAG, "errore nella scrittura");
        }
        return data;
    }

    public String scriviBuffered(String nomeFile, String data, Context c){
        return "xxx";
    }

    public String leggiRawFile(Context c){
        //variabile per le righe da leggere
        String testo="";
        StringBuilder sb = new StringBuilder();
        //recuperare le risorse
        Resources res = c.getResources();
        //recuperare il file aprendolo
        InputStream is = res.openRawResource(R.raw.file);
        //creato stream a partire da inputstream
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //ciclo per leggere il file
        try {
            while ((testo = br.readLine())!=null){
                sb.append(testo + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String leggiAssetsFile(Context c){
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = c.getResources().getAssets().open("file2.txt");
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return returnString.toString();
    }

}
