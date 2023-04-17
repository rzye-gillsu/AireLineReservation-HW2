import java.util.ArrayList;
import java.util.Scanner;

public class Signing {
    private String username;
    private String password;
    private Passengers p = new Passengers();

    private Scanner input = new Scanner(System.in);

    public void signingMenu() {
        System.out.println("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                           WELCOME TO AIRLINE RESERVATION SYSTEM
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ..........................MENU OPTIONS........................

                    <1> Sign in
                    <2> Sign up""");
        int option = input.nextInt();
        switch (option) {
            case 1 -> {
                signIN();
                break;
            }
            case 2 -> {
                signUP();
                signingMenu();
                break;
            }
        }

    }

    private void signIN() {
        gettingInput();
        checkSignIn();
    }

    private void checkSignIn() {
        if (isAdmin()) {
            System.out.println("\n\nWelcome back Admin!\n\n");
            Admin admin = new Admin();
            admin.adminMenu();
        } else {
            ArrayList<Passenger> passengers = p.getPassengers();
//            if (passengers.get())
            p.userMenu();
        }
    }

    private void signUP() {
        gettingInput();
        checkSignUP();
    }

    private void checkSignUP() {
        // saves 2 same user after password-2
        // stuck in the loop after username-2
        ArrayList<Passenger> passengers = p.getPassengers();

        if (userIsValid(passengers)) {
            addUser(passengers);
        }
    }

    private boolean userIsValid(ArrayList<Passenger> passengers) {
        if (isAdmin()) {
            System.out.println("You are predefined. Sign in to continue...");
        } else {
            for (int i = 0; i < passengers.size(); i++) {
                if (passengers.get(i).getUsername().equals(username)) {
                    System.out.println("\n!!!This username is already taken. Try it again: ");
                    signUP();
                    break;
                }
            }
            if (password.length() < 4) {
                System.out.println("\n!!!Password must contain at least 4 characters. Try it again: ");
                signUP();
            }
        }
        return true;
    }

    private void addUser(ArrayList<Passenger> passengers) {

        Passenger passenger = new Passenger();
        passenger.setUsername(username);
        passenger.setPassword(password);
        passengers.add(passenger);

        p.setPassengers(passengers);
        p.printPassengers();
    }


    private void gettingInput() {
        System.out.print("Username: ");
        username = input.next();
        System.out.print("Password (Need to be at least 4 characters): ");
        password = input.next();
    }

    private boolean isAdmin() {
        return username.equals("Admin") && password.equals("Admin");
    }
}
