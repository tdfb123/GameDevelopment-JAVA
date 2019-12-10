package Lopping;

public class Lopping {
    public static void main(String[] args) {
        int contador = 0;
        while (contador < 11) {
            System.out.println("Contador utilizando while - " + contador);
            contador++;//pode se usar o ++ ou +=1 para adicionar ao contador.
        }
        //outro método de realizar o looping usando for:
        for (int i = 0; i < 11; i++) { // declarado a variavel, a condicao e a adicao ao contador na mesma linha respectivamente
            System.out.println("Contador utilizando for - " + i);
        }
        //mais outro método do looping, executar o código enquanto while for verdadeiro:
        int contador2 = 0;
        do {
            System.out.println("Contador utilizando do - " + contador2);
            contador2++;
        } while (contador2 < 11);
    }
}