package AitorRodriguez.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        System.out.println("START");

        Configuration cfg = new Configuration();
        cfg.configure("com/aitor/hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        session.close();
        factory.close();
    }
}