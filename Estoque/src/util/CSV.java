package util;

import java.io.BufferedWriter;
import java.io.IOException;

public class CSV {
	
	/**
	 * Grava o conte�do da vari�vel valor no stream definido por dados.
	 * @param dados BufferedWriter de sa�da do dado.
	 * @param valor Valor a ser gravado.
	 * @throws IOException Lan�ada se dados n�o estiver acess�vel.
	 */
	public static void write(BufferedWriter dados, int valor) throws IOException {
		String s = String.valueOf(valor);
		write(dados,s,false,false);
	}
	public static void write(BufferedWriter dados, double valor) throws IOException {
		String s = String.valueOf(valor);
		write(dados,s,false,false);
	}
	public static void write(BufferedWriter dados, String valor) throws IOException {
		write(dados,valor,true,false);
	}

	public static void writeln(BufferedWriter dados, int valor) throws IOException {
		String s = String.valueOf(valor);
		write(dados,s,false,true);
	}
	public static void writeln(BufferedWriter dados, double valor) throws IOException {
		String s = String.valueOf(valor);
		write(dados,s,false,true);
	}
	public static void writeln(BufferedWriter dados, String valor) throws IOException {
		write(dados,valor,true,true);
	}
	
	private static void write(BufferedWriter dados, String valor, boolean aspas, boolean linha) throws IOException {
		if (aspas) dados.write("\"");
		dados.write(valor);
		if (aspas) dados.write("\"");
		if (linha) dados.write("\n");
		else dados.write(",");
	}

}