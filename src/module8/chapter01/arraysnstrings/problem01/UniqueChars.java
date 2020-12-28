package module8.chapter01.arraysnstrings.problem01;

public class UniqueChars {

    /**
     * Finds out if the input string has unique characters or not
     *
     * Improvements which can be made:
     *  1. Ignore case
     *  2. Ignore spaces
     * 
     * @param input input string
     * @return true if it has all unique characters, else returns false
     */
    boolean hasUniqueChars(String input) {
        // We start with assuming that string has unique characters
        // until proved otherwise
        boolean result = true;
        Integer[] charFreqs = new Integer[65535];
        for (Character c : input.toCharArray()) {
            if (charFreqs[c - '\u0000'] != null) {
                result = false;
                break;
            } else {
                charFreqs[c - '\u0000'] = 1;
            }
        }
        return result;
    }

    static void hasUniqueCharsTest() {
        UniqueChars uc = new UniqueChars();
        assert(uc.hasUniqueChars("Huh")); // java is case sensitive
        assert(uc.hasUniqueChars("abc"));
        assert(uc.hasUniqueChars(""));
        assert(uc.hasUniqueChars(" "));
        assert(uc.hasUniqueChars("राम"));
        assert(!uc.hasUniqueChars("  ")); // 2 spaces
        assert(!uc.hasUniqueChars("Hello"));
        assert(!uc.hasUniqueChars("999"));
        assert(!uc.hasUniqueChars("जलज"));
        assert(!uc.hasUniqueChars(" kabir ")); // 2 spaces
    }

    // NOTE: Run the program with "-ea" VM flag
    // -ea to enable assertions
    public static void main(String[] args) {
        hasUniqueCharsTest();
    }
}
