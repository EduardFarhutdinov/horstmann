package tom1.ch_09;

import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

public class TreeSetTest {

    public static void main(String[] args) {
        var parts = new TreeSet<Item>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4562));
        parts.add(new Item("Modem", 9912));
        System.out.println(parts);

        var sortByDescription = new TreeSet<Item>(Comparator.comparing(Item::getDescription));

        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);
    }


    static class Item implements Comparable<Item>{
        private String description;
        private int partNumber;

        public Item(String description, int partNumber) {
            this.description = description;
            this.partNumber = partNumber;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "description='" + description + '\'' +
                    ", partNumber=" + partNumber +
                    '}';
        }

        @Override
        public boolean equals(Object otherObject) {
            if(this == otherObject) return true;
            if(otherObject == null) return false;
            if(getClass() != otherObject.getClass()) return false;
            var other = (Item) otherObject;
            return Objects.equals(description,other.description) && partNumber == other.partNumber;
        }

        @Override
        public int hashCode() {
            return Objects.hash(getDescription(), partNumber);
        }

        @Override
        public int compareTo(Item item) {
            int diff = Integer.compare(partNumber,item.partNumber);
            return diff !=0 ? diff : description.compareTo(item.description);

        }
    }
}
