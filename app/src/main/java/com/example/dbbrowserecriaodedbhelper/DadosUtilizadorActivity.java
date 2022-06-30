package com.example.dbbrowserecriaodedbhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DadosUtilizadorActivity extends AppCompatActivity {

    EditText et_username, et_password;
    Button bt_cancelar, bt_eliminar, bt_modificar;
    Intent i;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_utilizador);

        et_password = findViewById(R.id.et_DadosUtilizador_password);
        et_username = findViewById(R.id.et_DadosUtilizador_username);
        bt_cancelar = findViewById(R.id.bt_DadosUtilizador_cancelar);
        bt_eliminar = findViewById(R.id.bt_DadosUtilizador_eliminar);
        bt_modificar = findViewById(R.id.bt_DadosUtilizador_modificar);

        i = getIntent();
        String username = i.getExtras().getString("username");

        db = new DBHelper(this);

        carregarDadosUtilizador();


        bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!et_password.getText().toString().trim().isEmpty()) {
                    long res = db.Update_Utilizador(username, et_password.getText().toString());

                    if (res > 0) {
                        Toast.makeText(DadosUtilizadorActivity.this, "Utilizador alterado com sucesso", Toast.LENGTH_SHORT).show();
                        setResult(1, i);
                        finish();

                    } else {
                        Toast.makeText(DadosUtilizadorActivity.this, "Erro ao alterar o utilizador", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DadosUtilizadorActivity.this, "Deve inserir uma password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0, i);
                finish();

            }
        });

        bt_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                long res = db.Delete_Utilizador(username);

                if (res > 0) {
                    Toast.makeText(DadosUtilizadorActivity.this, "Utilizador eliminado com sucesso", Toast.LENGTH_SHORT).show();
                    setResult(1, i);
                    finish();

                } else {
                    Toast.makeText(DadosUtilizadorActivity.this, "Erro ao deletar o utilizador", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void carregarDadosUtilizador() {
        String username = i.getExtras().getString("username");
        Cursor c = db.SelectByUsername_Utilizador(username);
        c.moveToFirst();
        if (c.getCount() == 1) {
            String password = c.getString(c.getColumnIndex("password"));
            et_username.setText(username);
            et_password.setText(password);

        } else if (c.getCount() == 0) {
            Toast.makeText(this, "Utilizador Inexistente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao carregar utilizador", Toast.LENGTH_SHORT).show();
        }
    }
}