package commands;

import ru.itmo.collection.Receiver;
import ru.itmo.utils.Messages;

import java.util.Scanner;

/**
 * Класс команды update
 */
public class UpdateByIdCommand extends Command {

    public UpdateByIdCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    public void execute(String[] args) {
        this.execute(args, new Scanner(System.in));
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        if(args.length == 2){
            Long id = null;
            try {
                id = Long.valueOf(args[1]);
            } catch (Exception e){
                Messages.normalMessageOutput("Неправильный ввод значения id");
            }
            if(receiver.updateElementById(id, scanner)){
                Messages.normalMessageOutput("Элемент с id - " + id + ", успешно обновлен!");
            } else {
                Messages.normalMessageOutput("Произошла ошибка, попробуйте еще раз");
            }
        } else {
            Messages.normalMessageOutput("Неправильный ввод аргументов, попробуйте еще раз!");
        }
    }
}
