package br.com.rn.validador;

public class Escada implements Validador {

	@Override
	public int validar(Double somatorio, Double theta) {
		System.out.println("Somatorio: " + somatorio);
		if (somatorio > theta) {
			return 1;
		}
		return 0;
	}

}
