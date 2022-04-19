import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.conversions.Bson;

import java.util.List;

public class Store {
    private String name;
    @BsonProperty("product_list")
    private List<Document> productList;

    public Store() {

    }

    public void addProductToStore(Product product) {
        Document pro = new Document("name", product.getName())
                .append("price", product.getPrice());
        Bson filter = Filters.eq("name", name);
        Bson update = Updates.push("product_list", pro);

        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions()
                .returnDocument(ReturnDocument.AFTER);

        Database.getStores().findOneAndUpdate(filter, update, options);
    }

    //check if a store with given name already exists. If not, add it to db
    public void createStore(String storeName) {
        if (Database.findStoreByName(storeName) != null) {
            System.out.println("Store with the name '" + storeName + "' already exists. Please try another name.");
        } else {
            Document store = new Document("name", storeName).append("product_list", productList);
            Database.getStores().insertOne(store);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getProductList() {
        return productList;
    }

    public void setProductList(List<Document> productList) {
        this.productList = productList;
    }
}
