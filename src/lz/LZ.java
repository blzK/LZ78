package lz;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author azathoth
 */
public class LZ {

    public Map<String, Integer> dict = new LinkedHashMap<>();
    public Map<Integer, String> dictDecod = new LinkedHashMap<>();
    private int alpha = 0;
    public StringBuilder data = new StringBuilder("");
    public StringBuilder code = new StringBuilder("");
    public StringBuilder decode = new StringBuilder("");

    public void code(String s) {
        int i = 0;
        int j = 1;
        int len = s.length();
//
//        StringBuilder s1 = new StringBuilder();
//        for (int k = 0; k < len; k++) {
//            if (s.charAt(k) == '0') {
//                s1.append('A');
//            } else if (s.charAt(k) == '1') {
//                s1.append('B');
//            }
//        }
//        s = s1.toString();

        while (i < s.length() && j <= s.length()) {

            if (dict.containsKey(s.substring(i, j))) {
                j++;
                if (j - 1 == s.length()) {

                    code.append("E");
                    code.append(dico(s.substring(i, j - 1)));
                    break;
                }
                continue;

            } else {
                if (dico(s.substring(i, j - 1)) == null) {

                    dict.put(s.substring(i, j), alpha + 1);
                    code.append(s.charAt(j - 1));
                    code.append(0);

                    alpha++;
                } else {

                    dict.put(s.substring(i, j), alpha + 1);
                    code.append(s.charAt(j - 1));
                    code.append(dico(s.substring(i, j - 1)));
                    alpha++;
                }
            }
            i = j;
            j++;
        }
    }

    public String decode(String s) {
        int beta = 1;
        StringBuilder s1 = new StringBuilder("");
        int len;
        int i = 0;
        int j = 1;

////        if (s.charAt(j + 1) != 'A' || s.charAt(j + 1) != 'B') {
////            if (i + 1 == j) {
////                System.out.println("jjjjjjjjj");
////                System.out.println(s.charAt(j));
////            } else {
////                System.out.println("fffffffff");
////                System.out.println(s.substring(i + 1, j));
////
////            }
////        }
        while (j < s.length()) {
            int subInt;
            System.out.println("**************");
            System.out.println("i = " + i);
            System.out.println("j = " + j);
            System.out.println("**************");

//            while (s.charAt(j + 1) == 'A' || s.charAt(j + 1) == 'B') {
//                
//                if (j + 1 < s.length()) {
//                    System.out.println(s.charAt(j + 1));
//                    j++;
//                    System.out.println("j "+ j);
//                }
//            }
//            if (s.charAt(j + 1) != 'A' || s.charAt(j + 1) != 'B') {
            if (i + 1 == j) {

                // subInt=Character.toString(s.charAt(j));
                subInt = Character.getNumericValue(s.charAt(j));
            } else {

                subInt = Integer.parseInt(s.substring(i + 1, j));

            }
//            } 
//        else {
//                throw new IllegalArgumentException("No valid input");
//            }

            System.out.println("SUB " + s.substring(i, j + 1));
            System.out.println("on regarde " + subInt + " et " + s.charAt(i) + " at i = " + i + " j = " + j);

            if (dictDecod.containsKey(subInt)) {
                System.out.println("dico contient " + subInt + " qui est " + dico(subInt));
                //FONCTION A VERIFIER
                if (String.valueOf(s.charAt(i)).equals("E")) {
                    decode.append(dico(Character.getNumericValue(s.charAt(j))));
                    dictDecod.put(-1, dico(Character.getNumericValue(s.charAt(j))));
                    break;
                }
                //FONCTION A VERIFIER
                String tmp_v = dico(subInt) + Character.toString(s.charAt(i));
                System.out.println("on met dans dico beta " + beta + " et " + tmp_v);
                dictDecod.put(beta, tmp_v);
                decode.append(tmp_v); //On en est là
            } else {
                dictDecod.put(beta, String.valueOf(s.charAt(i)));
                decode.append(String.valueOf(s.charAt(i)));
            }
////////                if (dictDecod.containsKey(Character.getNumericValue(s.charAt(i + 1)))) {
////////                    if (String.valueOf(s.charAt(i)).equals("E")) {
////////                        decode.append(dico(Character.getNumericValue(s.charAt(i + 1))));
////////                        dictDecod.put(-1, dico(Character.getNumericValue(s.charAt(i + 1))));
////////                        break;
////////                    }
////////                    dictDecod.put(beta, dico(Character.getNumericValue(s.charAt(i + 1))) + String.valueOf(s.charAt(i)));
////////                    decode.append(dico(Character.getNumericValue(s.charAt(i + 1)))).append(String.valueOf(s.charAt(i)));
////////                } else {
////////                    dictDecod.put(beta, String.valueOf(s.charAt(i)));
////////                    decode.append(String.valueOf(s.charAt(i)));
////////                }
            beta++;
            //pb ici !
            // i += 2;
            System.out.println("on increment j");
//                j++;
//
//            }
            System.out.println("i = " + i);
            System.out.println("j = " + j);
            i = j + 1;
            j = i + 1;

            System.out.println("i = " + i);
            System.out.println("j = " + j);
        }

//        len = decode.length();
//
//        for (int k = 0; k < len; k++) {
//            if (decode.charAt(k) == 'A') {
//                s1.append('0');
//            } else if (decode.charAt(k) == 'B') {
//                s1.append('1');
//            }
//        }
//        decode = s1;
        return decode.toString();

    }

    private Integer dico(String search) {
        return dict.get(search);
    }

    private String dico(Integer search) {
        return dictDecod.get(search);
    }

    public String getCode() {
        return code.toString();
    }

    public String getDecode() {
        return decode.toString();
    }
}
