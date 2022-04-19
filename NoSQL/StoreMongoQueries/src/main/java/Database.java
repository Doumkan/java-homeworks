import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.Arrays;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Database {
    private static MongoDatabase database = MongoClients.create("mongodb://localhost:27017").getDatabase("stores");
    private static MongoCollection<Document> products = database.getCollection("products");
    private static MongoCollection<Document> stores = database.getCollection("store_list");

    public static MongoCollection<Document> getProducts() {
        return products;
    }

    public static MongoCollection<Document> getStores() {
        return stores;
    }

    public static void displayUsingPojo(String productName, String storeName) {
        CodecRegistry pojoRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        //get a hold of our collection, using the codec registry, and
        //specifying the Product class as our POJO for the pojoRegistry
        MongoCollection<Product> productsPojo = database.getCollection("products", Product.class).withCodecRegistry(pojoRegistry);
        MongoCollection<Store> storesPojo = database.getCollection("store_list", Store.class).withCodecRegistry(pojoRegistry);

        //create a query to retrieve a document with the given product name
        Bson queryFilterProduct = new Document("name", productName);
        Bson queryFilterStore = new Document("name", storeName);

        //use the query to pipe the document into a Product object in the quick line
        Product pojoProduct = productsPojo.find(queryFilterProduct).iterator().tryNext();
        Store pojoStore = storesPojo.find(queryFilterStore).iterator().tryNext();

        //make sure it worked and the object is not empty
        assert pojoStore != null;
        assert pojoProduct != null;

        pojoStore.addProductToStore(pojoProduct);
    }

    public static Document findStoreByName(String storeName) {
        return stores.find(new BasicDBObject("name", storeName)).first();
    }

    public static Document findProductByName(String productName) {
        return products.find(new BasicDBObject("name", productName)).first();
    }

    public static void getStats() {
        products.aggregate(Arrays.asList(
                Aggregates.lookup("store_list", "name", "product_list.name", "shop_list"),
                Aggregates.unwind("$shop_list"),
                Aggregates.group("$shop_list.name",
                        Accumulators.avg("avg_price", "$price"),
                        Accumulators.max("max_price", "$price"),
                        Accumulators.min("min_price", "$price"),
                        Accumulators.sum("product_count", 1))
        )).forEach(document -> System.out.println(document.toJson()));

        products.aggregate(Arrays.asList(
                        Aggregates.lookup("store_list", "name", "product_list.name", "shop_list"),
                        Aggregates.unwind("$shop_list"),
                        Aggregates.match(Filters.lt("price", 10)),
                        Aggregates.group("$shop_list.name",
                                Accumulators.sum("amount_products_cheaper_than_10", 1))))
                .forEach(document -> System.out.println(document.toJson()));

    }
}
