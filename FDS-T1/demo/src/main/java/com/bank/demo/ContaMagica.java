package com.bank.demo;

public class ContaMagica {
    public static final int SILVER = 0;
    public static final int GOLD = 1;
    public static final int PLATINUM = 2;
    private double saldo;
    private int status;

    public ContaMagica(){
        this.saldo = 0.0;
        this.status = SILVER; 
    }

    public boolean Upgrade() {
        if (getSaldo() >= 50000 && getStatus() != GOLD) {
            status = GOLD;
            System.out.println("Upgrading to Gold");
            return true;
        } else if (getSaldo() >= 200000 && getStatus() != PLATINUM) { 
            status = PLATINUM;
            System.out.println("Upgrading to Platinum");
            return true;
        }
        return false;
    }

    public boolean Downgrade() {
        if (getSaldo() < 25000 && getStatus() == GOLD) {
            status = SILVER;
            System.out.println("Downgrading to Silver");
            return true;
        } else if (getSaldo() < 100000 && getStatus() == PLATINUM) {
            status = GOLD;
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
            switch (status) {
                case ContaMagica.SILVER:
                    saldo += valor;
                break;
                case ContaMagica.GOLD:
                    saldo += valor + valor*0.01;
                break;
                case ContaMagica.PLATINUM:
                    saldo += valor + valor*0.025;
                break;
            }
            Upgrade();
        }
    }

    public double Saque(double valor){
        if(valor > getSaldo() || valor < 0){
            System.out.println();
            throw new IllegalArgumentException("Valor Inválido");
        }
        else{
            saldo -= valor;
            Downgrade();
            return valor;
        }
    }

    public int getStatus(){
        return status;
    }

    public double getSaldo(){
        return saldo;
    }
}
