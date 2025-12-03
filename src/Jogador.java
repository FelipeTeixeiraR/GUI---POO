public class Jogador {
    private int id;
    private String nome;
    private int pontos;

    public Jogador(int id, String nome){
        this.id = id;
        this.nome = nome;
    }


    public String getNome() {return nome;}
    public int getPontos() {return pontos;}
    public int getId() {return id;}

    public void adicionarPontos(int pontos){this.pontos += pontos;}

    public void setNome(String nome) {this.nome = nome;}
}
