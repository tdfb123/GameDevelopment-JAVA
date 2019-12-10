package programaleitura;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      /*Scanner scan = new Scanner(System.in);
        String nome,idade,peso,cidade,estado,pais;
        System.out.println("Digite seu nome: ");
        nome = scan.nextLine();
        System.out.println("Digite sua idade: ");
        idade = scan.nextLine();
        System.out.println("Digite seu peso: ");
        peso = scan.nextLine();
        System.out.println("Digite sua cidade: ");
        cidade = scan.nextLine();
        System.out.println("Digite seu estado: ");
        estado = scan.nextLine();
        System.out.println("Digite seu país: ");
        pais = scan.nextLine();
        //Gerar resultado final:
        System.out.println("---------------");
        System.out.println("Aqui está o resultado do teste:");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Peso: " + peso);
        System.out.println("Cidade: " + cidade);
        System.out.println("Estado: " + estado);
        System.out.println("País: " + pais);*/

        Scanner scan = new Scanner(System.in);
        String nomeJogador;
        Random rand = new Random();
        rand.nextInt(100);
        System.out.println("Bem vindo ao jogo! Insira seu nome: ");
        nomeJogador = scan.nextLine();
        System.out.println("Seja muito bem vindo " + nomeJogador);
        System.out.println("Para qual direção deseja avançar? (w,s,a,d)");
        String comando = scan.nextLine();
        if(comando.equals("w")) {
            System.out.println("Você esta caminhando para frente!");
            System.out.println("Um inimigo surgiu, o que deseja fazer?(a=atacar,c=correr");
            comando = scan.nextLine();
            if(comando.equals("a")) {
                if(rand.nextInt(100) < 75) {
                    System.out.println("Você ganhou o jogo!");
                }else
                    System.out.println("Você perdeu o jogo!");

            }else {
                System.out.println("Você correu! O jogo terminou!");
            }
            if(comando.equals("s")) {
                System.out.println("Você está entrando em outro mapa...");

            }
        }
    }

}
