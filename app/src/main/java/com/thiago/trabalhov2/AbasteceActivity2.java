package com.thiago.trabalhov2;

//Classe java para criar a tela que gera um registro de abastecimento e comunica com os formularios e intents
//By Thiago Lacerda

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class AbasteceActivity2 extends AppCompatActivity {

    private EditText etValorGasolina, etValorEtanol, etQuilometragem, etLitrosAbastecidos;
    private Button btnSalvar;
    private Spinner spTipo;
    private String acao;
    private Abastecimento abastecimento;
    private int idProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abastece2);

        etValorGasolina = findViewById(R.id.etValorGasolina);
        etValorEtanol = findViewById(R.id.etValorEtanol);
        etQuilometragem = findViewById(R.id.etQuilometragem);
        etLitrosAbastecidos = findViewById(R.id.etLitrosAbastecidos);
        btnSalvar = findViewById(R.id.btnSalvar);
        spTipo = findViewById(R.id.spTipo);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        if(getIntent().getExtras() != null){
            idProduto = getIntent().getExtras().getInt("idProduto");
        }

        etValorEtanol.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                String ValorGasolina = etValorGasolina.getText().toString();
                String ValorEtanol = etValorEtanol.getText().toString();

                if (!ValorGasolina.isEmpty() && !ValorEtanol.isEmpty()) {
                    ValorGasolina = ValorGasolina.replace(",", ".");
                    double Vg = Double.valueOf(ValorGasolina);

                    ValorEtanol = ValorEtanol.replace(",", ".");
                    double Ve = Double.valueOf(ValorEtanol);

                    double valor = Produto.calcular(Vg, Ve);

                    if (valor < 0.7) {
                        Toast.makeText(AbasteceActivity2.this,"Abasteça com Etanol!",Toast.LENGTH_LONG).show();
                        spTipo.setSelection(2);
                    } else {
                        Toast.makeText(AbasteceActivity2.this,"Abasteça com Gasolina!",Toast.LENGTH_LONG).show();
                        spTipo.setSelection(1);
                    }
                }
            }
        });

        etValorGasolina.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {

                    String ValorGasolina = etValorGasolina.getText().toString();
                    String ValorEtanol = etValorEtanol.getText().toString();

                    if (!ValorGasolina.isEmpty() && !ValorEtanol.isEmpty()) {
                        ValorGasolina = ValorGasolina.replace(",", ".");
                        double Vg = Double.valueOf(ValorGasolina);

                        ValorEtanol = ValorEtanol.replace(",", ".");
                        double Ve = Double.valueOf(ValorEtanol);

                        double valor = Produto.calcular(Vg, Ve);

                        if (valor > 0.7) {
                            Toast.makeText(AbasteceActivity2.this,"Abasteça com Gasolina!",Toast.LENGTH_LONG).show();
                            spTipo.setSelection(1);
                        } else {
                            Toast.makeText(AbasteceActivity2.this,"Abasteça com Etanol!",Toast.LENGTH_LONG).show();
                            spTipo.setSelection(2);
                        }
                    }
                }
            }
        });
    }

    private void salvar() {
        acao = "inserir";
        if (abastecimento == null) {
            abastecimento = new Abastecimento();
        }

        abastecimento.setVeiculo_id(idProduto);
        Double KM = Double.valueOf(etQuilometragem.getText().toString());
        Double Litros = Double.valueOf(etLitrosAbastecidos.getText().toString());
        if (KM.isNaN() && Litros.isNaN()) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Atenção!");
            alerta.setMessage("O KM do veículo deve ser preenchido!");
            alerta.setIcon(android.R.drawable.ic_dialog_alert);
            alerta.setPositiveButton("OK", null);
            alerta.show();
        } else {
            abastecimento.setKmAbastecimento_veiculo(KM);
            String sLitros_abst = etLitrosAbastecidos.getText().toString();
            String sKM_Atual = etQuilometragem.getText().toString();
            if (sKM_Atual.isEmpty()) {
                abastecimento.setKmAbastecimento_veiculo(0.0);
            } else {
                sKM_Atual = sKM_Atual.replace(",", ".");
                double KM_Atual = Double.valueOf(sKM_Atual);
                abastecimento.setKmAbastecimento_veiculo(KM_Atual);
            }
            if (sLitros_abst.isEmpty()) {
                abastecimento.setCapacidade_tanque(0.0);
            } else {
                sLitros_abst = sLitros_abst.replace(",", ".");
                double Tanque = Double.valueOf(sLitros_abst);
                abastecimento.setL_abastecidos(Tanque);
            }
            abastecimento.setPrecoEta(Double.parseDouble(etValorEtanol.getText().toString()));
            abastecimento.setPrecoGas(Double.parseDouble(etValorGasolina.getText().toString()));
            abastecimento.setDt_Abastecimento(LocalDateTime.now());
            abastecimento.setTipo(spTipo.getSelectedItem().toString());
            abastecimento.setCombs_indicado(" ");

            if( spTipo.getSelectedItemPosition() > 0 ) {
                abastecimento.setCombs_selecionado(spTipo.getSelectedItem().toString());
            }

            if (acao.equals("inserir")) {
                AbastecimentoDAO.inserir(this, abastecimento);
                int CountAbastecimentos, difKM, SumAbast;
                double mediaKM;

                CountAbastecimentos = AbastecimentoDAO.getCountAbastecimentoById(this, abastecimento.getVeiculo_id());
                if (CountAbastecimentos > 1){
                    difKM = AbastecimentoDAO.getDifKM_AbastecimentoById(this, abastecimento.getVeiculo_id());
                    SumAbast = AbastecimentoDAO.getSumAbastecimentoById(this, abastecimento.getVeiculo_id());
                    if (SumAbast > 1){
                        mediaKM = difKM/SumAbast;
                        AbastecimentoDAO.inserirMediaKM(this, mediaKM, abastecimento.getVeiculo_id());

                    }
                }


                abastecimento = null;
                etLitrosAbastecidos.setText("");
                etQuilometragem.setText("");
                etValorEtanol.setText("");
                etValorGasolina.setText("");
                Intent intent = new Intent(AbasteceActivity2.this, MainActivity.class);
                //intent.putExtra("acao" , "editar");
                //intent.putExtra("idProduto" ,  prodSelecionado.getId() );
                startActivity(intent);







            }
        }
    }
}