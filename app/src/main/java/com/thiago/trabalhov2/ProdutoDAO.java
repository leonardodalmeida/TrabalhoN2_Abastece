package com.thiago.trabalhov2;

//Classe java para instanciar Querys/ Metodos de manipulação do objeto Produto (leia-se veículo) da tabela Veículo
//By Leonardo Dalmeida


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static void inserir(Context context, Produto produto){

        ContentValues valores = new ContentValues();
        valores.put("nome_veiculo" , produto.getAtutomovel());
        valores.put("kmAtual_veiculo" , produto.getQuilometro());
        valores.put("capacidade_tanque" , produto.getLitroTanque());
        valores.put("MediaKM" , produto.getKmLitro());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("Veiculos", null, valores);
    }

    public static void editar(Context context, Produto produto){

        ContentValues valores = new ContentValues();
        valores.put("nome_veiculo" , produto.getAtutomovel() );
        valores.put("kmAtual_veiculo" , produto.getQuilometro() );
        valores.put("capacidade_tanque" , produto.getLitroTanque() );
        valores.put("MediaKM" , produto.getKmLitro());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("Veiculos", valores, " id = " + produto.getId(), null);
    }

    public static void excluir(Context context, int idProduto){

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.delete("Veiculos", " id = " + idProduto, null);
    }

    public static List<Produto> getProdutos(Context context){
        List<Produto> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM Veiculos ORDER BY nome_veiculo" , null);
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                Produto p = new Produto();
                p.setId( cursor.getInt( 0 ) );
                p.setAtutomovel( cursor.getString( 1 ) );
                p.setQuilometro( cursor.getDouble( 2 ));
                p.setKmLitro( cursor.getDouble( 4 ));
                lista.add( p );
            }while ( cursor.moveToNext() );
        }
        return lista;
    }

    public static Produto getProdutoById(Context context, int idProduto){

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor =  db.rawQuery("SELECT * FROM Veiculos WHERE id = " + idProduto , null);
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            Produto p = new Produto();
            p.setId( cursor.getInt( 0 ) );
            p.setAtutomovel( cursor.getString( 1 ) );
            p.setQuilometro( cursor.getDouble( 2 ));
            p.setKmLitro( cursor.getDouble( 4 ));

            return p;
        }else{
            return null;
        }
    }

}