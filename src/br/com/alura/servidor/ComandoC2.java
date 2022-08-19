package br.com.alura.servidor;

import java.io.PrintStream;

public class ComandoC2 implements Runnable {

	private PrintStream saidaCliente;
	
	public ComandoC2(PrintStream saidaCliente) {
		this.saidaCliente = saidaCliente;
	}

	@Override
	public void run() {
		saidaCliente.println("Executando comando c2");
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		saidaCliente.println("Comando c2 executado com sucesso");
	}

}
