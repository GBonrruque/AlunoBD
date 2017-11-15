package com.example.asus.alunobd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

/**
 * Created by Asus on 01/11/2017.
 */

public class MyCustomAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Aluno> aluno;

    public MyCustomAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context,textViewResourceId, objects);

        this.context= context;
        aluno=objects;

    }

    private class ViewHolder
    {
        TextView alunoNome;
        TextView alunoRa;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder= null;
        if (convertView == null)
        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.aluno_details, null);

            holder = new ViewHolder();
            holder.alunoNome = (TextView) convertView.findViewById(R.id.alunoNome);
            holder.alunoRa = (TextView) convertView.findViewById(R.id.alunoRa);
            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Aluno individualAluno= aluno.get(position);
        holder.alunoNome.setText("Nome do Aluno: " +  individualAluno.getNome() + "");
        holder.alunoRa.setText("RA do Aluno: "+ individualAluno.getRa());
        return convertView;
    }
}
