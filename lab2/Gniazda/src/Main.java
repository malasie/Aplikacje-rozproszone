//Zad 1. Napisz program, wykorzystujący obsługę gniazd, który łączy się z portalami;
// www.wp.pl, www.ug.edu.pl, www.onet.pl, www.interia.pl, www.helion.pl na porcie 80 i
// wyświetla adres ip strony internetowej, numer portu i portu lokalnego. Kolejność losowa


import java.io.IOException;
import java.net.*;
import java.util.*;

public class Main
{
    public static void main(String args[]) {
        Socket socket = null;


        ArrayList<String> port = new ArrayList<String>();
        port.add("www.wp.pl");
        port.add("www.ug.edu.pl");
        port.add("www.onet.pl");
        port.add("www.interia.pl");
        port.add("www.helion.pl");

        Collections.shuffle(port);

        for (String host : port) {
            try {
                socket = new Socket(host, 80);
            } catch (UnknownHostException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
            if (socket != null) {
                System.out.println(socket);
            }
        }

// Napisz program, który wyświetli (string) wszystkie adresy IP przypisane do urządzenia sieciowego o nazwie przekazanej
// w postaci argumentu w wierszu poleceń.
// Adresy mają być posortowane według pierwszego członu adresu IP, a w przypadku takich samych adresów według kolejnych członów.

        Scanner myObj = new Scanner(System.in);
        String hostName = myObj.nextLine();

        InetAddress[] inetAddress = null;
        try {
            inetAddress = InetAddress.getAllByName(hostName);

            for (InetAddress address : inetAddress) {
                System.out.println(address);
            }
        } catch (UnknownHostException e) {
            System.out.println(
                    "Nie można uzyskać adresu IP dla hosta " + hostName);
            System.exit(0);
        }
    }
}