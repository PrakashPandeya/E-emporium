package util;

public class ValidationUtil {

   
    public static boolean isTextOnly(String text) {
        return text.matches("[a-zA-Z\\s]+"); // Match letters and whitespace only
    }

   
    public static boolean isNumbersOnly(String text) {
        return text.matches("\\d+"); // Match digits only
    }

    public static boolean isAlphanumeric(String text) {
        return text.matches("[a-zA-Z0-9]+"); // Match letters and digits only
    }

    public static boolean isEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$"); // Match standard email pattern
    }


    public static boolean hasNoSpecialCharacters(String text) {
        return text.matches("[a-zA-Z0-9\\s]+"); // Match only letters, digits, and whitespace
    }


    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]*$"); // No length validation
    }


    public static boolean hasLength(String text, int length) {
        return text.length() == length;
    }
    
    

    public static boolean isGenderMatches(String gender) {
        // Convert the gender to lowercase and compare with "male" and "female" ignoring case
        return gender != null && (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("other"));
    }
}