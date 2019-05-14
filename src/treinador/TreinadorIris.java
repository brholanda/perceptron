package treinador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.rn.neurorio.Perceptron;
import br.com.rn.nucleo.Main;
import br.com.rn.validador.Escada;

public class TreinadorIris extends Treinador {

	private List<double[]> conjuntoEntradasTreino, conjuntoEntradasTeste;
	private List<Integer> saidasEsperadasTreino, saidasEsperadasTeste;
	private int quantidadeTreino;

	public void treinar() throws NumberFormatException, IOException {

		String[] valores;
		InputStream res = Main.class.getResourceAsStream("/files/iris.data");

		BufferedReader in = new BufferedReader(new InputStreamReader(res));
		List<String> listaTreino = in.lines().collect(Collectors.toList());
		int tamanhoAmostragem = (int) listaTreino.size();
		quantidadeTreino = (tamanhoAmostragem * 80 / 100) / 2;
		int quantidadeSetosa = 0, quantidadeVersicolor = 0;
		in.close();
		if (tamanhoAmostragem != 0) {
			conjuntoEntradasTreino = new ArrayList<>();
			saidasEsperadasTreino = new ArrayList<>();
			conjuntoEntradasTeste = new ArrayList<>();
			saidasEsperadasTeste = new ArrayList<>();
			for (int i = 0; i < tamanhoAmostragem; i++) {
				valores = listaTreino.get(i).split(",");
				if (valores[4].equals("Iris-setosa")) {
					quantidadeSetosa = updateListas(quantidadeSetosa, valores, 1);
				} else if (valores[4].equals("Iris-versicolor")) {
					quantidadeVersicolor = updateListas(quantidadeVersicolor, valores, 0);
				}
			}
		} else {
			return;
		}
		int erro, saida, epocas = 0;

		Boolean saidasOk;

		Perceptron perceptron = new Perceptron(conjuntoEntradasTreino.get(0).length, new Escada());
		do {
			erro = 0;
			saidasOk = true;
			for (int i = 0; i < conjuntoEntradasTreino.size(); i++) {
				saida = perceptron.saidaAxionio(conjuntoEntradasTreino.get(i));
				if (saida != saidasEsperadasTreino.get(i)) {
					saidasOk = false;
					erro += saidasEsperadasTreino.get(i) - saida;
					System.out.println("erro: " + erro);
					perceptron.verificaErro(erro, fator, conjuntoEntradasTreino.get(i));
				}
				System.out.println("Saida: " + saida + "\nSaída Esperada: " + saidasEsperadasTreino.get(i) + "\nErro: "
						+ erro + "\n=====================================");
			}
			epocas++;
			System.out.println("Época: " + epocas + "\n#####################################");
		} while (!saidasOk);

		System.out.println("\nÉpocas: " + epocas);

		int erros = 0, acertos = 0;
		for (int i = 0; i < conjuntoEntradasTeste.size(); i++) {
			saida = perceptron.saidaAxionio(conjuntoEntradasTeste.get(i));
			if (saida != saidasEsperadasTeste.get(i)) {
				erros++;
			} else {
				acertos++;
			}
		}
		System.out.println("Acertos: " + acertos + "\nErros: " + erros);
	}

	private int updateListas(int quantidade, String[] valores, int esperado) {
		if (quantidade < quantidadeTreino) {
			conjuntoEntradasTreino.add(new double[] { Double.valueOf(valores[0]), Double.valueOf(valores[2]) });
			saidasEsperadasTreino.add(esperado);
			quantidade++;
		} else {
			conjuntoEntradasTeste.add(new double[] { Double.valueOf(valores[0]), Double.valueOf(valores[2]) });
			saidasEsperadasTeste.add(esperado);
		}
		return quantidade;
	}

}
