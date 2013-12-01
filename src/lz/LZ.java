package lz;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * The LZ class encodes and decode according to the LZ78 algorithm.
 *
 */
public class LZ {

    private final Map<String, Integer> dict = new LinkedHashMap<>();
    private final Map<Integer, String> dictDecod = new LinkedHashMap<>();

    private final StringBuilder code = new StringBuilder("");
    private final StringBuilder decode = new StringBuilder("");
    private final String delimiter = "|";

    /**
     *
     * @return returns the dictionnary used for encoding.
     */
    public Map<String, Integer> getDict() {
        return dict;
    }

    /**
     *
     * @return returns the dictionnary used for decoding.
     */
    public Map<Integer, String> getDictDecod() {
        return dictDecod;
    }

    /**
     * Codes the data according to the LZ78 Algorithm.
     *
     * @param s is the String input to be coded
     */
    public void code(String s) {
        int alpha = 0;
        int i = 0;
        int j = 1;
        int len = s.length();
        while (i < s.length() && j <= s.length()) {
            String sub = s.substring(i, j);
            String sub2 = s.substring(i, j - 1);
            if (dict.containsKey(sub)) {// If in dictionnary, we increase search field
                j++;
                if (j - 1 == s.length()) {//If at the end of the String don't, just end code
                    code.append("E").append(dico(s.substring(i, j - 1)));
                    break;
                }
                continue;
            } else {// If not in dictionnary
                char lastCharofSub = s.charAt(j - 1);   //Next char
                boolean isNumber = false;   //IsNext char a number?
                try {
                    Integer.parseInt(Character.toString(lastCharofSub));
                    isNumber = true;
                } catch (NumberFormatException e) {
                    isNumber = false;
                }
                if (dico(sub2) == null) { //if subentry not in dictionnary then create new entry in dictionnary
                    dict.put(sub, alpha + 1);
                    if (isNumber == false) { //then put into code, without delimiters if lastchar is a number
                        code.append(lastCharofSub).append(0);
                    } else {//put into code with delimiters
                        code.append(delimiter).append(lastCharofSub).append(delimiter).append(0);
                    }

                } else {    //if subentry in dictionnary, create entry for entire entry
                    dict.put(sub, alpha + 1);
                    if (isNumber == false) {
                        code.append(s.charAt(j - 1)).append(dico(sub2));
                    } else {
                        code.append(delimiter).append(lastCharofSub).append(delimiter).append(dico(sub2));
                    }
                }
                alpha++;
            }
            i = j;
            j++;

        }
    }

    /**
     * Decodes the data according to the LZ78 Algorithm.
     *
     * @param s is the String code input to be decoded
     */
    public void decode(String s) {
        int beta = 1;
        StringBuilder s1 = new StringBuilder("");
        int len;
        int i = 0;
        int j = 1;

        while (j < s.length()) {   //while we are in the string
            boolean isSpecial = false;
            if (Character.toString(s.charAt(i)).equals(delimiter) && j + delimiter.length() < s.length()) {
                i++;
                j += delimiter.length();
                isSpecial = true;
            }
            int subInt;
            if (j + 1 < s.length()) {
                if (j + 1 < s.length()) {// We test if we have a letter or a number
                    boolean isNumber = false;
                    try {
                        Integer.parseInt(Character.toString(s.charAt(j + 1)));
                        isNumber = true;
                    } catch (NumberFormatException e) {
                        isNumber = false;
                    }
                    while (isNumber == true && j < s.length()) {  //we take all the number we get
                        j++;
                        if (j + 1 < s.length()) {
                            try {
                                Integer.parseInt(Character.toString(s.charAt(j + 1)));
                                isNumber = true;
                            } catch (NumberFormatException e) {
                                isNumber = false;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
            if (i + 1 == j) { //We test if is a Number 
                subInt = Character.getNumericValue(s.charAt(j));
            } else if (isSpecial == true) {
                subInt = Integer.parseInt(s.substring(i + 2, j + 1));
            } else {
                subInt = Integer.parseInt(s.substring(i + 1, j + 1));

            }
            if (dictDecod.containsKey(subInt)) {
                if (String.valueOf(s.charAt(j - 1)).equals("E") && !s.substring(j).equals("E")) {//Epsilone decoding
                    try {
                        decode.append(dico(Integer.parseInt(s.substring(j))));
                        dictDecod.put(-1, dico(Integer.parseInt(s.substring(j))));
                    } catch (NumberFormatException n) {
                        j--;
                        decode.append(dico(Integer.parseInt(s.substring(j))));
                        dictDecod.put(-1, dico(Integer.parseInt(s.substring(j))));
                    }
                    break;
                }
                String tmp_v = dico(subInt) + Character.toString(s.charAt(i));
                dictDecod.put(beta, tmp_v);
                decode.append(tmp_v); //On en est là
            } else {
                dictDecod.put(beta, String.valueOf(s.charAt(i)));
                if (!Character.toString(s.charAt(i)).equals(delimiter)) {
                    decode.append(String.valueOf(s.charAt(i)));
                }
            }
            beta++;
            i = j + 1;
            j = i + 1;
            if (Character.toString(decode.charAt(decode.length() - 1)).equals("E")) {
                decode.deleteCharAt(decode.length() - 1);
            }
        }
    }

    private Integer dico(String search) {
        return dict.get(search);
    }

    private String dico(Integer search) {
        return dictDecod.get(search);
    }

    /**
     *
     * @return returns the coded data.
     */
    public String getCode() {
        if (code.toString().isEmpty()) {
            throw new IllegalStateException("Has not been coded");
        }
        return code.toString();
    }

    /**
     *
     * @return returns the uncoded data.
     */
    public String getDecode() {
        if (decode.toString().isEmpty()) {
            throw new IllegalStateException("Has not been decoded");
        }
        return decode.toString();
    }
}
