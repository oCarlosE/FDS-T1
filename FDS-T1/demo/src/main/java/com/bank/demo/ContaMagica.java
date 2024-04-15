package com.bank.demo;

public class ContaMagica {
    private double saldo;
    private TipoConta tipo;

    public ContaMagica(){
        this.saldo = 0.0;
        this.tipo = TipoConta.SILVER; 
    }

    public boolean Upgrade(){
        if(saldo > 50.000 && tipo != TipoConta.GOLD){
            tipo = TipoConta.GOLD;
            System.out.println("Upgrading to Gold");
            return true;
        }
        else if (saldo > 200.000  && tipo != TipoConta.PLATINUM) {
            tipo = TipoConta.PLATINUM;
            System.out.println("Upgrading to Platinum");
            return true;
        }
        return false;
    }

    public boolean Downgrade(){
        if (saldo < 25.000 && tipo == TipoConta.GOLD) {
            tipo = TipoConta.SILVER;
            System.out.println("Downgrading to Silver");
            return true;
        }
        else if (saldo < 100.000 && tipo == TipoConta.PLATINUM) {
            tipo = TipoConta.GOLD;
            System.out.println("Downgrading to Gold");
            return true;
        }
        return false;
    }

    public void Deposito(double valor){
        if (valor < 0) {
            throw new IllegalArgumentException("Valor negativo é inválido para depósito");
        }
        else{
            saldo += valor;
            Upgrade();
        }
    }

    public double Saque(double valor){
        if(valor > saldo || valor < 0){
            System.out.println();
            throw new IllegalArgumentException("Valor Inválido");
        }
        else{
            saldo -= valor;
            Downgrade();
            return valor;
        }
    }
}
