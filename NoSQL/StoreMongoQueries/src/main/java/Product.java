import org.bson.Document;

public class Product {
    private String name;
    private int price;

    public Product() {

    }

    //check if a product with given name already exists. If not, add it to db
    public void addProductToList(String name, int price) {
        if (Database.findProductByName(name) != null) {
            System.out.println("Product with the name '" + name + "' already exists. Please try another one.");
        } else {
            Document product = new Document("name", name).append("price", price);
            Database.getProducts().insertOne(product);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
