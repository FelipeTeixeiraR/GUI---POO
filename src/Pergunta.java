public class Pergunta {

    private String textoConta;
    private int resposta;

    public Pergunta(){}



    public Pergunta(String textoConta, int resposta) {
    }


    public void criarPergunta(){}

    public String mostrarPergunta(){
        return textoConta;
    }
    public Boolean verificarResposta(int respJogador){
        if (resposta == respJogador){
            return true;
        }
        else {
            return false;
        }
    }


    public String getTextoConta() {return this.textoConta;}

    public void setResposta(int resposta) {this.resposta = resposta;}
    public void setTextoConta(String textoConta) {this.textoConta = textoConta;}
}