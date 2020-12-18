package com.thiago.trabalhov2;

//Classe java para criar a tela que gera uma lista de registros de abastecimentos
//By Thiago Lacerda

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ActivitiAbastece extends AppCompatActivity {

    private ListView lstAbastece;
    private int idProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiti_abastece);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lstAbastece = findViewById(R.id.lsAbastece);

        if(getIntent().getExtras() != null){
            idProduto = getIntent().getExtras().getInt("idProduto");
            Log.i("idveiculo","id " +  idProduto);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext() , AbasteceActivity2.class);
                intent.putExtra("idProduto" ,  idProduto );
                startActivity(intent);
            }
        });

        carregarAbastecimento();

        lstAbastece.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // click no item da lista de abastecimento
            }
        });
        lstAbastece.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Abastecimento abastecSelecionado = (Abastecimento) adapterView.getItemAtPosition( i );
                excluir(abastecSelecionado);
                return true;
            }
        });
    }

    private void excluir(final Abastecimento abastece){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Abastecimento");
        alerta.setMessage("Confirma a exclusão do Abastecimento " + abastece.getId() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AbastecimentoDAO.excluir(ActivitiAbastece.this, abastece.getId());
                carregarAbastecimento();
            }
        });
        alerta.show();
    }

    private void carregarAbastecimento(){
        List<Abastecimento> lista = AbastecimentoDAO.getAbastecimentos(this,idProduto);
        ArrayAdapter adapater = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lstAbastece.setAdapter(adapater);
    }
}