package com.thiago.trabalhov2;

//Classe java para instanciar Querys/ Metodos de manipulação do objeto Abastecimento da tabela Abastecimentos, e também gerar o cálculo da média por KM/L na tabela veículo
//By Leonardo Dalmeida


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AbastecimentoDAO {

    public static void inserir(Context context, Abastecimento abastecimento) {

        ContentValues valores = new ContentValues();
        valores.put("veiculo_id", abastecimento.getVeiculo_id());
        valores.put("Dt_Abastecimento", String.valueOf(abastecimento.getDt_Abastecimento()));
        valores.put("kmAbastecimento_veiculo", abastecimento.getKmAbastecimento_veiculo());
        valores.put("precoGas", abastecimento.getPrecoGas());
        valores.put("precoEta", abastecimento.getPrecoEta());
        valores.put("capacidade_tanque", abastecimento.getCapacidade_tanque());
        valores.put("Combs_indicado", abastecimento.getCombs_indicado());
        valores.put("Combs_selecionado", abastecimento.getCombs_selecionado());
        valores.put("L_abastecidos", abastecimento.getL_abastecidos());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        if(db.insert("Abastecimentos", null, valores) == -1){
            System.out.println("Não foi possivel gravar os dados!");
        }


    }

    public static void inserirMediaKM(Context context, double MediaKM, int idVeiculo){

        ContentValues valores = new ContentValues();
        valores.put("MediaKM" , MediaKM );


        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("Veiculos", valores, " id = " + idVeiculo, null);
    }

    public static void excluir(Context context, int idAbastecimento) {

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.delete("Abastecimentos", " id = " + idAbastecimento, null);
    }

    public static List<Abastecimento> getAbastecimentos(Context context) {
        List<Abastecimento> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Abastecimentos", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Abastecimento a = new Abastecimento();
                a.setId(cursor.getInt(0));
                a.setKmAbastecimento_veiculo(cursor.getDouble(3));
                a.setL_abastecidos(cursor.getDouble(9));
                a.setCombs_selecionado(cursor.getString(8));
                lista.add(a);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public static List<Abastecimento> getAbastecimentos(Context context, int idProduto) {
        List<Abastecimento> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Abastecimentos WHERE veiculo_id =" + idProduto, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Abastecimento a = new Abastecimento();
                a.setId(cursor.getInt(0));
                a.setKmAbastecimento_veiculo(cursor.getDouble(3));
                a.setL_abastecidos(cursor.getDouble(9));
                a.setCombs_selecionado(cursor.getString(8));
                lista.add(a);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public static int getSumAbastecimentoById(Context context, int idVeiculo){

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor =  db.rawQuery("SELECT SUM(L_abastecidos) FROM Abastecimentos WHERE veiculo_id = " + idVeiculo , null);

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            //Abastecimento p = new Abastecimento();
            //p.setSumAbastecimento( cursor.getInt( 0 ) );
            return ( cursor.getInt( 0 ) );

        }else{
            return 0;
        }
    }

    //public static int getDifKM_AbastecimentoById(Context context, int idVeiculo){
    public static int getDifKM_AbastecimentoById(Context context, int idVeiculo){

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        int minKM, maxKM;
        minKM = 0;
        maxKM = 0;

        Cursor cursorMin =  db.rawQuery("SELECT kmAbastecimento_veiculo FROM Abastecimentos WHERE veiculo_id = " + idVeiculo + " AND id = (SELECT MIN(id) FROM abastecimentos WHERE veiculo_id = " + idVeiculo + ")", null);
        Cursor cursorMax =  db.rawQuery("SELECT kmAbastecimento_veiculo FROM Abastecimentos WHERE veiculo_id = " + idVeiculo + " AND id = (SELECT MAX(id) FROM abastecimentos WHERE veiculo_id = " + idVeiculo + ")", null);


        if( cursorMin.getCount() > 0 && cursorMax.getCount() > 0){

            cursorMin.moveToFirst();
            minKM = cursorMin.getInt( 0 );

            cursorMax.moveToFirst();
            maxKM = cursorMax.getInt( 0 );

//            Abastecimento p = new Abastecimento();
//            p.setDifKM_Abastecimento( maxKM - minKM);

            return (maxKM - minKM);

        }else{
            return 0;
        }


    }
    public static int getCountAbastecimentoById(Context context, int idVeiculo){

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor =  db.rawQuery("SELECT  * FROM Abastecimentos WHERE veiculo_id = " + idVeiculo , null);
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            //Abastecimento p = new Abastecimento();
            //p.setId( cursor.getInt( 0 ) );
            //p.setCount( cursor.getCount() );
            return ( cursor.getCount() );

        }else{
            return 0;
        }
    }

}