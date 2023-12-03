/**
 *  Essa eh a classe principal da aplicacao "World of Zull".
 *  "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
import java.util.Scanner;

public class Jogo 
{
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private int nivelDificuldade;
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {
        criarAmbientes();
        analisador = new Analisador();
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes()
    {
        Ambiente quarto, corredor, corredor2, banheiro, copa, cozinha, sala, garagem, hall, saida;
      
        // cria os itens
        Item kitMedico = new Item("Kit Medico","Pose ser utilizado para salvar", false);
        Item chave = new Item("chave","Chave que pode ser utilizada para abrir as portas", false);
        Item taco = new Item("Taco de basebol","Pode ser utlizado para atacar os inmigos", false);
        Item pistola = new Item("Pistola","Pode ser utilizada para combater o inimigo",true);
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
        
        // inicializa as saidas dos ambientes
        quarto.ajustarSaidas(corredor, corredor2, null, null);
        corredor.ajustarSaidas(null, copa, quarto, banheiro);
        corredor2.ajustarSaidas(cozinha, null, null, quarto);
        banheiro.ajustarSaidas(null, corredor, null, null);
        copa.ajustarSaidas(null, cozinha, null, corredor);
        cozinha.ajustarSaidas(null, sala, corredor2, copa);
        sala.ajustarSaidas(garagem, null, hall, cozinha);
        garagem.ajustarSaidas(null, null, sala, null);
        hall.ajustarSaidas(sala, saida, null, null);
        saida.ajustarSaidas(null, null, null, hall);
        

        ambienteAtual = quarto;  // o jogo comeca do lado de quarto
    }
    // cria o inimigo
    Personagem mostro = new Inimigo("Mostros", "Mostros são criaturas  que atacam", 70, 8);
    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() 
    {            
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.

        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas()
    {
        System.out.println();
        System.out.println("Bem-vindo ao jogo FUJA!");
        System.out.println("Seu objetivo é escapar da sua casa que foi invadida por vários zumbis.");
        System.out.println("Digite 'ajuda' se você precisar de ajuda.");
        System.out.println();
        
        escolherNivelDificuldade();

        descricaoAmbiente();
    }

    private void escolherNivelDificuldade(){
        Scanner entrada = new Scanner(System.in);
            
        while(true){

            System.out.print("Escolha o nível de dificuldade (1 a 3): ");
            nivelDificuldade = entrada.nextInt();
                
            if(nivelDificuldade >= 1 && nivelDificuldade <= 3){
                break;
            }else{
                System.out.println("Nível inválido! Tente novamente.");
                System.out.println();
            }
        }

        System.out.println("Você escolheu o nível " + nivelDificuldade + "!");
        System.out.println();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu não entendi o que você disse... Digite 'ajuda' se você precisar de ajuda.");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }

        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
     * palavras de comando
     */
    private void imprimirAjuda() 
    {
        System.out.println("Você está ouvindo sons estranhos e não sabe o que fazer...");
        System.out.println("Hora de tomar uma decisão!.");
        System.out.println();
        System.out.println("Suas palavras de comando são:");
        System.out.println("   ir sair ajuda");
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = null;
        if(direcao.equals("norte")) {
            proximoAmbiente = ambienteAtual.saidaNorte;
        }
        if(direcao.equals("leste")) {
            proximoAmbiente = ambienteAtual.saidaLeste;
        }
        if(direcao.equals("sul")) {
            proximoAmbiente = ambienteAtual.saidaSul;
        }
        if(direcao.equals("oeste")) {
            proximoAmbiente = ambienteAtual.saidaOeste;
        }

        if (proximoAmbiente == null) {
            System.out.println("Não há passagem!");
        }
        else {
            ambienteAtual = proximoAmbiente;
            
            descricaoAmbiente();
        
            // Verifica se o jogador chegou à saída (ambiente final)
            if(ambienteAtual.getDescricao().equals("na saída.")) {
                System.out.println("Você conseguiu escapar! Parabéns!");
                System.out.println("Obrigado por jogar. Ate mais!");
                System.exit(0);                                             // Revisar esse
            }
        
        }
    } 

    private void descricaoAmbiente() {
        System.out.println("Você está " + ambienteAtual.getDescricao());
            
            System.out.print("Saidas: ");
            if(ambienteAtual.saidaNorte != null) {
                System.out.print("norte ");
            }
            if(ambienteAtual.saidaLeste != null) {
                System.out.print("leste ");
            }
            if(ambienteAtual.saidaSul != null) {
                System.out.print("sul ");
            }
            if(ambienteAtual.saidaOeste != null) {
                System.out.print("oeste ");
            }
            System.out.println();
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nos queremos sair
        }
    }

}
