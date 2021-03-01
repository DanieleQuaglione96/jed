package com.example.jed.s06;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.example.jed.s05.CoderPlain;

public abstract class HibernateUtil {
    private static SessionFactory sessionFactory;

    static { //static initializer: to initialize a static data member.
        Configuration configuration = new Configuration();
        Properties settings = new Properties();

        settings.put(Environment.DATASOURCE, "java:comp/env/jdbc/me"); 
        settings.put(Environment.SHOW_SQL, "true"); //Dico ad hibernate di far vedere nel log il suo codice sql
        configuration.setProperties(settings);
        configuration.addAnnotatedClass(CoderPlain.class); //Per usare coder plain in hibernate devo dirglielo

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry); //Creo la sessionFactory
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
