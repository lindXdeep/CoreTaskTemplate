package jm.task.core.jdbc;

import java.sql.SQLException;

import java.util.logging.Logger;

import jm.task.core.jdbc.util.Util;

public class Main {

    public static Logger log = Util.getLogger("./log/log.config");

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        log.info("Load Entry point to Program");
       
        try {
            String msg = Util.connect();
            log.info(msg);

        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
