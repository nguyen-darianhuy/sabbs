public class Customer{
    int id;
    String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return "(\"" + name + "\")";
    }
}