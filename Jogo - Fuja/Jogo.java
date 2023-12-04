
/**
 *  Essa classe cria e inicializa todas as demais classes do jogo, e foi feita com base no codigo base do jogo World of Zuul, de  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 *  
 * @author  Alexandra Melo, Gabriela Memento
 * 
 */
import java.util.Scanner;
import java.io.*;

public class Jogo {
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private Personagem personagem;

    private int nivelDificuldade;
    private boolean ambienteSeguro;
    private int qtdMovimentos;

    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {
        criarAmbientes();
        ambienteSeguro = true;
        analisador = new Analisador();
        personagem = new Personagem();

    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes() {
        Ambiente quarto, corredor, corredor2, banheiro, copa, cozinha, sala, garagem, hall, saida;

        // cria os itens
        Item taco = new Item("Taco de Beisebol", "Pode ser utilizado para atacar inimigos.");
        Item faca = new Item("Faca", "Pode ser utilizado para atacar inimigos.");
        Item arma = new Item("Arma", "Pode ser utilizado para atacar inimigos.");
        Item chave = new Item("Chave da cozinha", "Pode ser usada para destrancar a cozinha.");

        // cria os inimigos
        Inimigo zumbiCopa = new Inimigo("Zumbi da Copa",
                "Parece que não tem outra alternativa a não ser utilizar um taco", taco);
        Inimigo zumbiSala = new Inimigo("Zumbi da Sala", "Esse zumbi parece frágil, um objeto afiado pode ser útil",
                faca);
        Inimigo zumbiHall = new Inimigo("Zumbi do Hall",
                "Zumbi muito forte, parece que só pode ser derrotado por uma arma de fogo", arma);

        // cria os ambientes
        quarto = new Ambiente("no quarto.");
        corredor = new Ambiente("no corredor principal.");
        corredor2 = new Ambiente("no segundo corredor.");
        banheiro = new Ambiente("no banheiro.");
        copa = new Ambiente("na copa.");
        cozinha = new Ambiente("na cozinha.");
        sala = new Ambiente("na sala.");
        garagem = new Ambiente("na garagem.");
        hall = new Ambiente("no hall e enxerga a saída.");
        saida = new Ambiente("na saída.");

        // adiciona os inimigos nos ambientes
        copa.adicionarInimigo(zumbiCopa);
        sala.adicionarInimigo(zumbiSala);
        hall.adicionarInimigo(zumbiHall);

        // coloca os itens nos ambientes
        quarto.adicionarItem(taco);
        cozinha.adicionarItem(faca);
        garagem.adicionarItem(arma);
        banheiro.adicionarItem(chave);

        // trancar ambientes
        cozinha.trancarAmbiente("Chave da cozinha");

        // inicializa as saidas dos ambientes
        quarto.ajustarSaidas("norte", corredor);
        quarto.ajustarSaidas("leste", corredor2);

        corredor.ajustarSaidas("leste", copa);
        corredor.ajustarSaidas("sul", quarto);
        corredor.ajustarSaidas("oeste", banheiro);

        banheiro.ajustarSaidas("leste", corredor);

        copa.ajustarSaidas("leste", cozinha);
        copa.ajustarSaidas("oeste", corredor);

        cozinha.ajustarSaidas("leste", sala);
        cozinha.ajustarSaidas("sul", corredor2);
        cozinha.ajustarSaidas("oeste", copa);

        corredor2.ajustarSaidas("norte", cozinha);
        corredor2.ajustarSaidas("oeste", quarto);

        sala.ajustarSaidas("norte", garagem);
        sala.ajustarSaidas("sul", hall);
        sala.ajustarSaidas("oeste", cozinha);

        garagem.ajustarSaidas("sul", sala);

        hall.ajustarSaidas("norte", sala);
        hall.ajustarSaidas("leste", saida);

        ambienteAtual = quarto; // o jogo comeca no quarto
    }

    /*
     * 
     * Realiza a escrita da quantidade de movimentos atual no arquivo texto para que
     * a interface possa mostrar na tela para o usuario quantos movimentos restantes
     * ele possui
     * 
     * @param Embora o metodo nao possua parametro, é preciso passar a raiz do
     * caminho em que se deseja criar o arquivo movimentos.
     * 
     */

    public void insereQtdMovimentosArq() {
        String caminho = "movimentos.txt";
        BufferedWriter bw = null;
        try {
            FileWriter arq = new FileWriter(caminho, false);

            bw = new BufferedWriter(arq);

            bw.write(Integer.toString(qtdMovimentos));

        } catch (IOException e) {
            System.out.println("ERRO na escrita do arquivo");
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    System.out.println("Nao foi possivel fechar o escritor de arquivo");
                }

            }
        }
    }

    /**
     * Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() {
        imprimirBoasVindas();
        int nivelDificuldade = escolherNivelDificuldade();

        if (nivelDificuldade == 1) {
            qtdMovimentos = 30;
            System.out.println("Atençao! Você só pode se mover 30 vezes pelo mapa!");
        } else if (nivelDificuldade == 2) {
            qtdMovimentos = 25;
            System.out.println("Atençao! Você só pode se mover 25 vezes pelo mapa!");
        } else {
            qtdMovimentos = 20;
            System.out.println("Atençao! Você só pode se mover 20 vezes pelo mapa!");
        }

        insereQtdMovimentosArq();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.

        // enquanto o jogador tiver movimentos, pega comando, se os movimentos acabarem
        // e ele nao conseguir vencer o jogo, ele perde

        boolean terminado = false;
        while (!terminado) {

            if (qtdMovimentos == 0) {
                System.out.println("Ah, não! Você não foi rapido o bastante, os Zumbis te pegaram!");
                break;
            }

            System.out.println("\nVoce ainda tem " + qtdMovimentos + " movimentos restantes!");

            Comando comando = analisador.pegarComando();

            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas() {
        System.out.println();
        System.out.println("Bem-vindo ao jogo FUJA!");
        System.out.println("Seu objetivo é escapar da sua casa que foi invadida por vários zumbis.");
        System.out.println("Digite 'ajuda' se você precisar de ajuda.");
        System.out.println();

        descricaoAmbiente();
    }

    private int escolherNivelDificuldade() {
        Scanner entrada = new Scanner(System.in);

        while (true) {

            System.out.print("\nEscolha o nível de dificuldade (1 a 3): ");
            nivelDificuldade = entrada.nextInt();

            if (nivelDificuldade >= 1 && nivelDificuldade <= 3) {
                break;
            } else {
                System.out.println("Nível inválido! Tente novamente.");
                System.out.println();
            }
        }

        System.out.println("\nVocê escolheu o nível " + nivelDificuldade + "!");
        System.out.println();

        return nivelDificuldade;
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * 
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) {
        boolean querSair = false;

        if (comando.ehDesconhecido()) {
            System.out.println("Eu não entendi o que você disse... Digite 'ajuda' se você precisar de ajuda.");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();

        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();

        } else if (palavraDeComando.equals("ir")) {

            qtdMovimentos--;
            insereQtdMovimentosArq();

            if (ambienteSeguro) {
                irParaAmbiente(comando);
            } else {
                Inimigo inimigoAtual = ambienteAtual.getInimigo();
                System.out.println("Você precisa derrotar todos os inimigos antes de prosseguir!");
                System.out.println("Você encontrou um " + inimigoAtual.getNome() + ". Você pode derrotá-lo com: "
                        + inimigoAtual.getItemDerrotar().getNome() + "!");
                System.out.println("atacar");
            }

        } else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);

        } else if (palavraDeComando.equals("pegar")) {
            pegarItem();

        } else if (palavraDeComando.equals("atacar")) {
            lidarComInimigos();

        } else if (palavraDeComando.equals("usar")) {
            usarItem();
        }

        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de
     * palavras de comando
     */

    private void imprimirAjuda() {
        System.out.println("Você está ouvindo sons estranhos e não sabe o que fazer...");
        System.out.println("Hora de tomar uma decisão!.");
        System.out.println();
        System.out.println("Suas palavras de comando são:");
        System.out.println("   ir sair ajuda atacar pegar usar");
    }

    /**
     * Tenta ir em uma direcao. Se existe uma saida entra no
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);

        if (proximoAmbiente == null) {
            System.out.println("Não há passagem!");
        } else {
            if (proximoAmbiente.getInimigo() != null) {
                Inimigo inimigoAtual = proximoAmbiente.getInimigo();
                if (personagem.possuiItem(inimigoAtual.getItemDerrotar())) {
                    ambienteAtual = proximoAmbiente;
                    ambienteSeguro = false;
                    System.out.println("Você encontra um " + inimigoAtual.getNome() + ". Você pode derrotá-lo com: "
                            + inimigoAtual.getItemDerrotar().getNome() + "!");
                    System.out.println("atacar");
                } else {
                    if (inimigoAtual != null) {
                        System.out.println("Você encontra um " + inimigoAtual.getNome()
                                + "! Você precisa derrotá-lo para prosseguir.");
                        descricaoAmbiente();
                    }
                }

            } else {
                if (proximoAmbiente.estaTrancado() == false) {
                    ambienteAtual = proximoAmbiente;
                    descricaoAmbiente();
                } else {
                    System.out.println("O ambiente está trancado! Você precisa de uma " + proximoAmbiente.getChave()
                            + " para destrancá-lo.");
                    descricaoAmbiente();
                }
            }

            // Verifica se o jogador chegou à saída (ambiente final)
            if (ambienteAtual.getDescricao().equals("na saída.")) {
                System.out.println("Você conseguiu escapar! Parabéns!");
                System.out.println("Obrigado por jogar. Ate mais!");
                System.exit(0);
            }
        }

    }

    private void descricaoAmbiente() {
        System.out.println("Você está " + ambienteAtual.getDescricao());

        System.out.print("Saidas: ");
        if (ambienteAtual.getSaida("norte") != null) {
            System.out.print("norte ");
        }
        if (ambienteAtual.getSaida("leste") != null) {
            System.out.print("leste ");
        }
        if (ambienteAtual.getSaida("sul") != null) {
            System.out.print("sul ");
        }
        if (ambienteAtual.getSaida("oeste") != null) {
            System.out.print("oeste ");
        }
        System.out.println();

        if (ambienteAtual.getItem() != null) {
            System.out.println("Há um item no ambiente: " + ambienteAtual.getItem().getNome());
        }
    }

    /**
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * 
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) {
        if (comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        } else {
            return true; // sinaliza que nos queremos sair
        }
    }

    private void lidarComInimigos() {
        if (ambienteAtual.getInimigo() != null) {
            Inimigo inimigoAtual = ambienteAtual.getInimigo();
            if (personagem.possuiItem(inimigoAtual.getItemDerrotar())) {
                System.out.println("Você derrotou o " + inimigoAtual.getNome() + " com "
                        + inimigoAtual.getItemDerrotar().getNome() + "!");

                personagem.removerItem(inimigoAtual.getItemDerrotar());
                ambienteAtual.removerInimigo(inimigoAtual);
                ambienteSeguro = true;
                descricaoAmbiente();
            } else {
                System.out.println("Você não possui o item necessário para derrotar o " + inimigoAtual.getNome() + "!");
            }
        } else {
            System.out.println("Não há inimigos para atacar!");
        }
    }

    private void pegarItem() {
        if (ambienteAtual.getItem() != null) {
            Item itemAtual = ambienteAtual.getItem();
            personagem.adicionarItem(itemAtual);
            ambienteAtual.removerItem();
            System.out.println("Você pegou o item: " + itemAtual.getNome());
            System.out.println(itemAtual.getDescricao());
            descricaoAmbiente();
        } else {
            System.out.println("Não há itens para pegar!");
        }
    }

    public void usarItem() {
        if (personagem.getInventario().size() > 0) {
            System.out.println("Você possui os seguintes itens: ");
            for (Item item : personagem.getInventario()) {
                System.out.println(item.getNome());
            }

            System.out.println("Digite o nome do item que deseja usar: ");
            Scanner entrada = new Scanner(System.in);
            String nomeItem = entrada.nextLine();

            for (Item item : personagem.getInventario()) {
                if (item.getNome().equals(nomeItem)) {
                    if (ambienteAtual.getSaida("norte") != null) {
                        ambienteAtual.getSaida("norte").destrancarAmbiente(nomeItem);
                    }
                    if (ambienteAtual.getSaida("leste") != null) {
                        ambienteAtual.getSaida("leste").destrancarAmbiente(nomeItem);
                    }
                    if (ambienteAtual.getSaida("sul") != null) {
                        ambienteAtual.getSaida("sul").destrancarAmbiente(nomeItem);
                    }
                    if (ambienteAtual.getSaida("oeste") != null) {
                        ambienteAtual.getSaida("oeste").destrancarAmbiente(nomeItem);
                    }

                    // Remover o item do inventário após ser utilizado
                    System.out.println("Você usou a " + nomeItem + " para destrancar o ambiente.");
                    personagem.removerItem(item);
                    descricaoAmbiente();
                    return;
                }
            }

            System.out.println("Item não encontrado no inventário.");
        } else {
            System.out.println("Você não possui itens!");
        }
    }
}