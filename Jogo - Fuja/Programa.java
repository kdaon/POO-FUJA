/**
 * A classe Programa é responsável por iniciar o jogo.
 */


public class Programa {

	/**
     * Método principal que inicia o jogo chamando o método 'jogar' da classe Jogo.
     *
     * @param args Argumentos da linha de comando (não são utilizados neste programa).
     */

	public static void main(String[] args) {
			Jogo jogo = new Jogo();
			jogo.jogar();
	}

}
