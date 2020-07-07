package org.flota.project;

public class SingletonCliente {
    

    // Se creo esta clase para poder usar el patron Singleton
    public static SingletonCliente sCliente;

    public static synchronized SingletonCliente getInstance()  {

        if (sCliente == null)    {
                sCliente = new SingletonCliente();
        }
        return sCliente;
    }

    public void message (String mensaje)    {

        System.out.println( mensaje );
        RegistroLog.getInstance().infoLog(mensaje);

    }
}