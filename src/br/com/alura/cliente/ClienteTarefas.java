package br.com.alura.cliente;

import java.net.Socket;

public class ClienteTarefas {
	
	public static void main(String[] args) throws Exception {
		
		Socket socker = new Socket("localhost", 12345);
		
		System.out.println("Conexão estabelecida");
		
		socker.close();
	}

}
