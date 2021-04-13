package fields;

import java.io.Serializable;

/**
 * Класс для координат
 */
public class Coordinates implements Serializable {
    private static final long serialVersionUID = 12245;

    private Integer x; //Поле не может быть null
    private Float y; //Максимальное значение поля: 687, Поле не может быть null

    public Integer getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public boolean setX(Integer x) {
        if (x == null) {
            return false;
        } else {
            this.x = x;
            return true;
        }
    }

    public boolean setY(Float y) {
        if (y == null || y > 687) {
            return false;
        } else {
            this.y = y;
            return true;
        }
    }

}
