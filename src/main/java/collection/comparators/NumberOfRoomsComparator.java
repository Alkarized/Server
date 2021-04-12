package collection.comparators;

import fields.Flat;

import java.util.Comparator;

/**
 * Класс наследуемый от Comparator Flat - сортировка по полю numberOfRooms
 */
public class NumberOfRoomsComparator implements Comparator<Flat> {
    @Override
    public int compare(Flat o1, Flat o2) {
        return o2.getNumberOfRooms().compareTo(o1.getNumberOfRooms());
    }
}
