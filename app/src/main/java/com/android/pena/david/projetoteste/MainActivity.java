package com.android.pena.david.projetoteste;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listaCategorias;
    private String categoriesEndPoint = "https://api.themoviedb.org/3/movie/popular?api_key=a23e1e6c36e4f42b4595e036ab812329";

    //Cria a activity e executa a chamada à API
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCategorias = (RecyclerView) findViewById(R.id.lista_categorias);
        setTitle("Filmes");
        new getCategoriesAsync().execute();
    }



    private class getCategoriesAsync extends AsyncTask<Void,Void,String>{

        //Metodo Asincrono para fazer a chamada da API
        @Override
        protected String doInBackground(Void... voids) {

            try{
                URL url = new URL(categoriesEndPoint);
                String response = getResponseFromHttpUrl(url);
                return response;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s == null) return;

            try {
                JSONObject obj = new JSONObject(s);
                String filmes = obj.getString("results");
                JSONArray array = new JSONArray(filmes);
                listaCategorias.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                CategoriasAdapter adapter = new CategoriasAdapter(getApplicationContext(),array);
                listaCategorias.setAdapter(adapter);
                //Log.e("TAGGG", array.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //Metodo que gera uma conexão HTTP e processa o inputStream em uma String
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        Log.e("",urlConnection.getResponseCode()+"");
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
