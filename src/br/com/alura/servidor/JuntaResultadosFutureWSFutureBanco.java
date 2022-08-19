package br.com.alura.servidor;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JuntaResultadosFutureWSFutureBanco implements Callable<Void> {

	private Future<String> futureWS;
	private Future<String> futureBanco;
	private PrintStream saidaCliente;

	public JuntaResultadosFutureWSFutureBanco(Future<String> futureWS, Future<String> futureBanco, PrintStream saidaCliente) {
		this.futureWS = futureWS;
		this.futureBanco = futureBanco;
		this.saidaCliente = saidaCliente;
	}

	@Override
	public Void call() {
		
		System.out.println("Aguardando resultados do Future WS e Banco");

		try {
			String resultadoWS = futureWS.get(15, TimeUnit.SECONDS);
			String resultadoBanco = futureBanco.get(15, TimeUnit.SECONDS);
			this.saidaCliente.println("Resultado c2: " + resultadoWS + " - " + resultadoBanco);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			this.saidaCliente.println("Timeout no comando c2");
			this.futureWS.cancel(true);
			this.futureBanco.cancel(true);
		}
		
		this.saidaCliente.println("Finalizou JuntaResultadosFutureWSFutureBanco");
		
		return null;
	}

}
