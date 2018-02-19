import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* AdmissionsFileManager has static methods to process ArrayLists of ProspectiveStudents.
 * One method reads in from a text file and makes ProspectiveStudent objects. These objects
 * are parsed using another method, and saved in an ArrayList. It has a method to parse a date
 * from a String, used in the method to parse text from the file into a ProspectiveStudent object.
 * It also has methods that can be used to write an ArrayList<ProspectiveStudent> to a text file,
 * and to serialise and deserialize an ArrayList<ProspectiveStudent>.*/

public class AdmissionsFileManager {

    private static final String filename = "student_admissions_file.txt";
    private static final String serialFilename = "student_admissions_file.ser";

    /**************************************************************************/

    private AdmissionsFileManager() {}

    public static ArrayList<ProspectiveStudent> read() {
        Scanner in;
        try {
            in = new Scanner(new FileInputStream(filename), "UTF-8");
        } catch (FileNotFoundException e) {
            System.err.println("Could not find '" + filename + "'");
            return null;
        }

        ArrayList<ProspectiveStudent> list = new ArrayList<>();
        while(in.hasNextLine()) {
            String id = in.nextLine();
            if (id.startsWith("ID: ")) { //from this we can be sure that there are the required nine extra lines following this one
                ArrayList<String> details = new ArrayList<>();
                details.add(id);
                for (int i = 0; i < 9; i++) {
                    details.add(in.nextLine());
                }
                ProspectiveStudent ps = parseProspectiveStudent(details);
                if (ps != null) {
                    list.add(ps);
                }
            }
        }
        in.close();
        return list;
    }

    /**************************************************************************/

    private static Calendar parseDate(String s){
		Calendar dob = null;
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(s);
			dob = Calendar.getInstance();
			dob.setTime(date);
		}
		catch (ParseException e) {
			System.err.println("Invalid date format in " + filename);
			return null;
		}
		return dob;
	}

	/**************************************************************************/

    private static ProspectiveStudent parseProspectiveStudent(ArrayList<String> details) {
        ArrayList<String> data = new ArrayList<>();

        for (String s : details) {
            String[] split = s.split("\\:\\s+", 2);
            data.add(split[1]);
        }

        //some statements missing here
        int id = Integer.parseInt(data.get(0));
        String name = data.get(1);
        Calendar dob = parseDate(data.get(2));
        Gender gender = Gender.valueOf(data.get(3));
        String phone = data.get(4);
        String email = data.get(5);
        long course = Long.parseLong(data.get(6));
        QualificationsType qualificationsType = QualificationsType.valueOf(data.get(7));
        FeeType feeType = FeeType.valueOf(data.get(8));
        OfferStatus offerStatus = OfferStatus.valueOf(data.get(9));

        return new ProspectiveStudent(id, name, dob, gender, phone, email, course, qualificationsType, feeType, offerStatus);
    }

    /**************************************************************************/

    /* takes an ArrayList<ProspectiveStudent> and saves it into a text file with a name given by
     * the user. The objects in the ArrayList are written into the text file using the
     * ProspectiveStudent class's toString() method. Each element from the ArrayList is
     * separated in the text file with a blank line. The method uses a platform independent
     * way of getting a new line, and handles any potential exceptions. If an exception is
     * thrown the user will see an appropriate error message. */
    public static void write(ArrayList<ProspectiveStudent> toSave, String s) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(s));

            for (int i = 0; i < toSave.size(); i++) {
                writer.write(toSave.get(i).toString());
                writer.newLine();
                writer.newLine();
            }

            writer.close();

        }
        catch (IOException e) {
            System.out.println("There was a problem writing to the file: " + s);
            System.out.println(e.getMessage());
        }
    }

    /**************************************************************************/

	/* returns an ArrayList<ProspectiveStudent>, populated by deserializing from the file
     * student_admissions_file.ser. Handles the ClassNotFoundException in its own catch block,
     * handles any other potential exceptions. If an exception is thrown the user will see an
     * appropriate error message. */
    public static ArrayList<ProspectiveStudent> deserialize(String s) {
        //some statements missing here
        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(s));

            Object studentObject = os.readObject();

            @SuppressWarnings("unchecked")
            ArrayList<ProspectiveStudent> ps = (ArrayList<ProspectiveStudent>) studentObject;

            os.close();

            return ps;

        }
        catch (ClassNotFoundException c) {
            System.out.println("There was a problem reading the class of the object in file: " + s);
            System.out.println(c.getMessage());
        }
        catch (IOException e) {
            System.out.println("There was a problem reading from the file: " + s);
            System.out.println(e.getMessage());
        }

    return null;
    }

    /**************************************************************************/

	/*takes an ArrayList<ProspectiveStudent> and serializes it into the file student_admissions_file.ser.
     * Handles any potential exceptions. If an exception is thrown the user will see an appropriate
     * error message. */
    public static void serialize(ArrayList<ProspectiveStudent> prospectiveStudents, String s) {
       //some statements missing here
       try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(s));

            os.writeObject(prospectiveStudents);

            os.close();

        }
        catch (IOException e) {
            System.out.println("There was a problem writing to the file: " + s);
            System.out.println(e.getMessage());
        }

    }

    /**************************************************************************/

	/*do not change any part of the file below here, with the exception of uncommenting test statements from the getTestData() method if required. */
    public static void main(String[] args) {
        testRead();
        testWrite();
        testSerialize();
        testDeserialize();
    }

    private static void testRead() {
		System.out.println("Testing the read() method with default file "+filename);
		System.out.println();
        ArrayList<ProspectiveStudent> students = read();

        for(ProspectiveStudent ps : students) {
            System.out.println(ps);
        }
        System.out.println();
        System.out.println("End of test");
        System.out.println();
        System.out.println("/*****************************************/");
        System.out.println();
    }

    private static void testWrite() {
		System.out.println("Testing the write() method with file name testWrite.txt");
		System.out.println();
        write(getTestData(), "testWrite.txt");
	    System.out.println();
		System.out.println("End of test");
		System.out.println();
		System.out.println("/*****************************************/");
        System.out.println();
    }

    private static void testSerialize() {
		System.out.println("Testing the serialize() method with file name testSer.ser");
		System.out.println();
        serialize(getTestData(), "testSer.ser");
        System.out.println();
		System.out.println("End of test");
		System.out.println();
		System.out.println("/*****************************************/");
        System.out.println();
    }

    private static void testDeserialize() {
		System.out.println("Testing the deserialize() method with file testSer.ser");
		System.out.println();
        ArrayList<ProspectiveStudent> students = deserialize("testSer.ser");
        for (ProspectiveStudent p : students) {
            System.out.println(p);
        }
        System.out.println();
		System.out.println("End of test");
        System.out.println();
		System.out.println("/*****************************************/");
        System.out.println();
    }

    private static ArrayList<ProspectiveStudent> getTestData() {
        ProspectiveStudent ps1 = new ProspectiveStudent(1, "Adrian Mole", new GregorianCalendar(1980, 0, 20), Gender.MALE, "1237894560", "mole@example.com", 100211L, QualificationsType.ACTUAL, FeeType.HOME, OfferStatus.UNCONDITIONAL);
        ProspectiveStudent ps2 = new ProspectiveStudent(2, "Some Guy", new GregorianCalendar(1980, 2, 31), Gender.MALE, "1234567890", "guy@example.com", 100302L, QualificationsType.ACTUAL, FeeType.OVERSEAS, OfferStatus.UNCONDITIONAL);
        ProspectiveStudent ps3 = new ProspectiveStudent(3, "Guy Man", new GregorianCalendar(1980, 6, 12), Gender.MALE, "0123456789", "guy@example.fr", 100190L, QualificationsType.POTENTIAL, FeeType.HOME, OfferStatus.PENDING);
        /*ProspectiveStudent ps4 = new ProspectiveStudent(4, "A Lady", new GregorianCalendar(1980, 4, 9), Gender.FEMALE, "07798765", "al@example.ca", 100404L, QualificationsType.POTENTIAL, FeeType.OVERSEAS, OfferStatus.CONDITIONAL);
        ProspectiveStudent ps5 = new ProspectiveStudent(5, "Jane Doe", new GregorianCalendar(1980, 11, 13), Gender.FEMALE, "+447000000", "jdoe@example.co.uk", 100250L, QualificationsType.ACTUAL, FeeType.HOME, OfferStatus.UNCONDITIONAL);
        ProspectiveStudent ps6 = new ProspectiveStudent(6, "Mx Smith", new GregorianCalendar(1980, 8, 1), Gender.OTHER, "+331234567", "mxsmith@example.fr", 100140L, QualificationsType.ACTUAL, FeeType.HOME, OfferStatus.UNCONDITIONAL);*/

        ArrayList<ProspectiveStudent> pss = new ArrayList<>();
        pss.add(ps1);
        pss.add(ps2);
        pss.add(ps3);
       // pss.add(ps4);
       // pss.add(ps5);
       // pss.add(ps6);
        return pss;
    }
}

