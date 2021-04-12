package fields;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс аппартаментов
 */
public class Flat implements Comparable<Flat>{
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area; //Значение поля должно быть больше 0
    private Integer numberOfRooms; //Значение поля должно быть больше 0
    private Furnish furnish; //Поле не может быть null
    private View view; //Поле не может быть null
    private Transport transport; //Поле не может быть null
    private House house; //Поле не может быть nul

    public Flat() {
        this.creationDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCreationDate() {
        return new SimpleDateFormat("HH:mm:ss.SSS dd-MM-yyyy").format(creationDate);
    }

    public Long getArea() {
        return area;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public View getView() {
        return view;
    }

    public Transport getTransport() {
        return transport;
    }

    public House getHouse() {
        return house;
    }

    public boolean setName(String name) {
        if (name == null || name.equals("")) {
            return false;
        } else {
            this.name = name;
            return true;
        }
    }

    public boolean setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return true;
    }

    public boolean setArea(Long area) {
        if (area > 0) {
            this.area = area;
            return true;
        } else {
            return false;
        }
    }

    public boolean setNumberOfRooms(Integer numberOfRooms) {
        if (numberOfRooms > 0) {
            this.numberOfRooms = numberOfRooms;
            return true;
        } else {
            return false;
        }
    }

    public boolean setFurnish(Furnish furnish) {
        if (furnish == null) {
            return false;
        } else {
            this.furnish = furnish;
            return true;
        }
    }

    public boolean setView(View view) {
        if (view == null) {
            return false;
        } else {
            this.view = view;
            return true;
        }
    }

    public boolean setTransport(Transport transport) {
        if (transport == null) {
            return false;
        } else {
            this.transport = transport;
            return true;
        }
    }

    public boolean setHouse(House house) {
        this.house = house;
        return true;
    }

    public boolean setId(Long id) {
        /*if (id > 0) {
            this.id = IdManager.findUniq(id);
            return true;
        } else {
            return false;
        }*/
        this.id = id;
        return true;
    }

    public boolean setCreationDate(Date creationDate) {
        if (creationDate == null) {
            return false;
        } else {
            this.creationDate = creationDate;
            return true;
        }
    }

    /**
     * Выводит информацию в строков виде об объекте
     */
    public String printInfoAboutElement() {
        return "id - " + id + "\n" +
                "Имя - " + name + "\n" +
                "Координаты x и y - " + coordinates.getX() + ", " + coordinates.getY() + "\n" +
                "Дата создания - " + getCreationDate() + "\n" +
                "Площадь - " + area + "\n" +
                "Кол-во комнат - " + numberOfRooms + "\n" +
                "Отделка - " + furnish + "\n" +
                "Вид - " + view + "\n" +
                "Транспорт - " + transport + "\n" +
                "Имя дома - " + house.getName() + "\n" +
                "Год дома - " + house.getYear() + "\n" +
                "Кол-во квартир на этаже - " + house.getNumberOfFlatsOnFloor();
    }

    /**
     * Метод сравнения 2 объектов класса Flat
     */
    @Override
    public int compareTo(Flat o) {
        return this.getName().compareTo(o.getName());
    }
}



