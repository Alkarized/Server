package fields;

import java.io.Serializable;

/**
 * Класс Дома
 */
public class House implements Serializable {
    private static final long serialVersionUID = 8634;


    private String name; //Поле может быть null
    private Long year; //Значение поля должно быть больше 0
    private Long numberOfFlatsOnFloor; //Значение поля должно быть больше 0

    public String getName() {
        return name;
    }

    public Long getYear() {
        return year;
    }

    public Long getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public boolean setYear(Long year) {
        if (year > 0) {
            this.year = year;
            return true;
        } else {
            return false;
        }
    }

    public boolean setNumberOfFlatsOnFloor(Long numberOfFlatsOnFloor) {
        if (numberOfFlatsOnFloor > 0) {
            this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
            return true;
        } else {
            return false;
        }
    }

}
