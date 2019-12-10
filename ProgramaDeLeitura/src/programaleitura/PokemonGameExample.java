package programaleitura;

import java.util.Scanner;
import java.util.Random;

public class PokemonGameExample {
    public static void main(String[] args) {
        String nomeDoJogador;
        int jogadorAtk;
        int escolhaDoJogador = 0;
        String nomeDoNpc = "Gary";
        String pokemonDoNpc = "Charmander";
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Bem vindo ao PokemonGameExample 0.1" +
                "\nComo é o seu nome? ");
        nomeDoJogador = scanner.nextLine();
        System.out.println("Certo " + nomeDoJogador + " Você ganhou um Squirtle!");
        System.out.println("Deseja lutar? " +
                "1 = SIM " +
                "2 = NÃO");
        escolhaDoJogador = scanner.nextInt();
        while (escolhaDoJogador == 1) {
            System.out.println("Hora da batalha, qual ataque deseja? " +
                    "1 = Bubbles, " +
                    "2 = Scratch, " +
                    "3 = Water gun");
            jogadorAtk = scanner.nextInt();
            if (jogadorAtk == 1) {
                if (rand.nextInt(60) <= 50) {
                    System.out.println("Você usou bubbles e causou " + rand.nextInt(60) + " de dano");
                } else {
                    System.out.println("Você deu um crítico de " + rand.nextInt(60) + " e finalizou o outro pokémon!");
                    break;
                }

            }else {
                System.out.println("Sinto muito, seu pokémon ainda não aprendeu esse ataque!");
            }

        }
    }
}