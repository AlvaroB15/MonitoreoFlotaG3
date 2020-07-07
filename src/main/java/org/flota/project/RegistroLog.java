package org.flota.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RegistroLog {

    /* Modelo de Singleton con RegistroLog   */

    public static RegistroLog registro;
    // public static final  Logger LOGGER = LogManager.getLogger(RegistroLog.class.getName());
    public static final  Logger LOGGER = LogManager.getLogger(RegistroLog.class.getName());

    public static synchronized RegistroLog getInstance()  {

        if (registro == null)    {
                registro = new RegistroLog();
                // registro = new RegistroLog(mensaje);
                
        }
        return registro;
    }

    public void log (String mensaje)    {

        System.out.println( mensaje );
    }

    public void infoLog(String msj){
        LOGGER.info(msj);
    }

    public void errorLog(String msj){
        LOGGER.error(msj);
    }

    public void debugLog(String msj){
        LOGGER.debug(msj);
    }

}