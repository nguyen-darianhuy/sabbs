package sabbs;
public class Customer{
    private int id;
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "(\"" + name + "\")";
    }
}