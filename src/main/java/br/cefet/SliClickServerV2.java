package br.cefet;

import br.cefet.serial.SerialRxTx;

/**
 * @author User
 */
public class SliClickServerV2 {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SerialRxTx serial = new SerialRxTx();

		if (serial.iniciaSerial()) {
			while (true) {

			}
		} else {

		}

	}
}
