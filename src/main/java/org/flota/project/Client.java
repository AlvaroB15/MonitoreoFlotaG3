package org.flota.project;

/**
 * Hello world!
 *
 */
public class Client 
{
    
    public static void main( String[] args )
    {
        // Se llamo de forma statica el metodo message que esta en el singleton de CLiente
        
        // SingletonCliente singletonCliente = new SingletonCliente();

        SingletonCliente.getInstance().message("Iniciando cliente");;

        // RegistroLog.getInstance().infoLog("as");

    }
}
