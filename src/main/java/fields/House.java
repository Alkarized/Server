package fields;

/**
 * Класс Дома
 */
public class House {
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
