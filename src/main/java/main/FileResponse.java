package main;

import message.MessageColor;
import message.Messages;

import java.io.Serializable;

public enum FileResponse implements Serializable {
    AllowAccessAlreadyCreated {
        @Override
        public void getMessageOfResponse() {
            Messages.normalMessageOutput("Ну это, файлик есть и отлично!", MessageColor.ANSI_BLUE);
        }
    },
    AllowAccessNotCreated {
        @Override
        public void getMessageOfResponse() {
            Messages.normalMessageOutput("Файлик еще не создан, появится как только так сразу, а не, уже появился", MessageColor.ANSI_YELLOW);
        }
    },
    DenyAccessNoWritable {
        @Override
        public void getMessageOfResponse() {
            Messages.normalMessageOutput("Уууупс, а в файлик-то ничего записать нельзя, а смысл в чем? Зайкрой и открой нормально", MessageColor.ANSI_RED);
            System.exit(-1);
        }
    },
    DenyAccessNoReadable {
        @Override
        public void getMessageOfResponse() {
            Messages.normalMessageOutput("Ну что-то явно пошло не так, дай права на чтение пощупать.", MessageColor.ANSI_RED);
            System.exit(-1);
        }
    },
    DenyAccessNoWritableAndNoReadable {
        @Override
        public void getMessageOfResponse() {
            Messages.normalMessageOutput("Файл нельзя считать и что-то в него записать, давай исправляй, сударь!", MessageColor.ANSI_RED);
            System.exit(-1);
        }
    },

    NothingAccepted{
        @Override
        public void getMessageOfResponse() {
            System.exit(-1);
        }
    };

    private static final long serialVersionUID = 200;
    public abstract void getMessageOfResponse();

}