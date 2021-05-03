
public class Workers {
    private String username;        ///Workers variables those are the same with database
    private String pass;
    private String email;
    private int telephone;
    private String address;

    public Workers(String username, String pass, String email, int telephone, String address) { // constructor of the workers
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
    }

    public String getUsername() {           // All the getter setter methods for workers which will come from database
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
