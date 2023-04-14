import java.util.Scanner;

public interface Signing {
    Scanner input = new Scanner(System.in);
    void signUP();
    void signIN();
    void checkSignUp();
    void checkSignIn();

    default int printMenu() {
        System.out.println("1-> Sign in\n2-> Sign up\n\tYour option: ");
        int option = input.nextInt();
        return option;
    }

}
