package POO;

public class Main {

    private static int exemplo() {
        return 10;
    }

    private static String exemplo2() {
        return "Thiago";
    }

    public void outroMetodo(int n1, String nome) {
        System.out.println(n1);
        System.out.println(nome);

    }

    public static void main(String[] args) {
        System.out.println(exemplo());
        System.out.println(exemplo2());
        new Main().outroMetodo(10, "Thiago");

    }

}