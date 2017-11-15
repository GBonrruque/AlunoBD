package com.example.asus.alunobd;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class Edicao extends AppCompatActivity {

    DroidDatabaseHelper db;
    EditText editNome;
    EditText editRa;
    AlertDialog alerta;
    String aId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao);

        editNome = (EditText)findViewById(R.id.editNome);
        editRa = (EditText)findViewById(R.id.editRa);

        Bundle extras = getIntent().getExtras();
        aId = (String) extras.get("idAluno");
       // Log.e("[MEUTESTE]", "Nome do aluno selecionado: " + a_Id);

        db = new DroidDatabaseHelper(this);
        //Aluno a_sel = new Aluno(null, null, null);
        Aluno alunoSel = db.buscarAluno(aId);
        String alunoSelNome = alunoSel.getNome();
        String alunoSelRa = alunoSel.getRa();

        editNome.setText(alunoSelNome);
        editRa.setText(alunoSelRa);
    }

    public void atualizarCadastro(View v) {
        if (editNome.getText().toString().trim().length() == 0 || editRa.getText().toString().trim().length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Erro nos campos:");
            builder.setMessage("Preencha todos os campos corretamente.");
            alerta = builder.create();
            alerta.show();
            editNome.requestFocus();

        } else {
            DroidDatabaseHelper db = new DroidDatabaseHelper(this);

            String idAt = aId;
            String nomeAt = editNome.getText().toString();
            String raAt = editRa.getText().toString();

            Aluno alunoAt = new Aluno(idAt ,nomeAt, raAt);
            db.updateAluno(alunoAt);


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Aluno Atualizado:");
            builder.setMessage("Confira a atualização na lista de alunos.");
            builder.setNeutralButton("OK", null);
            alerta = builder.create();
            alerta.show();

        }
    }

    public void excluirAluno(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir");
        builder.setMessage("Tem certeza que deseja excluir este aluno?");
        builder
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String idAlunoEx = aId;
                        db.deleteAluno(idAlunoEx);
                    }
                })
                .setNegativeButton("NÃO", null);
        alerta = builder.create();
        alerta.show();
    }

    public void voltar(View v){
        Intent i = new Intent(this, ListarAlunos.class);
        startActivity(i);
    }
}
