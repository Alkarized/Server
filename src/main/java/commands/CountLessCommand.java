package commands;

import ru.itmo.collection.Receiver;
import ru.itmo.utils.Messages;

import java.util.Scanner;

/**
 * Класс команды count_less_than_number_of_rooms
 */
public class CountLessCommand extends Command {

    public CountLessCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("count_less_than_number_of_rooms numberOfRooms : вывести количество элементов, значение поля numberOfRooms которых меньше заданного");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 2){
            try {
                int number = Integer.parseInt(args[1]);
                int count = receiver.countLessNumberOfRooms(number);
                if(count == -1){
                    Messages.normalMessageOutput("В коллекции отсутсвуют эллементы, сначала добавиь их, а потом пытайся что-то сделать");
                } else {
                    Messages.normalMessageOutput("Всего элементов коллекции, в которых значения поля numberOfRooms ниже заданного - " + count);
                }
            } catch (Exception e){
                Messages.normalMessageOutput("Неправильный ввод числа, повторите попытку!");
            }
        } else {
            Messages.normalMessageOutput("Что-то не так с аргументами, давай по новой!");
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}
