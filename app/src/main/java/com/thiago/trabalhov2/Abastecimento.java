package com.thiago.trabalhov2;

//Classe java para instanciar objetos que pegam e setam as variáveis do cadastro de Abastecimento, bem como metodo para printar string da lista
//By Leonardo Dalmeida


import java.time.LocalDateTime;

public class Abastecimento {

    private int id;
    private int veiculo_id;
    private LocalDateTime Dt_Abastecimento;
    private double kmAbastecimento_veiculo;
    private double precoGas;
    private double precoEta;
    private double capacidade_tanque;
    private String Combs_indicado;
    private String Combs_selecionado;
    private double L_abastecidos;
    private String tipo;

    public Abastecimento() {

    }

    public Abastecimento(int veiculo_id, LocalDateTime dt_Abastecimento, double kmAbastecimento_veiculo, double precoGas, double precoEta, double capacidade_tanque, String combs_indicado, String combs_selecionado, double l_abastecidos, String tipo) {
        this.veiculo_id = veiculo_id;
        Dt_Abastecimento = dt_Abastecimento;
        this.kmAbastecimento_veiculo = kmAbastecimento_veiculo;
        this.precoGas = precoGas;
        this.precoEta = precoEta;
        this.capacidade_tanque = capacidade_tanque;
        Combs_indicado = combs_indicado;
        Combs_selecionado = combs_selecionado;
        L_abastecidos = l_abastecidos;
        this.tipo = tipo;
    }

    public Abastecimento(int id, int veiculo_id, LocalDateTime dt_Abastecimento, double kmAbastecimento_veiculo, double precoGas, double precoEta, double capacidade_tanque, String combs_indicado, String combs_selecionado, double l_abastecidos, String tipo) {
        this.id = id;
        this.veiculo_id = veiculo_id;
        Dt_Abastecimento = dt_Abastecimento;
        this.kmAbastecimento_veiculo = kmAbastecimento_veiculo;
        this.precoGas = precoGas;
        this.precoEta = precoEta;
        this.capacidade_tanque = capacidade_tanque;
        Combs_indicado = combs_indicado;
        Combs_selecionado = combs_selecionado;
        L_abastecidos = l_abastecidos;
        this.tipo = tipo;
    }

    public Abastecimento(int veiculo_id, LocalDateTime dt_Abastecimento, double kmAbastecimento_veiculo, double precoGas, double precoEta, double capacidade_tanque, String combs_indicado, String combs_selecionado, double l_abastecidos) {
        this.veiculo_id = veiculo_id;
        Dt_Abastecimento = dt_Abastecimento;
        this.kmAbastecimento_veiculo = kmAbastecimento_veiculo;
        this.precoGas = precoGas;
        this.precoEta = precoEta;
        this.capacidade_tanque = capacidade_tanque;
        Combs_indicado = combs_indicado;
        Combs_selecionado = combs_selecionado;
        L_abastecidos = l_abastecidos;
        this.tipo = tipo;
    }

    public Abastecimento(int id, int veiculo_id, LocalDateTime dt_Abastecimento, double kmAbastecimento_veiculo, double precoGas, double precoEta, double capacidade_tanque, String combs_indicado, String combs_selecionado, double l_abastecidos) {
        this.id = id;
        this.veiculo_id = veiculo_id;
        Dt_Abastecimento = dt_Abastecimento;
        this.kmAbastecimento_veiculo = kmAbastecimento_veiculo;
        this.precoGas = precoGas;
        this.precoEta = precoEta;
        this.capacidade_tanque = capacidade_tanque;
        Combs_indicado = combs_indicado;
        Combs_selecionado = combs_selecionado;
        L_abastecidos = l_abastecidos;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(int veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDt_Abastecimento() {
        return Dt_Abastecimento;
    }

    public void setDt_Abastecimento(LocalDateTime dt_Abastecimento) {
        Dt_Abastecimento = dt_Abastecimento;
    }

    public double getKmAbastecimento_veiculo() {
        return kmAbastecimento_veiculo;
    }

    public void setKmAbastecimento_veiculo(double kmAbastecimento_veiculo) {
        this.kmAbastecimento_veiculo = kmAbastecimento_veiculo;
    }

    public double getPrecoGas() {
        return precoGas;
    }

    public void setPrecoGas(double precoGas) {
        this.precoGas = precoGas;
    }

    public double getPrecoEta() {
        return precoEta;
    }

    public void setPrecoEta(double precoEta) {
        this.precoEta = precoEta;
    }

    public double getCapacidade_tanque() {
        return capacidade_tanque;
    }

    public void setCapacidade_tanque(double capacidade_tanque) {
        this.capacidade_tanque = capacidade_tanque;
    }

    public String getCombs_indicado() {
        return Combs_indicado;
    }

    public void setCombs_indicado(String combs_indicado) {
        Combs_indicado = combs_indicado;
    }

    public String getCombs_selecionado() {
        return Combs_selecionado;
    }

    public void setCombs_selecionado(String combs_selecionado) {
        Combs_selecionado = combs_selecionado;
    }

    public double getL_abastecidos() {
        return L_abastecidos;
    }

    public void setL_abastecidos(double l_abastecidos) {
        L_abastecidos = l_abastecidos;
    }

    @Override
    public String toString() {
        return "Km Atual " + kmAbastecimento_veiculo + "- Litros " + L_abastecidos + "- Combustivel " + Combs_selecionado;
    }
}
