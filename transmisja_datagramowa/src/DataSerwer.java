import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class DataSerwer {
  public static int sPort=777;
  public static int kPort=888;
  public static int bufferSize=1024;
  public static DatagramSocket gniazdo;
  public static byte buffer[]=new byte[bufferSize];
  public static void main(String[] args) throws Exception{
      gniazdo = new DatagramSocket(sPort);
      Serw();
  }

    static String Polibiusz(char s)
    {
        s= Character.toLowerCase(s);
        int row, col;

        row = ((s - 'a') / 5) + 1;

        col = ((s - 'a') % 5) + 1;

        if (s == 'k')
        {
            row = row - 1;
            col = 5 - col + 1;
        }

        else if (s >= 'j')
        {
            if (col == 1)
            {
                col = 6;
                row = row - 1;
            }
            col = col - 1;
        }

        return row+""+col;
    }

    public static void Serw() throws Exception{
      System.out.println("SERWER");
      System.out.println("Host:"+ InetAddress.getLocalHost());
      System.out.println("WIADOMOŚĆ");
      String s="";
      int pos=0;
      while (true){
          int c=System.in.read();
          switch (c){
              case -1:
                  System.out.println("SERWER STOP");
                  return;
              case '\r':
                  break;
              case '\n':
                  gniazdo.send(new DatagramPacket(buffer, pos, InetAddress.getLocalHost(), kPort));
                  pos=0;
                  break;

              default:
                  if (c!=' ') {
                      s = Polibiusz((char) c);
                      buffer[pos++] = (byte) s.charAt(0);
                      buffer[pos++] = (byte) s.charAt(1);
                      buffer[pos++] = (byte) ' ';
                  }

          }
      }
  }
}