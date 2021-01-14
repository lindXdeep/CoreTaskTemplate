package jm.task.core.jdbc.util.properties;

import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
public class HibernateProperty {
    
    private static Properties properties = null;
    private static Configuration cfg = null;

    static {
        properties = new Properties();
        properties.setProperty(Environment.DRIVER, JDBCProperty.getMySqlDriver().getName());
        properties.setProperty(Environment.URL, JDBCProperty.getURL());
        properties.setProperty(Environment.USER, JDBCProperty.getUser());
        properties.setProperty(Environment.PASS, JDBCProperty.getPass());
        properties.setProperty(Environment.POOL_SIZE, "10");
        properties.setProperty(Environment.AUTOCOMMIT, "false");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.setProperty(Environment.CACHE_PROVIDER_CONFIG, "org.hibernate.cache.NoCacheProvider");
        properties.setProperty(Environment.SHOW_SQL, "true");

        cfg = new Configuration();
        cfg.setProperties(properties);
    }

    public static Configuration getConfiguration(){
        return cfg;
    }
}
