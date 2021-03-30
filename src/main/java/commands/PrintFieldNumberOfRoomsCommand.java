package commands;

import ru.itmo.collection.Receiver;
import ru.itmo.utils.Messages;

import java.util.Scanner;

/**
 * Класс команды print_field_descending_number_of_rooms
 */
public class PrintFieldNumberOfRoomsCommand extends Command {

    public PrintFieldNumberOfRoomsCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("print_field_descending_number_of_rooms : вывести значения поля numberOfRooms всех элементов в порядке убывания");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 1){
            if(!receiver.printFieldDescendingNumberOfRooms()){
                Messages.normalMessageOutput("В коллекции нет эллементов, так что вывод пуст");
            }
        } else {
            Messages.normalMessageOutput("Непавильны ввод агрументов, попробуйте еще раз");
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}
