package commands;

import ru.itmo.collection.Receiver;
import ru.itmo.utils.Messages;

import java.util.Scanner;

/**
 * Класс команды execute_script
 */
public class ExecuteScriptCommand extends Command {

    public ExecuteScriptCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2){
            Messages.normalMessageOutput("Неправильно введены аргументы, попробуйте еще раз!");
        } else {
            receiver.executeScript(args[1]);
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}
