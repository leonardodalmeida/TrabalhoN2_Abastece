package com.thiago.trabalhov2;

//Classe java para instanciar objetos que pegam e setam as variáveis do cadastro de veículo, bem como metodo para printar string da lista
//By Leonardo Dalmeida

public class Produto {

    private int id;
    private String Atutomovel;
    private Double KMLitro, litroTanque, Quilometro, vEta, vGas, KM_abstecimento, litrosA;

    public Produto() {
    }

    public Produto(String atutomovel, Double quilometro, Double kmLitro) {
        Atutomovel = atutomovel;
        Quilometro = quilometro;
        KMLitro = kmLitro;
    }

    public Produto(int id, String atutomovel, Double kmLitro, Double litroTanque, Double quilometro, Double vEta, Double vGas, Double KM_abstecimento, Double litrosA) {
        this.id = id;
        Atutomovel = atutomovel;
        KMLitro = kmLitro;
        this.litroTanque = litroTanque;
        Quilometro = quilometro;
        this.vEta = vEta;
        this.vGas = vGas;
        this.KM_abstecimento = KM_abstecimento;
        this.litrosA = litrosA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAtutomovel() {
        return Atutomovel;
    }

    public void setAtutomovel(String atutomovel) {
        Atutomovel = atutomovel;
    }

    public Double getKmLitro() {
        return KMLitro;
    }

    public void setKmLitro(Double kmLitro) {
        KMLitro = kmLitro;
    }

    public Double getLitroTanque() {
        return litroTanque;
    }

    public void setLitroTanque(Double litroTanque) {
        this.litroTanque = litroTanque;
    }

    public Double getQuilometro() {
        return Quilometro;
    }

    public void setQuilometro(Double quilometro) {
        Quilometro = quilometro;
    }

//    public Double getkmLitro() {
//        return kmLitro;
//    }
//
//    public void setkmLitro(Double kmLitro) {
//        kmLitro = kmLitro;
//    }

    public Double getvEta() {
        return vEta;
    }

    public void setvEta(Double vEta) {
        this.vEta = vEta;
    }

    public Double getvGas() {
        return vGas;
    }

    public void setvGas(Double vGas) {
        this.vGas = vGas;
    }

    public Double getKM_abstecimento() {
        return KM_abstecimento;
    }

    public void setKM_abstecimento(Double KM_abstecimento) {
        this.KM_abstecimento = KM_abstecimento;
    }

    public Double getLitrosA() {
        return litrosA;
    }

    public void setLitrosA(Double litrosA) {
        this.litrosA = litrosA;
    }

    public static double calcular(double vGas, double vEta){
        return vEta/vGas;
    }

    public static  double calculaMediaGasolina(double kmL, double vGas){
        return kmL/vGas;
    }

    public static  double calculaMediaEtanol(double kmL, double vEta){
        return kmL/vEta;
    }

    @Override
    public String toString() {
        return  Atutomovel + " - KM rodados: " + Quilometro + " - KM/L: " + KMLitro;
    }

}