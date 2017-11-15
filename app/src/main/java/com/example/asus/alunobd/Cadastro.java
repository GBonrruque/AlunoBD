package com.example.asus.alunobd;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Cadastro extends AppCompatActivity {

    EditText editTextNome;
    EditText editTextRa;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextNome = (EditText)findViewById(R.id.editTextNome);
        editTextRa = (EditText)findViewById(R.id.editTextRa);
    }
    public void cadastrar(View v){
        if (editTextNome.getText().toString().trim().length() == 0 || editTextRa.getText().toString().trim().length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Erro nos campos");
            builder.setMessage("Preencha todos os campos corretamente");
            alerta = builder.create();
            alerta.show();
            editTextNome.requestFocus();

        }else{
            DroidDatabaseHelper db = new DroidDatabaseHelper(this);
            Aluno a = new Aluno(null, editTextNome.getText().toString(),editTextRa.getText().toString());
            db.insertAluno(a);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Aluno Cadastrado");
            builder.setMessage("Veja a lista para visualizar o cadastro");
            alerta = builder.create();
            alerta.show();
        }

    }
    public void voltar(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
