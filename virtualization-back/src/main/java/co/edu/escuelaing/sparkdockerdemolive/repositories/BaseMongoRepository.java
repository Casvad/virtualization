package co.edu.escuelaing.sparkdockerdemolive.repositories;

import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMongoRepository<T>{

    private final MongoCollection<T> collection;

    protected BaseMongoRepository(MongoCollection<T> collection) {
        this.collection = collection;
    }

    public T saveOne(T element){

        this.collection.insertOne(element);

        return element;
    }

    public List<T> findAll(){
        List<T> results = new ArrayList<>();

        this.collection.find().iterator().forEachRemaining(results::add);

        return results;
    }
}
