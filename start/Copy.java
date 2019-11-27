package start;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public interface Copy {

    //makes a file copy
    public static String copyFile(String fileName, String copyName){
        Path file =  Paths.get(fileName);
        Path copy = Paths.get(copyName);
        String userMessage = "";
        try {
            if (Files.exists(file)) {
                if (Files.exists(copy)) {
                    System.out.println(userMessage ="Plik kopii już istnieje - " + copy.subpath(copy.getNameCount()-2,copy.getNameCount()));
                } else {
                    Files.copy(file, copy);
                    System.out.println(userMessage = "Plik skopiowany - " + copy.subpath(copy.getNameCount()-2,copy.getNameCount()));
                }
            } else {
                System.out.println(userMessage = "Plik " + file.subpath(file.getNameCount()-2,file.getNameCount()) + " nie istniej");
            }
        }catch (IOException e){
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        return userMessage;
    }

    static void copyFile(String fileName, String copyName, ArrayList<String> list){
        list.add(copyFile(fileName, copyName));
    }

    //creates file
    public static String createFile(String fileName){
        Path file = Paths.get(fileName);
        String userMessage = "";
        try{
            if(!Files.exists(file)){
                Files.createFile(file);
                System.out.println(userMessage ="Plik stworzony - " + file.subpath(file.getNameCount()-2,file.getNameCount()));
            }
            else{
                System.out.println(userMessage ="Plik juz isntnieje - " + file.subpath(file.getNameCount()-2,file.getNameCount()));
            }
        }catch (IOException e){
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        return userMessage;
    }

    static void createFile(String fileName, ArrayList<String> list){
        list.add(createFile(fileName));
    }

    static String createDir(String dirName){
        Path dir = Paths.get(dirName);
        String userMessage = "";
        try{
            Files.createDirectory(dir);
            System.out.println(userMessage ="Folder stworzony - " + dir.subpath(dir.getNameCount()-2,dir.getNameCount()));
        }catch (FileAlreadyExistsException e){
            System.out.println(userMessage ="Folder istnieje - " + dir.subpath(dir.getNameCount()-2,dir.getNameCount()));
        }catch (IOException e){
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        return userMessage;
    }

    static void createDir(String dirName, ArrayList<String> list){
        list.add(createDir(dirName));
    }


}
