package main;

/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;*/
import utils.ProgramStarter;

public class MainServerClass {

    public static void main(String[] args) {
        String[] str = new String[1];
        str[0] = "test";

        ProgramStarter programStarter = new ProgramStarter("localhost",1707, str);
        programStarter.startProgram();
    }

}