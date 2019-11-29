package tom1.ch_09;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListTest {
    public static void main(String[] args) {
        var a = new LinkedList<String>();

        a.add("Amy");

        a.add("Carl");
        a.add("Erica");

        var b = new LinkedList<String>();

        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext()){
            if(aIter.hasNext()){
                aIter.next();
            }
            aIter.add((bIter.next()));
        }

        System.out.println(a);

        bIter = b.iterator();

        while (bIter.hasNext()){
            bIter.next();
            if (bIter.hasNext()){
                bIter.next();
                bIter.remove();
            }
        }

        a.removeAll(b);
        System.out.println(b);
        System.out.println(a);
    }
}
