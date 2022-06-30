package com.example.dbbrowserecriaodedbhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NovoUtilizadorActivity extends AppCompatActivity {

    EditText et_username,et_password;
    Button bt_gravar, bt_cancelar;
    Intent i;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_utilizador);
        et_password = findViewById(R.id.et_NovoUtilizador_password);
        et_username =findViewById(R.id.et_NovoUtilizador_username);
        bt_cancelar = findViewById(R.id.bt_NovoUtilizador_cancelar);
        bt_gravar = findViewById(R.id.bt_NovoUtilizador_gravar);
        i = getIntent();

        db = new DBHelper(this);



        bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setResult(0,i);
                finish();
            }
        });


        bt_gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username =et_username.getText().toString();
                String password = et_password.getText().toString();



                if(!username.trim().isEmpty() && !password.trim().isEmpty()){


                   long res =  db.Insert_Utilizador(username,password);
                           if(res>0){
                               Toast.makeText(NovoUtilizadorActivity.this,
                                       "Utilizador inserido com sucesso", Toast.LENGTH_SHORT).show();
                               setResult(1,i);
                               finish();

                           } else {
                               Toast.makeText(NovoUtilizadorActivity.this,
                                       "Erro ao inserir o utilizador", Toast.LENGTH_SHORT).show();
                           }

                }




            }
        });








    }
}