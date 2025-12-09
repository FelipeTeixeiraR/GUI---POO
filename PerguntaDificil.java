import java.util.Random;

public class PerguntaDificil extends Pergunta {

    public PerguntaDificil(String textoConta, int resposta){
        super(textoConta, resposta);
    }

    public PerguntaDificil() {

    }

    @Override
    public void criarPergunta(){
        Random random = new Random();
        int tconta = random.nextInt(4);
        int a, b;

        switch (tconta){
            case 0: a = random.nextInt(20000, 100000);
                b = random.nextInt(20000, 100000);
                setResposta(a + b);
                setTextoConta(a + " + " + b);
                break;
            case 1: a = random.nextInt(20000, 100000);
                b = random.nextInt(20000, 100000);
                setResposta(a - b);
                setTextoConta(a + " - " + b);
                break;
            case 2: a = random.nextInt(1000, 2000);
                b = random.nextInt(1000, 2000);
                setResposta(a * b);
                setTextoConta(a + " * " + b);
                break;
            case 3: a = random.nextInt(2000, 10000);
                b = random.nextInt(200, 500);
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