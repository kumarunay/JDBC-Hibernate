package com.unytech;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Student s1 = new Student();
//
////        s1.setsName("Alex kumari");
////        s1.setRollNo(28);
////        s1.setsAge(40);//here we're updating the age from 45 to 40 with session.merge() method insted of persist()
//
//        Student s2 ;
//        Laptop l1 = new Laptop();
//        l1.setBrand("asus");
//        l1.setModel("Rog");
//        l1.setRam(16);
//        l1.setLid(1);
//
//        Laptop l2 = new Laptop();
//        l2.setBrand("Dell");
//        l2.setModel("XPS");
//        l2.setRam(32);
//        l2.setLid(2);
//
//        Laptop l3 = new Laptop();
//        l3.setBrand("Apple");
//        l3.setModel("mac");
//        l3.setRam(8);
//        l3.setLid(3);
//
//        Alien a1 = new Alien();
//        a1.setAid(101);
//        a1.setAname("unay");
//        a1.setTech("Java");
//
//        Alien a2 = new Alien();
//        a2.setAid(102);
//        a2.setAname("Kumar");
//        a2.setTech("python");

//        Alien a3 = new Alien();
//        a3.setAid(103);
//        a3.setAname("Harika");
//        a3.setTech("SmartCom");

//        a1.setLaptops(Arrays.asList(l1, l2));
//        a2.setLaptops(Arrays.asList(l3));
////        a3.setLaptops(Arrays.asList(l1));

//        l1.setAliens(Arrays.asList(a1,a3));
//        l2.setAliens(Arrays.asList(a1,a2));
//        l3.setAliens(Arrays.asList(a2));

/**
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(com.unytech.Student.class);
        cfg.configure();

        SessionFactory sf = cfg.buildSessionFactory();

        #note: for the above configuration we just reduced the code with SessionFactory Interface
        #note: below we have reduced the Configuration part with SessionFactory as it implements Configuration class
*/
        SessionFactory sf = new Configuration()
                            .addAnnotatedClass(com.unytech.Laptop.class)
                            .addAnnotatedClass(com.unytech.Alien.class)           //.addAnnotatedClass(com.unytech.Student.class)
                            .configure()
                            .buildSessionFactory();

        Session session = sf.openSession();

//        s2 = session.get(Student.class,21);
        /*to get the data from DB we don't need Transaction for fetching the dat a*/

//        s1=session.find(Alien.class, 24);// fetching the data of alex chari to perfom the delete operation

        //for any changes in DB we need transaction, it will be called as transaction and to effect the DB need to commit() the transaction
//        Transaction transaction = session.beginTransaction();
//            session.persist(l1);
//            session.persist(l2);
//            session.persist(l3);
//            session.persist(a1);
//            session.persist(a2);
//            session.persist(a3);

//        session.persist(s1); // as we use persist() to update the age we got error so will use merge()

//        session.merge(s1);// DB details of Alex chari is updated..
        /**# Note : 2nd scenorio we added new details Alex kumari, 28, 40, the same merge() method we used,
        intially it looks for the data with primary key then if it is not found it simply inserting the data ,
         We can use merge() for both (insert/ update)*/

        /** Delete method :: 1st fetch the data using get then used delete/remove()*/
//        session.remove(s1);/** yes, Now Alex chari details are deleted from DB*/

//
//        transaction.commit();

        /**---Lets Query using HQl for the Laptop class to fetch the data ****/
        //select * from laptop where ram=32--->SQL
        // from laptop where ram=32----------->HQL

        /**Below query used from HQL Hibernate Query language */
//        Query query = session.createQuery("from Laptop where ram=32");
//        Query query = session.createQuery("from Laptop where brand like 'asus'");

//        String brand = "Dell";
//        int ram = 16;
//        Query query = session.createQuery("select model, brand from Laptop where brand like ?1  ");/** we need to set the Ordianl Parameter with numbering like ( ?1, ?2, ?3)
//         these are query numbers later need to replace with the query.setParameter()*/
//        query.setParameter(1,brand);
////        query.setParameter(2,ram);
//        List<Object[]> laptops=query.getResultList();
//
//        for(Object[] data: laptops){
//            System.out.println((String)data[0]+" "+(String) data[1]);
//        }
//            /** here we're collecting the data as a object#code from DB so we need to convert it String as diseried output */
//        System.out.println(laptops);

//        Laptop l1 = session.load(Laptop.class, 2);
//        Laptop l1 = session.byId(Laptop.class).getReference(2);

//        Laptop l1 = session.get(Laptop.class, 2);
        /** ""Get() Vs Load()"" when we're using the load()(Deprecated) == byId().getReference() method query doesn't executed bcoz its a "Lazy" Method
                                                        when you calling print() method then only query executed and If we use get()(deprecated) == find() query execute
                                                        1st wait for the print() method as "early" waiting */

        /**---------------------------------------------level 2 cache using Encache----------------------------------------*/
        Laptop l1 = session.find(Laptop.class, 2);
        System.out.println(l1);
//        Laptop l2 = session.find(Laptop.class, 2);
//        System.out.println(l2);

        session.close();

        Session session1 = sf.openSession();
        Laptop l2 = session1.find(Laptop.class, 2);
        System.out.println(l2);
                                                                    /** from the above we're fetching the same response twice so the hibernate
                                                                     * Executing the query for only one time and providing response twice
                                                                     * Hibernate: select l1_0.lid,l1_0.brand,l1_0.model,l1_0.ram from Laptop l1_0 where l1_0.lid=?
                                                                     * Laptop{lid=2, brand='Dell', model='XPS', ram=32}
                                                                     * Laptop{lid=2, brand='Dell', model='XPS', ram=32}
                                                                     * -----------but when we opened new Session1 want fetch the same data from
                                                                     * session cache it won't be happened as can observe above we need to change hibernate configs in the POM.xml
                                                                     * After Adding the hibernate-Jcahce, Ehcache, and jaxb-runtime in dependency managment we got the all dependencies
                                                                     * and by @Cacheble annotation in Laptop.class as we're using Laptop class so that we can use the Level 2 cache or
                                                                     * Ehcache as we can share cached data between two different sessions
                                                                     * Hibernate: select l1_0.lid,l1_0.brand,l1_0.model,l1_0.ram from Laptop l1_0 where l1_0.lid=?
                                                                     * Laptop{lid=2, brand='Dell', model='XPS', ram=32}
                                                                     * Laptop{lid=2, brand='Dell', model='XPS', ram=32}*/

        session1.close();


//        Session session1 = sf.openSession();
//
//        Alien a5 = session1.find(Alien.class,101);
////        System.out.println(a5);
//        session1.close();
        sf.close();

//        System.out.println(a1);


    }
}