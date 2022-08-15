package br.com.alura.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {

	public static void main(String[] args) throws Exception {
		
		System.out.println("#### Iniciando servidor ####");
		ServerSocket servidor = new ServerSocket(12345);
		
//		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		while(true) {
			Socket accept = servidor.accept();
			System.out.println("Aceitando novo cliente na porta " + accept.getPort());
			
			DistribuirTarefas distribuirTarefas = new DistribuirTarefas(accept);
			threadPool.execute(distribuirTarefas);
		}
		
	}
	
}
