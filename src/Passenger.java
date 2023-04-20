public class Passenger {
    private String username;
    private String password;
    private int charge;

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge += charge;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
