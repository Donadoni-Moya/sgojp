package pe.com.unmsm.sgojp.api.dao.impl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bson.conversions.Bson;
import org.codehaus.jackson.map.ObjectMapper;
import pe.com.unmsm.sgojp.api.dao.OpinionDAO;
import static pe.com.unmsm.sgojp.api.dao.Connection.getConnection;
import pe.com.unmsm.sgojp.api.model.service.Opinion;

/**
 *
 * @author Miguel
 */
public class OpinionDAOImpl implements OpinionDAO {

    private final String COLLECTION_NAME = "opinions";
    private final MongoDatabase DATABASE = getConnection();

    @Override
    public List<Opinion> filterUserId(String userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean register(Opinion e) {
        MongoCollection<Opinion> collection = DATABASE.getCollection(COLLECTION_NAME, Opinion.class);
        ObjectMapper oMapper = new ObjectMapper();
        BasicDBObject doc = new BasicDBObject(oMapper.convertValue(e, Map.class));
        collection.insertOne(e);
        return true;
    }

    @Override
    public boolean update(Opinion e) {
        MongoCollection<Opinion> collection = DATABASE.getCollection(COLLECTION_NAME, Opinion.class);
        ObjectMapper oMapper = new ObjectMapper();
        BasicDBObject a = new BasicDBObject("id",e.getId());
        a.put("comment","eeeeeeeeeee");
        collection.updateMany(new BasicDBObject("_id", e.getId()),a );

        return true;
    }

    @Override
    public Opinion get(String id) {
        Opinion user = null;
        MongoCollection<Opinion> collection = DATABASE.getCollection(COLLECTION_NAME, Opinion.class);

        MongoCursor<Opinion> cursor = collection.find(new BasicDBObject("id", id)).iterator();
        try {
            while (cursor.hasNext()) {
                user = cursor.next();
            }
        } finally {
            cursor.close();
        }
        return user;
    }

    @Override
    public List<Opinion> getAll() {

        MongoCollection<Opinion> collection = DATABASE.getCollection(COLLECTION_NAME, Opinion.class);
        MongoCursor<Opinion> cursor = collection.find().iterator();
        List<Opinion> lista = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                lista.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return lista;
    }

    @Override
    public boolean remove(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        OpinionDAOImpl od = new OpinionDAOImpl();
        Opinion op = new Opinion();
        op.setId("1");
        op.setUser_id("1");
        op.setComment("22221");
        op.setDate("2019-11-11");

//        od.register(op);
        od.update(op);
    }

}
