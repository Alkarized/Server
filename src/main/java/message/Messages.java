package message;

/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;*/

/**
 * Класс для вывода информации в командную строку
 */
public class Messages {
    //private static final Logger logger = LogManager.getLogger();
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Выводит на экран сообщение, которое говорит о каких-то действиях программы
     * @param message сообщение которые выведется на экран
     */
    public static void normalMessageOutput(String message, MessageColor messageColor) {
        System.out.println(messageColor.getColorType() + message + ANSI_RESET);
    }

    /*public static Logger getLogger(){
        return logger;
    }*/
}