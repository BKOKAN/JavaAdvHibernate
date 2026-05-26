package hr.algebra.test.util;

import hr.algebra.test.model.Polaznik;
import hr.algebra.test.model.ProgramObrazovanja;
import hr.algebra.test.model.Upis;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory =
            buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try {

            return new Configuration()
                    .configure("META-INF/hibernate.cfg.xml")

                    .addAnnotatedClass(Polaznik.class)
                    .addAnnotatedClass(ProgramObrazovanja.class)
                    .addAnnotatedClass(Upis.class)

                    .buildSessionFactory();

        } catch (Throwable ex) {

            System.err.println(
                    "SessionFactory creation failed."
            );

            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}