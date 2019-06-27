package br.com.rn.validador;

public class Degrau implements Validador {

	@Override
	public int validar(Double somatorio, Double theta) {
		return somatorio > theta ? 1 : 0;
	}

}
