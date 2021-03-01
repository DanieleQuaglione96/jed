package com.example.jed.s06;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.example.jed.s05.CoderPlain;

public class CoderDao {
    public List<CoderPlain> getAll() {
        try (Session session = HibernateUtil.getSession()) { //Try with resources
            // explicit HQL query is "select c from CoderPlain c" -> vado a prendere tutti gli oggetti CoderPlain (non c'è il where)
            Query<CoderPlain> query = session.createQuery("from CoderPlain", CoderPlain.class);
            return query.list(); //Eseguo la query e il risultato è ritornato come lista
        }
    }
}
//La sessione è tutto quello che gestisce la connessione col database