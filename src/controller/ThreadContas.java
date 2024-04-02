package controller;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadContas extends Thread {
    private Conta conta;
    private Semaphore semaforoSaque;
    private Semaphore semaforoDeposito;

    public ThreadContas(Conta conta, Semaphore semaforoSaque, Semaphore semaforoDeposito) {
        this.conta = conta;
        this.semaforoSaque = semaforoSaque;
        this.semaforoDeposito = semaforoDeposito;
    }

    public void run() {
        try {
            int tipoTransacao = ThreadLocalRandom.current().nextInt(2);
            if (tipoTransacao == 0) {
                conta.sacar(ThreadLocalRandom.current().nextDouble(100));
            } else {
                conta.depositar(ThreadLocalRandom.current().nextDouble(100));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

