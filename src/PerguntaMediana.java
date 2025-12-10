import java.util.Random;

public class PerguntaMediana extends Pergunta{
    public PerguntaMediana(String textoConta, int resposta){
        super(textoConta, resposta);
    }

    public PerguntaMediana() {

    }

    @Override
    public void criarPergunta(){
        Random random = new Random();
        int tconta = random.nextInt(4);
        int a, b;

        switch (tconta){
            case 0: a = random.nextInt(2000, 10000);
                b = random.nextInt(2000, 10000);
                setResposta(a + b);
                setTextoConta(a + " + " + b);
                break;
            case 1: a = random.nextInt(2000, 10000);
                b = random.nextInt(2000, 10000);
                setResposta(a - b);
                setTextoConta(a + " - " + b);
                break;
            case 2: a = random.nextInt(100, 200);
                b = random.nextInt(100, 200);
                setResposta(a * b);
                setTextoConta(a + " * " + b);
                break;
            case 3: a = random.nextInt(200, 1000);
                b = random.nextInt(20, 50);
                setResposta(a / b);
                setTextoConta(a + " / " + b);
                break;
        }
    }
    @Override
    public String mostrarPergunta(){
        return getTextoConta();
    }
}