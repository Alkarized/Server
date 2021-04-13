package collection.comparators;

import fields.Flat;

import java.util.Comparator;

/**
 * Класс наследуемый от Comparator Flat - сортировка по полю coordinates
 */
public class CoordinatesComparator implements Comparator<Flat> {
    @Override
    public int compare(Flat o1, Flat o2) {
        return o1.getCoordinates().getX().compareTo(o2.getCoordinates().getX()) +
                o1.getCoordinates().getY().compareTo(o2.getCoordinates().getY());
    }
}
