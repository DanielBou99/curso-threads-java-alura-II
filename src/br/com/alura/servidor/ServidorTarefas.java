package br.com.alura.servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTarefas {

	public static void main(String[] args) throws Exception {
		
		System.out.println("#### Iniciando servidor ####");
		ServerSocket servidor = new ServerSocket(12345);
		
		while(true) {
			Socket accept = servidor.accept();
			System.out.println("Aceitando novo cliente na porta " + accept.getPort());
		}
		
	}
	
}
