package br.com.rn.nucleo;

import java.io.IOException;

import br.com.rn.treinador.Treinador;
import br.com.rn.treinador.TreinadorIris;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Treinador treinador = new TreinadorIris();
		treinador.treinar();
	}

}
