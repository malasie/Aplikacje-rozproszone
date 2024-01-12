import java.util.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface zad1 extends Remote {


    public SortedMap<Double, String> valueSort(TreeMap<Double, String> tmap) throws RemoteException;

}

