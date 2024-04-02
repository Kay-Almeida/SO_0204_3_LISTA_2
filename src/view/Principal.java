package view;

import java.util.concurrent.Semaphore;
import controller.Conta; 
import controller.ThreadContas;

public class Principal {
    public static void main(String[] args) {
    	
        Conta[] contas = new Conta[20];
        Semaphore semaforoSaque = new Semaphore(1);
        Semaphore semaforoDeposito = new Semaphore(1);

        for (int i = 0; i < contas.length; i++) {
            contas[i] = new Conta(i + 1, 1000, semaforoSaque, semaforoDeposito);
        }

        ThreadContas[] transacoes = new ThreadContas[20];
        for (int i = 0; i < transacoes.length; i++) {
            transacoes[i] = new ThreadContas(contas[i % contas.length], semaforoSaque, semaforoDeposito);
            transacoes[i].start();
        }

        try {
            for (ThreadContas transacao : transacoes) {
                transacao.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
