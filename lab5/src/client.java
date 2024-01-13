import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class client
{
    public static void main( String args[]) throws RemoteException
    {
        client c= new client();
        c.connectRemote();
    }

    private void connectRemote() {
        try
        {


            TreeMap<Double, String> pary = new TreeMap<Double, String>();
            /*Adding elements to TreeMap*/
            pary.put(21.8, "Gdynia");
            pary.put(299.2, "Warszawa");
            pary.put(14.5, "Sopot");
            pary.put(497.0, "Krakow");
            pary.put(487.6, "Berlin");


            Registry reg=LocateRegistry.getRegistry("localhost", 4444);


            zad1 z= (zad1) reg.lookup("hi serwer");
            zad2 z2= (zad2) reg.lookup("hi serwer");

            SortedMap<Double, String> pary_sort=z.valueSort(pary);
            System.out.println("-------- ZAD 1 --------");
            Set set = pary.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry)iterator.next();
                System.out.print("Liczba kilometrów: "+ mentry.getKey() + " Miasto: ");
                System.out.println(mentry.getValue());
            }

            System.out.println("\n-------- ZAD 2 --------");

            String slowo="lokomotywa";
            System.out.println("Słowo: "+slowo+" Szyfr: "+ z2.szyfr(slowo));
            slowo="film";
            System.out.println("Słowo: "+slowo+" Szyfr: "+ z2.szyfr(slowo));
        }
        catch(NotBoundException|RemoteException e)
        {
            System.out.println(e);
        }

    }
}