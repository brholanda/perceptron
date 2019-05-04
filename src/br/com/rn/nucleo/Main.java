package br.com.rn.nucleo;

import java.io.IOException;

import treinador.Treinador;
import treinador.TreinadorIris;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Treinador treinador = new TreinadorIris();
		treinador.treinar();
	}

}
