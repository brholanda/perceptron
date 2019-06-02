package br.com.rn.treinador;

import br.com.rn.neurorio.Perceptron;
import br.com.rn.validador.Escada;

public class TreinadorPortaLogica extends Treinador {

	public void treinar() {
		double[][] conjuntoEntradas = { { 1, 1 }, { 1, 0 }, { 0, 1 }, { 0, 0 } };
		int[] saidasEsperadas = { 0, 0, 0, 1 };
		int erro, saida, passos = 0;

		Boolean saidasOk;

		Perceptron perceptron = new Perceptron(conjuntoEntradas[0].length, new Escada());
		do {
			saidasOk = true;
			for (int i = 0; i < conjuntoEntradas.length; i++) {
				erro = 0;
				saida = perceptron.saidaAxionio(conjuntoEntradas[i]);
				if (saida != saidasEsperadas[i]) {
					saidasOk = false;
					erro = saidasEsperadas[i] - saida;
					System.out.println("erro: " + erro);
					perceptron.verificaErro(erro, fator, conjuntoEntradas[i]);
				}
				System.out.println("Saida: " + saida + "\nSaída Esperada: " + saidasEsperadas[i] + "\nErro: " + erro
						+ "\n=====================================");
			}
			passos++;
			System.out.println("Passo: " + passos + "\n#####################################");
		} while (!saidasOk);

		System.out.println("\nPassos: " + passos);
	}

}
