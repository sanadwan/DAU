import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {

    /**
     * hashDAU - HashMap with strings representing the dates and a HashSet of strings for the ides
     */
   private static HashMap<String, HashSet<String>> hashDAU = new HashMap<>();
    /**
     * pattern - regex for getting the log date and the id from each line in the input
     */
   private static final Pattern pattern = Pattern.compile("([0-9]*),([0-9]{2}\\/[0-9]{2}\\/[0-9]{4}) ");

    /**
     *
     * gets as input a file name, parse each line and insert the id and the date into the hashMap
     *      and prints the result for how many loges in each date
     * @param input - the file name
     * @throws IOException
     */
    public static void readInputFile(String input) throws IOException {
        // reading the file
        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        // parsing each line in the file
        String line;
        while ((line = br.readLine()) != null){
            parseDAU(line);
        }
        //printing the result
        printDAU();
    }

    /**
     * printing the hashmap as a list of date and the number of unique logs
     */
    private static void printDAU() {
        for (String key: hashDAU.keySet()
             ) {
            System.out.println(key + " " + hashDAU.get(key).size());
        }
    }

    /**
     * check if the sting have the given regex and inset the id and date into the hashmap
     * @param str - the given line from the file
     */
    private static void parseDAU(String str){
        Matcher matcher = pattern.matcher(str);
        String date, id;
        if(matcher.find()){
            // the id of the log
            id = matcher.group(1);
            // the date of the log
            date = matcher.group(2);
            // inserting the date and id
            insetToHash(date, id);
        }
    }

    /**
     * checks if the hashMap contain the date and add the id to the value, otherwise adds new key
     * @param date - the date of the log
     * @param id - the id of the log
     */
    private static void insetToHash(String date, String id) {
        if (hashDAU.containsKey(date)){
            hashDAU.get(date).add(id);
        }
        else {
            // initialize new hashset in case the hashmap don't contain the date as a key
            HashSet<String> addedDate = new HashSet<>();
            addedDate.add(id);
            hashDAU.put(date,addedDate);
        }
    }


}
