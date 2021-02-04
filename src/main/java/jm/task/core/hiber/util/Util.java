package jm.task.core.hiber.util;
public class Util {

    private Util() {
    }

    static{
        Logger.readConfiguration();
    }

    public static java.util.logging.Logger getLogger(){
        return java.util.logging.Logger.getLogger(Util.class.getSuperclass().getName());
    }
}