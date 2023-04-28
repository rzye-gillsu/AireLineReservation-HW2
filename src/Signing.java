import java.util.ArrayList;
import java.util.Scanner;
import SerializingTools.Serialized;

public class Signing {
    private Admin admin;
    private String username;
    private String password;
    private Passengers p = new Passengers();
    private Scanner input = new Scanner(System.in);

    public Signing() {
        admin = Admin.getInstance();
    }

    public void signingMenu() {
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                           WELCOME TO AIRLINE RESERVATION SYSTEM
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ..........................MENU OPTIONS........................

                    <1> Sign in
                    <2> Sign up
                    <3> Exit
                 Your option:\040""");
        int option = input.nextInt();
        if (option == 3) {
            (Serialized.getInstance()).writeFile(admin.getFlightTreeMap(), p.getPassengers());
            System.out.println("\nSee you soon!");
            System.exit(0);
        }
        switch (option) {
            case 1 -> signIN();
            case 2 -> signUP();
        }
        signingMenu();
    }

    private void signIN() {
        gettingInput();
        checkSignIn();
    }

    /**
     * Checks whether Admin is signing or not and user with given username and password is available.
     */
    private void checkSignIn() {
        int check = 0;
        if (isAdmin()) {
            System.out.println("\n\nWelcome back Admin!\n\n");
            admin.adminMenu();
            return;
        } else {
            ArrayList<Passenger> passengers = p.getPassengers();

            for (var p1 : passengers) {
                if (userFound(p1)) {
                    p.setPassenger(p1);
                    System.out.printf("\n\nWelcome dear %s!\n\n", username);
                    value = true;
                    userMenuHappened();
                    p.userMenu(admin.getFlightTreeMap());
                    check = 1;
                }
            }
        }
        if (check == 0)
            System.out.println("\n!!!User with given username and password is not found.");
        signingMenu();
    }

    private static boolean value = false;

    public static boolean userMenuHappened() {
        return value;
    }

    /**
     * @param p1 passanger
     * @return -> returns true if user with given username and password is found.
     */
    private boolean userFound(Passenger p1) {
        return p1.getUsername().equals(username) && p1.getPassword().equals(password);
    }

    private void signUP() {
        gettingInput();
        checkSignUP();
    }

    /**
     * Checks if the given username and password can be used or not.
     */
    private void checkSignUP() {
        ArrayList<Passenger> passengers = p.getPassengers();

        if (userIsValid(passengers)) {
            addUser(passengers);
        }
    }

    /**
     * checks if username has not been used and before and the password is in the correct syntax.
     *
     * @param passengers
     * @return -> returns true if the username and password is ready to use by the user.
     */
    private boolean userIsValid(ArrayList<Passenger> passengers) {
        if (isAdmin()) {
            System.out.println("You are predefined. Sign in to continue...");
        } else {
            for (var p1 : passengers) {
                if (p1.getUsername().equals(username)) {
                    System.out.println("\n!!!This username is already taken. Try it again: ");
                    signUP();
                    return false;
                }
            }
            if (password.length() < 4) {
                System.out.println("\n!!!Password must contain at least 4 characters. Try it again: ");
                signUP();
                return false;
            }
        }
        return true;
    }

    /**
     * Sets username and password and generates an instance of Ticket class.";
     *
     * @param passengers
     */
    private void addUser(ArrayList<Passenger> passengers) {

        Passenger passenger = new Passenger();
        passenger.setUsername(username);
        passenger.setPassword(password);
        passenger.setTicket(new Ticket());
        passengers.add(passenger);
        admin.addObserver(passenger);

        p.setPassengers(passengers);
    }


    private void gettingInput() {
        System.out.print("Username: ");
        username = input.next();
        System.out.print("Password (Need to be at least 4 characters): ");
        password = input.next();
    }

    /**
     * Checks if admin can login or not.
     *
     * @return
     */
    private boolean isAdmin() {
        return username.equals("Admin") && password.equals("Admin");
    }
}
