package com.thiago.trabalhov2;

//Classe java para criar a tela que gera um registro de veiculo e comunica com os formularios e intents
//By Thiago Lacerda


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormularioActivity2 extends AppCompatActivity {

    private EditText etAtutomovel, etKmLitro, etLitroTanque, etQuilometro;
    private Button btnSalvarFor;
    private String acao;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario2);

        if(getIntent().getExtras() != null){
            acao = getIntent().getExtras().getString("acao");
        }

        etAtutomovel = findViewById(R.id.etAtutomovel);
        etKmLitro = findViewById(R.id.etKmLitro);
        etLitroTanque = findViewById(R.id.etLitroTanque);
        etQuilometro = findViewById(R.id.etQuilometro);
        btnSalvarFor = findViewById(R.id.btnSalvarFor);

        btnSalvarFor = findViewById(R.id.btnSalvarFor);

        btnSalvarFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormularioActivity2.this, AbasteceActivity2.class);
                startActivity(intent);
            }
        });

        btnSalvarFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    private void salvar(){
        if(produto == null){
            produto = new Produto();
        }
        String nome = etAtutomovel.getText().toString();
        if ( nome.isEmpty() ){
            AlertDialog.Builder alerta = new AlertDialog.Builder( this);
            alerta.setTitle("Atenção!");
            alerta.setMessage("O nome do veículo deve ser preenchido!");
            alerta.setIcon( android.R.drawable.ic_dialog_alert );
            alerta.setPositiveButton("OK", null);
            alerta.show();
        }else {
            produto.setAtutomovel(nome);
            String sKM_Atual = etQuilometro.getText().toString();
            String sTanque = etLitroTanque.getText().toString();
            String sKmLitro = etKmLitro.getText().toString();
            if (sKM_Atual.isEmpty()) {
                produto.setQuilometro(0.0);
            } else {
                sKM_Atual = sKM_Atual.replace(",", ".");
                double KM_Atual = Double.valueOf(sKM_Atual);
                produto.setQuilometro(KM_Atual);
            }
            if (sTanque.isEmpty()) {
                produto.setLitroTanque(0.0);
            } else {
                sTanque = sTanque.replace(",", ".");
                double Tanque = Double.valueOf(sTanque);
                produto.setLitroTanque(Tanque);
            }

            if (sKmLitro.isEmpty()) {
                produto.setKmLitro(0.0);
            } else {
                sKmLitro = sKmLitro.replace(",", ".");
                double kmLitro = Double.valueOf(sKmLitro);
                produto.setKmLitro(kmLitro);
            }

            if (acao.equals("inserir")) {
                ProdutoDAO.inserir(this, produto);
                produto = null;
                etAtutomovel.setText("");
                etQuilometro.setText("");
                etLitroTanque.setText("");
                etKmLitro.setText("");
                finish();
            }
        }
    }
}