package com.example.asus.alunobd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListarAlunos extends AppCompatActivity {

    MyCustomAdapter myCustomAdapter;
    ListView listView;
    DroidDatabaseHelper db;
    ArrayList<Aluno> aluno= new ArrayList<Aluno>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_alunos);

        db = new DroidDatabaseHelper(this);

        aluno=db.getData();
        myCustomAdapter= new MyCustomAdapter(this,R.layout.aluno_details,aluno);

        listView = (ListView) findViewById(R.id.listAlunos);
        listView.setAdapter(myCustomAdapter);

        //Selecionar item da lista
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //int pos = listView.getSelectedItemPosition();
              Aluno aluno = (Aluno)listView.getItemAtPosition(position);
              Log.e("[MEUTESTE]", "Nome do aluno selecionado: " + aluno.getNome() + " " + aluno.getId());

              Intent i = new Intent(getApplicationContext(), Edicao.class);
              i.putExtra("idAluno", aluno.getId());
              startActivity(i);
          }
      });
    }

    public void cancelar(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
