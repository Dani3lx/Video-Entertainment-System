
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.*;


public class DataManager implements Serializable {

    // All instances of StudentManager will share one Logger, so it's
    // static.
    // We use StudentManager.class.getName() to get the fully-qualified
    // name of this class, which happens to be "managers.StudentManager".
    // We use this name as the name of our Logger object.
    // Using the class class is called "reflection".
    private static final Logger logger = Logger.getLogger(DataManager.class.getName());
    private static final Handler handler = new ConsoleHandler();
    private static final List<User> UserList = new ArrayList<>();

    public DataManager()
    {
        logger.addHandler(handler);
    }

    /**
     * Populates the records map from the file at path filePath.
     *
     * @param filePath the path of the data file
     * @throws FileNotFoundException if filePath is not a valid path
     */
    public static void readCSV(String filePath) throws FileNotFoundException {

        // FileInputStream can be used for reading raw bytes
        Scanner scanner = new Scanner(new FileInputStream(filePath));
        String[] record;
        User user;

        while (scanner.hasNextLine()) {
            record = scanner.nextLine().split("\n"); //what char is used for the nextline, we can set maybe
            user = new NonAdminUser (record[0], record[1]); //User toString method
            //todo Since user is abstract, what we can do is just add admins and then non-admins
            UserList.add(user);
        }
        scanner.close();
    }
    public static List<User> getUserList() {
        return UserList;
    }

    /**
     * Adds record to this StudentManager.
     *
     * @param record a record to be added.
     */
    public void add(User record) {
        //todo not sure if we need this

        UserList.add(record);
        // Log the addition of a student.
        logger.log(Level.INFO, "Added a new student " + record.toString());
    }

    /**
     * Writes the students to file at filePath.
     *
     * @param filename the file to write the records to
     * @throws IOException if error reading from file
     */
    public static void writeCSV(String filename) throws IOException {
        File outputFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(outputFile)){
            String sb = UserData.getAllUsers().toString(); //todo not sure if this actually works
            pw.write(sb);
        }
        assert(outputFile.exists());
    }

    public static void writeLoginHistoryCSV(String filename) throws IOException {
        File outputFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(outputFile)){
            HashMap<String, HashSet<String>> allLoginHistory = UserData.getAllLoginHistory();
            if (!(Objects.isNull(allLoginHistory))) {
                StringBuilder sb = new StringBuilder();
                for (String name: allLoginHistory.keySet()){
                    String key = name;
                    for (String value: allLoginHistory.get(name)){
                        sb.append(new String(key + ", " + value + ", "));
                    }
                }
                pw.write(sb.toString());
            }
        }
        assert(outputFile.exists());
    }

//   @Override
//    public String toString() {
//        StringBuilder result = new StringBuilder();
//        for (User r : UserData.getAllUsers()) {
//            result.append(r.toString()).append("\n");
//        }
//        return result.toString();
//    }
}
