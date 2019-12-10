package SwitchECase;

public class SwitchECase {
    public static void main(String[] args) {

        int vida = 100;

        switch (vida)
        {
            case 100:
                System.out.println("Sua vida está cheia");
                break;
            case 30:
                System.out.println("Sua vida está em 30%");
            break;
            default:
                System.out.println("Nenhuma condição foi aplicada!");
                break;
        }
        vida = 70;
        switch (vida)
        {
            case 70:
                System.out.println("Sua vida está em 70%");
                break;
        }
        vida = 0;
        switch (vida)
        {
            case 0:
                System.out.println("Você desmaiou...");
                break;
        }
    }
}
