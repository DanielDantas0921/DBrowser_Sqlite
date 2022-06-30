package com.example.dbbrowserecriaodedbhelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv_utilizadores;
    List<Utilizador> lista_utilizadores;
    DBHelper db;
    Button bt_novoUtilizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        lista_utilizadores = new ArrayList<>();
        lv_utilizadores = findViewById(R.id.lv_utilizadores);
        bt_novoUtilizador = findViewById(R.id.bt_novoUtilizador);


        bt_novoUtilizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NovoUtilizadorActivity.class);
                startActivityForResult(i,1);
            }
        });

        lv_utilizadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,DadosUtilizadorActivity.class);
                i.putExtra("username",lista_utilizadores.get(position).getUsername());
                startActivityForResult(i,2);
            }
        });


        listarUtilizadores();


    }

    private void listarUtilizadores() {
        lista_utilizadores.clear();
        Cursor c = db.SelectAll_Utilizador();
        c.moveToFirst();
        if (c.getCount() > 0) {
            do {

                String username = c.getString(c.getColumnIndex("username"));
                String password = c.getString(c.getColumnIndex("password"));
                lista_utilizadores.add(new Utilizador(username, password));

            } while (c.moveToNext());

        }

        ArrayAdapter<Utilizador> adapter
                = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista_utilizadores);
        lv_utilizadores.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode ==1){
        listarUtilizadores();

        } else if (requestCode == 2 && (resultCode ==1 || resultCode == 2)){
            listarUtilizadores();
        }

    }
}