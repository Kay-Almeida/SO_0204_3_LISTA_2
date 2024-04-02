package controller;

import java.util.concurrent.Semaphore;

public class Conta {
	
    private int codigo;
    private double saldo;
    private Semaphore semaforoSaque;
    private Semaphore semaforoDeposito;

    public Conta(int codigo, double saldo, Semaphore semaforoSaque, Semaphore semaforoDeposito) {
        this.codigo = codigo;
        this.saldo = saldo;
        this.semaforoSaque = semaforoSaque;
        this.semaforoDeposito = semaforoDeposito;
    }

    public void sacar(double valor) {
        try {
            semaforoSaque.acquire();
            if (saldo >= valor) {
                saldo -= valor;
                System.out.println("Saque de R$" + valor + " realizado com sucesso na conta " + codigo + ". Novo saldo: R$" + saldo);
            } else {
                System.out.println("Saldo insuficiente para sacar R$" + valor + " na conta " + codigo + ". Saldo atual: R$" + saldo);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforoSaque.release();
        }
    }

    public void depositar(double valor) {
        try {
            semaforoDeposito.acquire();
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso na conta " + codigo + ". Novo saldo: R$" + saldo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforoDeposito.release();
        }
    }
}


