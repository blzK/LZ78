package lz;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author azathoth
 */
public class LZ {

    private Map<String, Integer> dict = new LinkedHashMap<>();
    private Map<Integer, String> dictDecod = new LinkedHashMap<>();

    private StringBuilder code = new StringBuilder("");
    private StringBuilder decode = new StringBuilder("");
    private final String delimiter = "|";

    public Map<String, Integer> getDict() {
        return dict;
    }

    public Map<Integer, String> getDictDecod() {
        return dictDecod;
    }

    public void code(String s) {
        int alpha = 0;
        int i = 0;
        int j = 1;
        int len = s.length();

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
            
            String sub = s.substring(i, j);
            String sub2=s.substring(i, j - 1);
            System.out.println("************");
            System.out.println("SUB = "+ sub + " SUB2= " + sub2);
            
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
                    System.out.println("Nous rajoutons au code "+ lastCharofSub);
                    if (isNumber == false) { //then put into code, without delimiters if lastchar is a number
                        code.append(lastCharofSub).append(0);
                    } else {//put into code with delimiters
                        code.append(delimiter).append(lastCharofSub).append(delimiter).append(0);
                    }

                } else {    //if subentry in dictionnary, create entry for entire entry
                    dict.put(sub, alpha + 1);
                    System.out.println("Nous rajoutons au code "+ dico(sub2));
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

    public String decode(String s) {
        int beta = 1;
        StringBuilder s1 = new StringBuilder("");
        int len;
        int i = 0;
        int j = 1;
//////      If reading a Binary Input
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

        //while we are in the string
        while (j < s.length()) {
            boolean isSpecial = false;
            if (Character.toString(s.charAt(i)).equals(delimiter) && j + delimiter.length() < s.length()) {
                i++;
                j += delimiter.length();
                isSpecial = true;
            }
            int subInt;
            System.out.println("**************");
            System.out.println("i = " + i);
            System.out.println("j = " + j);
            System.out.println("**************");
            if (j + 1 < s.length()) {
                System.out.println("**************");
                System.out.println("**************");

// We test if we have a letter or a number
                if (j + 1 < s.length()) {
                    boolean isNumber;

                    isNumber = false;

                    try {
                        System.out.println("A REGARDER " + s.charAt(j + 1));
                        Integer.parseInt(Character.toString(s.charAt(j + 1)));
                        isNumber = true;
                    } catch (NumberFormatException e) {
                        isNumber = false;
                    }
                    // while (s.charAt(j + 1) != 'A' || s.charAt(j + 1) != 'B') {
                    while (isNumber == true && j < s.length()) {  //we take all the number we get
                        System.out.println("NOMBRE A DEUX CHIFFRES");
                        System.out.println(s.charAt(j + 1));
                        j++;
                        System.out.println("on increment DONC j =" + j);
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
                System.out.println("Nous avons un nombre à 1 chiffre");
                subInt = Character.getNumericValue(s.charAt(j));
            } else if (isSpecial == true) {
                subInt = Integer.parseInt(s.substring(i + 2, j + 1));
            } else {
                System.out.println("Nous avons un nommbre à plusieurs  chiffres");
                subInt = Integer.parseInt(s.substring(i + 1, j + 1));

            }

            System.out.println("SUB " + s.substring(i, j + 1));
            System.out.println("on regarde " + subInt + " et " + s.charAt(i) + " at i = " + i + " j = " + j);

            if (dictDecod.containsKey(subInt)) {
                System.out.println("dico contient " + subInt + " qui est " + dico(subInt));
                //FONCTION A VERIFIER
                if (String.valueOf(s.charAt(j-1)).equals("E")) {
                    System.out.println("__________________FIN___________");
                    System.out.println("Nous avons à la fin "+s.substring(j));
                    System.out.println("on rajoute "+dico(Integer.parseInt(s.substring(j))));
                    decode.append(dico(Integer.parseInt(s.substring(j))));
                    dictDecod.put(-1, dico(Integer.parseInt(s.substring(j))));
                    /*decode.append(dico(Character.getNumericValue(s.charAt(j))));
                    dictDecod.put(-1, dico(Character.getNumericValue(s.charAt(j))));*/
                    System.out.println("__________________FIN___________");
                    break;
                }
                //FONCTION A VERIFIER
                String tmp_v = dico(subInt) + Character.toString(s.charAt(i));
                System.out.println("on met dans dico beta " + beta + " et " + tmp_v);
                dictDecod.put(beta, tmp_v);
                decode.append(tmp_v); //On en est là
            } else {
                dictDecod.put(beta, String.valueOf(s.charAt(i)));
                if (!Character.toString(s.charAt(i)).equals(delimiter)) {
                    decode.append(String.valueOf(s.charAt(i)));
                }
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
//////      If reading Binary Input
////        len = decode.length();
////
////        for (int k = 0; k < len; k++) {
////            if (decode.charAt(k) == 'A') {
////                s1.append('0');
////            } else if (decode.charAt(k) == 'B') {
////                s1.append('1');
////            }
////        }
////        decode = s1;
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
