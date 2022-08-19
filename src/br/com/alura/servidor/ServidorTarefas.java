package br.com.alura.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {
	
	private ExecutorService threadPool;
	private ServerSocket servidor;
//	private volatile boolean estaRodando;
	private AtomicBoolean estaRodando;

	public ServidorTarefas() throws Exception {
		System.out.println("#### Iniciando servidor ####");
		this.servidor = new ServerSocket(12345);
		this.threadPool = Executors.newFixedThreadPool(4);
		this.estaRodando = new AtomicBoolean(true);
	}

	public void rodar() throws Exception {
		while(estaRodando.get()) {
			try {
				Socket accept = servidor.accept();
				System.out.println("Aceitando novo cliente na porta " + accept.getPort());
				DistribuirTarefas distribuirTarefas = new DistribuirTarefas(threadPool , accept, this);
				threadPool.execute(distribuirTarefas);
			} catch (SocketException e) {
				System.out.println("SocketException, está rodando? " + this.estaRodando);
			}
		}
	}
	
	public void parar() throws Exception {
		estaRodando.set(false);
		servidor.close();
		threadPool.shutdown();
	}
	
	public static void main(String[] args) throws Exception {
		ServidorTarefas servidor = new ServidorTarefas();
		servidor.rodar();
		servidor.parar();
	}

}
