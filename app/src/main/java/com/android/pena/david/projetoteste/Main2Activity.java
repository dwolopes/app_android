package com.android.pena.david.projetoteste;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import static android.R.attr.category;

public class Main2Activity extends AppCompatActivity {

    private TextView categoriaObj;
    private ImageView imgName;
    private Context context;
    private JSONArray filme_array;
    private TextView categoria_overview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        categoriaObj = (TextView) findViewById(R.id.categoria_name);

        //Adiciona backbutton para voltar para o pai
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //pega a string que veio da intenção
        String obj = getIntent().getStringExtra("CATEGORIA_TAG");

        if(obj != null){
            categoriaObj.setText(obj);
            String overview = obj.getString("overview");
            categoria_overview.setText(filme_array.getString("title"));
        }

        //String posterPath = "https://image.tmdb.org/t/p/w300_and_h450_bestv2"+ filme_array.getString("poster_path");
        //Picasso.with(context).load(posterPath).into(imgName);

    }
}
