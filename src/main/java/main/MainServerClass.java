package main;

import collection.CollectionManager;
import commands.CountLessCommand;
import server.Connection;
import collection.Receiver;
import utils.CSVFileReader;
import utils.ProgramStarter;

public class MainServerClass {

    public static void main(String[] args) {

        String[] test = new String[1];
        test[0] = "test";

        //ProgramStarter programStarter = new ProgramStarter("localhost",1707, args);
        ProgramStarter programStarter = new ProgramStarter("localhost",1707, test);
        programStarter.startProgram();
    }

}