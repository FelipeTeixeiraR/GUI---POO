import java.util.*;

public class Jogar {

    ArrayList<Jogador> jogadores = new ArrayList<>();
    private int numeroRodadas;
    private int dificuldade;

    // Lista onde guardamos as Pergunta criadas para que a verificacao use exatamente
    // as mesmas perguntas que foram mostradas na GUI.
    private ArrayList<Pergunta> perguntasGeradas = new ArrayList<>();

    public void cadastrarJogadores(Jogador j){
        jogadores.add(j);
    }

    public void criarJogadoresAutomaticamente(int quantJog, int numeroRodadas, int dificuldade, List<String> nomes){
        this.numeroRodadas = numeroRodadas;
        this.dificuldade = dificuldade;

        for (int i=0; i<quantJog; i++){
            Jogador j = new Jogador(i + 1, nomes.get(i));
            cadastrarJogadores(j);
        }
    }

    // Gera as contas já indicando jogador e rodada e PREENCHE perguntasGeradas.
    // Retorna apenas as strings de exibição (para a GUI).
    public ArrayList<String> pegarTextosParaGUI(){
        ArrayList<String> contas = new ArrayList<>();
        perguntasGeradas.clear(); // limpar caso seja chamado mais de uma vez

        for (int rodada = 1; rodada <= numeroRodadas; rodada++){
            for (int j = 0; j < jogadores.size(); j++){

                Pergunta p;
                switch (dificuldade){
                    case 1: p = new PerguntaFacil(); break;
                    case 2: p = new PerguntaMediana(); break;
                    case 3: p = new PerguntaDificil(); break;
                    default: p = new PerguntaFacil(); break;
                }

                p.criarPergunta();                // gera a pergunta e sua resposta correta
                perguntasGeradas.add(p);          // guarda o objeto PERGUNTA para usar depois

                String conta = "Rodada " + rodada +
                        " — Jogador: " + jogadores.get(j).getNome() +
                        " → " + p.mostrarPergunta() + " = ?";

                contas.add(conta);
            }
        }
        return contas;
    }

    // Executa jogo usando respostas enviadas pelo GUI — verifica usando perguntasGeradas.
    public ArrayList<String> iniciarComRespostas(List<Integer> respostas) {
        ArrayList<String> resultados = new ArrayList<>();

        if (respostas == null) respostas = new ArrayList<>();


        int total = Math.min(respostas.size(), perguntasGeradas.size());
        int perguntaIndex = 0;
        for (int rodada = 1; rodada <= numeroRodadas; rodada++){
            for (int j = 0; j < jogadores.size(); j++){


                Pergunta p = perguntasGeradas.get(perguntaIndex);
                int respostaJog = respostas.get(perguntaIndex);
                perguntaIndex++;

                Jogador jog = jogadores.get(j);

                boolean acertou = p.verificarResposta(respostaJog);
                if (acertou) {
                    jog.adicionarPontos(5);
                    resultados.add(jog.getNome() + " acertou (+5)");
                } else {
                    resultados.add(jog.getNome() + " errou");
                }
            }
        }

        // Ordena jogadores por pontos decrescente
        jogadores.sort(Comparator.comparingInt(Jogador::getPontos).reversed());

        resultados.add("=== PLACAR FINAL ===");
        for (Jogador jog : jogadores){
            resultados.add(jog.getNome() + " → " + jog.getPontos() + " pontos");
        }

        return resultados;
    }
}
