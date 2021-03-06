package hibernateConfig;

import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    private HibernateUtil() {
    }

    public static SessionFactory getInstance() {
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost/CompanyManagement");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put("hibernate.connection.CharSet", "utf-8");
                settings.put("hibernate.connection.characterEncoding", "utf-8");
                settings.put(Environment.HBM2DDL_AUTO, "create");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Email.class);
                configuration.addAnnotatedClass(Attachment.class);
                configuration.addAnnotatedClass(Revision.class);
                configuration.addAnnotatedClass(Category.class);
                configuration.addAnnotatedClass(CategoryElement.class);
                configuration.addAnnotatedClass(Leave.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
