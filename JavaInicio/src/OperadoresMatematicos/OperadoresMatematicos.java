package OperadoresMatematicos;


public class OperadoresMatematicos {
    public static void main(String[] args) {


        int vida1 = 100;
        int vida2 = 100;
        int vida3 = 100;
        int vidaFinal = vida1 + vida2 + vida3;

        System.out.println("Sua vida é: " + (vida1 + vida2));
        System.out.println("Sua vida final pode ser até: " + vidaFinal);

        String nomeJogador = "Thiago";
        int skillSword = 99;
        int skillAxe = 95;
        int skillMace = 80;
        int skillCrossbow = 20;
        if(nomeJogador == "Thiago" && skillSword == 100) {
            System.out.println("O jogador " + nomeJogador + " é full skill em Sword");

        }else if(nomeJogador == "Thiago" || nomeJogador == "Fernando" && skillAxe == 95) {
            System.out.println("O jogador " + nomeJogador + " tem " + skillAxe + " de skill em Axe");

        }else{
            System.out.println("Suas skills de sword, axe, mace e crossbow são respectivamente: ");
            System.out.println(skillSword + skillAxe + skillMace + skillCrossbow);
        }
    }
}