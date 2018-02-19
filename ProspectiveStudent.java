import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;
import java.util.*;


public class ProspectiveStudent implements Serializable {
    private int id;
    private String name;
    private Calendar dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private String emailAddress;
    private long courseAppliedTo;
    private QualificationsType qualifications;
    private FeeType feeType;
    private OfferStatus offer;

    public ProspectiveStudent() {

		id = 636;
		name = "Fred Test";
		gender = Gender.MALE;
		try{
			Date date = new SimpleDateFormat().parse("12/12/1912");
			dateOfBirth = Calendar.getInstance();
			dateOfBirth.setTime(date);
		}
		catch(ParseException e) {
			System.err.println("Invalid date format");
		}
		phoneNumber = "000000000";
		emailAddress = "fredtest@fakeserver.com";
		courseAppliedTo = 000000;
		qualifications = QualificationsType.POTENTIAL;
		feeType = FeeType.HOME;
		offer = OfferStatus.CONDITIONAL;
    }

    public ProspectiveStudent(int id, String name, Calendar birthdate, Gender gender, String phoneNumber,
                              String emailAddress, long courseAppliedTo, QualificationsType qualifications,
                              FeeType feeType, OfferStatus offer) {

        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = birthdate;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.courseAppliedTo = courseAppliedTo;
        this.qualifications = qualifications;
        this.feeType = feeType;
        this.offer = offer;

    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public long getCourseAppliedTo() {
        return courseAppliedTo;
    }

    public void setCourseAppliedTo(long courseAppliedTo) {
        this.courseAppliedTo = courseAppliedTo;
    }

    public OfferStatus getOfferStatus() {
        return offer;
    }

    public void setOfferStatus(OfferStatus offer) {
        this.offer = offer;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    public QualificationsType getQualificationsType() {
        return qualifications;
    }

    public void setQualificationsType(QualificationsType qualifications) {
        this.qualifications = qualifications;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID: ").append(id).append(System.lineSeparator());
        sb.append("Name: ").append(name).append(System.lineSeparator());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDob = formatter.format(dateOfBirth.getTime());
        sb.append("Date Of Birth: ").append(formattedDob).append(System.lineSeparator());
        sb.append("Gender: ").append(gender).append(System.lineSeparator());
        sb.append("Phone Number: ").append(phoneNumber).append(System.lineSeparator());
        sb.append("Email Address: ").append(emailAddress).append(System.lineSeparator());
        sb.append("Course Applied To: ").append(courseAppliedTo).append(System.lineSeparator());
        sb.append("Qualifications: ").append(qualifications).append(System.lineSeparator());
        sb.append("Tuition Fee Status: ").append(feeType).append(System.lineSeparator());
        sb.append("Offer Status: ").append(offer).append(System.lineSeparator());

        return sb.toString();
        //StringBuilder is used here because of the need to make a fairly complex String. Strings are immutable, meaning that they cannot be changed once made. While you can concatenate Strings to make a new String, the original String remains in memory, hence the memory can fill up if a lot of String concatenation is done. StringBuilder is mutable, and also has an overloaded append() method, able to append many different types. See the API for more information. https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
    }
}
