package tom2.ch04;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

    public static void main(String[] args) throws UnknownHostException {

        String[] addr = {"www.google.com","www.yandex.ru"};
        if(addr.length > 0) {
            String host = addr[1];
            InetAddress[] addresses = InetAddress.getAllByName(host);

            for ( InetAddress a:addresses) {
                System.out.println(a);

            }
        }else {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address);
        }
    }
}
