package utils.csv;

import fields.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс-парсер для CSV
 */
public class CSVParser {
    /**
     * Парсинг строки в Массив полей
     * @param line строка
     * @return выходной массив полученных полей
     */
    public String[] parseLineToArray(String line) {
        List<String> list = new ArrayList<>();
        boolean isHereNotUsedQuote = false;
        StringBuilder field = new StringBuilder("");
        for (int i = 0; i < line.length(); i++) {
            char x = line.charAt(i);
            if (x == '"') {
                isHereNotUsedQuote = !isHereNotUsedQuote;
            } else if (x == ',') {
                if (!isHereNotUsedQuote) {
                    list.add(String.valueOf(field));
                    field = new StringBuilder("");
                } else {
                    field.append(x);
                }
            } else {
                field.append(x);
            }
        }
        list.add(String.valueOf(field));
        return !isHereNotUsedQuote ? list.toArray(new String[0]) : null;
    }

    /**
     * Создает CSV строку из Flat объкта
     * @param flat объект, который будет преобразован в CSV строку
     * @return CSV line
     */
    public String makeCSVLineFromFlat(Flat flat) {
        return flat.getId() + "," +
                flat.getName() + "," +
                flat.getCoordinates().getX() + "," +
                flat.getCoordinates().getY() + "," +
                flat.getCreationDate() + "," +
                flat.getArea() + "," +
                flat.getNumberOfRooms() + "," +
                flat.getFurnish() + "," +
                flat.getView() + "," +
                flat.getTransport() + "," +
                flat.getHouse().getName() + "," +
                flat.getHouse().getYear() + "," +
                flat.getHouse().getNumberOfFlatsOnFloor();
    }

    /**
     * Парсинг массива полей в новый Flat
     *
     * @param args массив полей
     * @return новый Flat
     */
    public Flat parseArrayToFlat(String[] args) {
        if (args.length == 13) {
            Flat flat = new Flat();
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals(""))
                    args[i] = null;
            }
            try {
                Coordinates coordinates = new Coordinates();
                House house = new House();
                if (flat.setId(Long.valueOf(Objects.requireNonNull(args[0]))) && flat.setName(args[1]) &&
                        coordinates.setX(Integer.valueOf(args[2])) && coordinates.setY(Float.valueOf(args[3])) &&
                        flat.setCreationDate(new SimpleDateFormat("HH:mm:ss.SSS dd-MM-yyyy").parse(args[4])) &&
                        flat.setArea(Long.valueOf(args[5])) && flat.setNumberOfRooms(Integer.parseInt(args[6])) &&
                        flat.setFurnish(Furnish.valueOf(args[7])) && flat.setView(View.valueOf(args[8])) &&
                        flat.setTransport(Transport.valueOf(args[9])) && house.setName(args[10]) &&
                        house.setYear(Long.valueOf(args[11])) && house.setNumberOfFlatsOnFloor(Long.valueOf(args[12]))) {
                    flat.setCoordinates(coordinates);
                    flat.setHouse(house);
                    return flat;
                } else {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
