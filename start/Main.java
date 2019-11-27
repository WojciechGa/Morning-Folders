package start;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Main{

    public static void main(String[] args) {

        ArrayList<String> komunikaty = new ArrayList<>();

        //create arrays with file and directory names
        ArrayList<String> folders = new ArrayList<>();
        ArrayList<String> createdFiles = new ArrayList<>();
        Map<String, String> copyFiles = new HashMap<>();

        ReadPath reader;

        if(args.length >= 1){
            reader = new ReadPath(args[0]);
        }
        else{
            reader = new ReadPath();
        }

        folders.addAll(reader.readSinglePath(System.getProperty("user.dir") +
                "\\workingFiles\\folders.csv"));

        createdFiles.addAll(reader.readSinglePath(System.getProperty("user.dir") +

                "\\workingFiles\\files.csv"));
        copyFiles.putAll(reader.readTwoPaths(System.getProperty("user.dir") +
                "\\workingFiles\\copyFiles.csv"));

        if(Days.isMonday(Calendar.getInstance())) {
            folders.addAll(reader.readSinglePath(System.getProperty("user.dir") +
                    "\\workingFiles\\mondayFolders.csv"));
            createdFiles.addAll(reader.readSinglePath(System.getProperty("user.dir") +
                    "\\workingFiles\\mondayFiles.csv"));

            copyFiles.putAll(reader.readTwoPaths(System.getProperty("user.dir") +
                    "\\workingFiles\\mondayCopyFiles.csv"));

        }

        //rozne sposoby na pobranie sciezki do pliku
/*        System.out.println(new File("").getAbsolutePath());
        System.out.println(System.getProperty("user.dir"));*/

//        folders.forEach(x -> System.out.println(x));
        folders.forEach(x -> Copy.createDir(x, komunikaty));

//        createdFiles.forEach(x -> System.out.println(x));
        createdFiles.forEach(x -> Copy.createFile(x, komunikaty));

//        copyFiles.forEach((x,y)-> System.out.println(x+ " | " + y));
        copyFiles.forEach((x,y)-> Copy.copyFile(x, y,komunikaty));

        new MyFrame(komunikaty);
    }
}
