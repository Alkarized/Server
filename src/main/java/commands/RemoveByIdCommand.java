package commands;

import ru.itmo.collection.Receiver;
import ru.itmo.utils.Messages;

import java.util.Scanner;

/**
 * Класс команды remove_by_id
 */
public class RemoveByIdCommand extends Command {

    public RemoveByIdCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("remove_by_id id : удалить элемент из коллекции по его id");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 2){
            try {
                Long id = Long.valueOf(args[1]);
                if(receiver.removeById(id)){
                    Messages.normalMessageOutput("Элемент с таким id - " + id + ", был успешно удален");
                } else {
                    Messages.normalMessageOutput("Элемента с таким id - " + id + ", либо не существует, либо в коллекции нет элементов");
                }
            } catch (Exception e){
                Messages.normalMessageOutput("Неправильно введен id, давайте по новой");
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
