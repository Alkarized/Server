package commands;

import ru.itmo.collection.Receiver;
import ru.itmo.utils.Messages;

import java.util.Scanner;

/**
 * Класс команды min_by_coordinates
 */
public class MinByCoordinatesCommand extends Command {

    public MinByCoordinatesCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("min_by_coordinates : вывести любой объект из коллекции, значение поля coordinates которого является минимальным");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 1){
            if(!receiver.printElementWithMinCoordinates()){
                Messages.normalMessageOutput("В коллекции нет эллементов, нечего выводить!");
            }
        } else {
            Messages.normalMessageOutput("Неправильные аргументы комманды, попробуйте еще раз");
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}
