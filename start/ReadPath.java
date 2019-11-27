package start;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Class used to read file paths form sorce files and pass them to array
 */
public class ReadPath {
    String today, prevWorkingDay, twoWorkingDays, x;
    Calendar now;
    Calendar cPrevDay;
    TreeMap<String, String> change= new TreeMap<>();

    {
        now = Calendar.getInstance();
        cPrevDay = Calendar.getInstance();
        today = String.format("%1$tY%<tm%<te", now);
    }

    private void doChangeMap(){
        change.put("today", today);
        change.put("previouseWorkingDay", prevWorkingDay);
        change.put("twoWorkingDays", twoWorkingDays);
        change.put(("z//"+ today), String.format("%s-%s-%s", today.substring(0,4), today.substring(4,6), today.substring(6,8)));
        change.put(("z//"+ prevWorkingDay), String.format("%s-%s-%s", prevWorkingDay.substring(0,4), prevWorkingDay.substring(4,6), prevWorkingDay.substring(6,8)));
        change.put(("z//"+ twoWorkingDays), String.format("%s-%s-%s", twoWorkingDays.substring(0,4), twoWorkingDays.substring(4,6), twoWorkingDays.substring(6,8)));
    }

    public ReadPath(){
        prevWorkingDay = String.format("%1$tY%<tm%<te",Days.previousWorkingDay(cPrevDay));
        twoWorkingDays = String.format("%1$tY%<tm%<te",Days.previousWorkingDay(cPrevDay));
        doChangeMap();
    }

    public ReadPath(String s){
        prevWorkingDay = s;
        cPrevDay.set(Integer.parseInt(s.substring(0,4)),Integer.parseInt(s.substring(4,6)) -1,Integer.parseInt(s.substring(6,8)));
        twoWorkingDays = String.format("%1$tY-%<tm-%<te", Days.previousWorkingDay(Days.previousWorkingDay(cPrevDay)));
        doChangeMap();
    }


    //reads sigle path for creating files and directiries
    public ArrayList<String> readSinglePath(String pathName){
        Path sorceFile = Paths.get(pathName);
        ArrayList<String> list = new ArrayList<>();
        try {
            list.addAll(Files.readAllLines(sorceFile));
            for (String s : list) {
                int indexList = list.indexOf(s);
                for (Map.Entry<String, String> entry : change.entrySet()) {
                    String z = entry.getKey();
                    String y = entry.getValue();
                    s = s.replace(z, y);
                }

                list.set(indexList, s);
            }
        }catch (IOException e){
            System.out.println(e.getClass().getSimpleName() + e.getMessage());
        }
        return list;
    }

    //reads two paths to copy files
    public Map<String, String> readTwoPaths(String pathName){
        Path sorceFile = Paths.get(pathName);
        ArrayList<String> listOf2Files = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        try{
            List<String> fromFile = Files.readAllLines(sorceFile);
            listOf2Files.addAll(fromFile);
            for (String x : listOf2Files) {
                int indexList = listOf2Files.indexOf(x);
                for (Map.Entry<String, String> entry : change.entrySet()) {
                    String z = entry.getKey();
                    String y = entry.getValue();
                    x = x.replace(z, y);
                    listOf2Files.set(indexList, x);
                }
                String[] seperate = x.split(";");
                map.put(seperate[0], seperate[1]);
            }
        }catch(IOException e){
            System.out.println(e.getClass().getSimpleName() + e.getMessage());
        }
        return map;
    }

    public Calendar getNow() {
        return now;
    }
}
