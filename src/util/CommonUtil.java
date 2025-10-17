package util;

public class CommonUtil {
    public static boolean isValidAlphabeticString(String str) {
        if (str == null || str.isBlank()) {
            return false;
        }
        for (char ch : str.toCharArray()) {
            if (!Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
    }
}
