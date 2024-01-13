import java.util.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface zad2 extends Remote {

public String szyfr(String slowo) throws RemoteException;

}
