package entity;

public class Customer extends User{

    private String address;
    private String username;
    private String password;
    private Integer Id;

    public Customer(Integer Id){
        this.Id=Id;
    }

    public Customer(String address) {
        this.address = address;
    }

    public Customer(int id, String username, String password, String address) {
        super(id, username, password);
        this.address = address;
    }

    public Customer(String username, String password, String address) {
        super(username, password);
        this.address = address;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "address='" + address + '\'' +
                '}';
    }

}
