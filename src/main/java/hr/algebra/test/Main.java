package hr.algebra.test;

import hr.algebra.test.model.Polaznik;
import hr.algebra.test.model.ProgramObrazovanja;
import hr.algebra.test.model.Upis;
import hr.algebra.test.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== IZBORNIK =====");

            System.out.println("1. Dodaj polaznika");
            System.out.println("2. Dodaj program");
            System.out.println("3. Upisi polaznika");
            System.out.println("4. Prebaci polaznika");
            System.out.println("5. Ispis");
            System.out.println("6. Izlaz");

            int izbor = Integer.parseInt(sc.nextLine());

            switch (izbor) {

                case 1:
                    dodajPolaznika();
                    break;

                case 2:
                    dodajProgram();
                    break;

                case 3:
                    upisiPolaznika();
                    break;

                case 4:
                    prebaciPolaznika();
                    break;

                case 5:
                    ispisi();
                    break;

                case 6:
                    System.exit(0);
            }
        }
    }

    static void dodajPolaznika() {

        Session session = HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Transaction tx = session.beginTransaction();

        try {

            System.out.print("Ime: ");
            String ime = sc.nextLine();

            System.out.print("Prezime: ");
            String prezime = sc.nextLine();

            Polaznik p = new Polaznik();

            p.setIme(ime);
            p.setPrezime(prezime);

            session.persist(p);

            tx.commit();

            System.out.println("Polaznik spremljen!");

        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();
        }
    }
    static void dodajProgram() {

        Session session = HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Transaction tx = session.beginTransaction();

        try {

            System.out.print("Naziv: ");
            String naziv = sc.nextLine();

            System.out.print("CSVAT: ");
            int csvat =
                    Integer.parseInt(sc.nextLine());

            ProgramObrazovanja po = new ProgramObrazovanja();

            po.setNaziv(naziv);
            po.setCsvat(csvat);

            session.persist(po);

            tx.commit();

            System.out.println("Program spremljen!");

        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();
        }
    }
    static void upisiPolaznika() {

        Session session =HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Transaction tx = session.beginTransaction();

        try {

            System.out.print("ID polaznika: ");
            int polaznikID =
                    Integer.parseInt(sc.nextLine());

            System.out.print("ID programa: ");
            int programID =
                    Integer.parseInt(sc.nextLine());

            Polaznik polaznik = session.get(
                            Polaznik.class,
                            polaznikID
                    );

            ProgramObrazovanja program = session.get(
                            ProgramObrazovanja.class,
                            programID
                    );

            Upis upis = new Upis();

            upis.setPolaznik(polaznik);
            upis.setProgramObrazovanja(program);

            session.persist(upis);

            tx.commit();

            System.out.println("Upis spremljen!");

        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();
        }
    }
    static void prebaciPolaznika() {

        Session session = HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Transaction tx = session.beginTransaction();

        try {

            System.out.print("ID polaznika: ");
            int polaznikID = Integer.parseInt(sc.nextLine());

            System.out.print("Novi ID programa: ");
            int noviProgramID = Integer.parseInt(sc.nextLine());

            Upis upis = session.createQuery(
                                    "FROM Upis WHERE polaznik.polaznikID = :pid",
                                    Upis.class
                            )
                            .setParameter("pid", polaznikID)
                            .uniqueResult();

            ProgramObrazovanja noviProgram = session.get(
                            ProgramObrazovanja.class,
                            noviProgramID
                    );

            upis.setProgramObrazovanja(noviProgram);

            session.merge(upis);

            tx.commit();

            System.out.println("Polaznik prebačen!");

        } catch (Exception e) {

            tx.rollback();

            e.printStackTrace();

        } finally {

            session.close();
        }
    }
    static void ispisi() {

        Session session =  HibernateUtil
                        .getSessionFactory()
                        .openSession();

        var lista = session.createQuery(
                        "FROM Upis",
                        Upis.class
                ).list();

        for (Upis u : lista) {

            System.out.println( u.getPolaznik().getIme()
                            + " "
                            + u.getPolaznik().getPrezime()
                            + " --- "
                            + u.getProgramObrazovanja().getNaziv()
            );
        }

        session.close();
    }
}