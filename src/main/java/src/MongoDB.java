package src;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

import java.net.ConnectException;

class MongoDB {

    private MongoDatabase database;

    MongoDB(String databaseName) {
        MongoClient client = new MongoClient();
        this.database = client.getDatabase(databaseName);
    }


}
