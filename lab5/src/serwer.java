import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class serwer extends UnicastRemoteObject implements zad1, zad2 {
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

    @Override
    public String szyfr(String slowo) throws RemoteException{
        char[] ch = slowo.toCharArray();
        char[] samogloski = "aąeęioóuy".toCharArray();
        char s1='a';
        char s2='a';
        int pozycja=-1;
        String zaszyfrowane="";
        for( int i=0; i<slowo.length(); i++){
            if("bcćdfghjklłmnńpqrsśtwxzźżBCĆDFGHJKLŁMNŃPQRSŚTWXZŹŻ".indexOf(slowo.charAt(i))!=-1){
                if (s1=='a'){
                   pozycja=i;
                   s1=slowo.charAt(i);
                   s2=slowo.charAt(i);
                   zaszyfrowane += s1;
                }
                else{
                    s1=s2;
                    s2=slowo.charAt(i);
                    zaszyfrowane += s1;
                }


            }
            else{
                zaszyfrowane += slowo.charAt(i);
            }
            if(i==slowo.length()-1){
                zaszyfrowane =zaszyfrowane.substring(0, pozycja) + s2
                        + zaszyfrowane.substring(pozycja + 1);
            }
        }

        return zaszyfrowane;
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