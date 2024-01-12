import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class serwer extends UnicastRemoteObject implements zad1 {
    public serwer() throws RemoteException {
        super();
    }
    @Override
    public SortedMap<Double, String> valueSort(TreeMap<Double, String> tmap) throws RemoteException
    {
        SortedMap<Double, String> sorted = new TreeMap<Double, String>(new Comparator<Double>() {
            @Override
            public int compare(Double km1, Double km2) {
                int comp = km1.compareTo(km2);

                if (comp == 0)
                    return 0;

                else
                    return comp;
            }
        })
        ;

        sorted.putAll(tmap);

        return tmap;
    }



    public static void main(String args[]) throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(4444);
            reg.rebind("hi serwer", new serwer());
            System.out.println("serwer is ready");

        } catch (RemoteException e) {
            System.out.println("Exception" + e);
        }
    }
}