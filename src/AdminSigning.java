public class AdminSigning implements Signing {
    private String username;
    private String password;

    public AdminSigning() {
        menu();
    }

    public void menu() {
        int option = printMenu();
        if (option == 1)
            signIN();
        if (option == 2)
            signUP();
    }

    @Override
    public void signUP() {
        System.out.print("Please Enter your favorable username: ");
        username = input.next();
        System.out.print("Please Enter your favorable password: ");
        password = input.next();
        checkSignUp();
    }

    @Override
    public void signIN() {
        System.out.print("Please Enter your username: ");
        username = input.next();
        System.out.print("Please Enter your password: ");
        password = input.next();
        checkSignIn();
    }

    @Override
    public void checkSignUp() {
        if (username == "Admin" && password == "Admin") {
            System.out.println("You are predefined. Sign in to continue...");
            menu();
        }
        else {
            System.out.println("Your username and password are successfully submitted.");
            menu();
            // is a new user.
        }
    }

    @Override
    public void checkSignIn() {
        if (username == "Admin" && password == "Admin") {
            Admin admin = new Admin();
            System.out.println("Welcome Back Admin!");
            admin.adminMenu();
        }
        else {
            // search the users data
            System.out.println("");
            // is a new user.
        }
    }

}
