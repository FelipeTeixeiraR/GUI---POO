public class Main {
    public static void main(String[] args) {

        Jogar jogo = new Jogar();

        jogo.criarJogadoresAutomaticamente();
        jogo.iniciar(jogo.getNumeroRodads(), jogo.getDificuldade());

        jogo.pontuacaoFinal();
    }
}
