/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codageParPlages;

/**
 *
 * @author Maravillas
 */
public class CodageParPlages {

    private final char[] alphabet;
    private StringBuilder decode;
    private StringBuilder code;

    public CodageParPlages() {
        alphabet = new char[2];
        this.alphabet[0] = '0';
        this.alphabet[1] = '1';
    }

    public String code(String word) {
        if (word != null) {
            code = new StringBuilder();
            int count = 0;

            for (int i = 0; i < word.length(); i++) {
                switch (word.charAt(i)) {
                    case '0':
                        count++;
                        if (i + 1 < word.length()) {
                            if (word.charAt(i + 1) != alphabet[0]) {
                                code.append(count);
                                code.append('A');
                                count = 0;
                            }
                        } else if (i == word.length() - 1) {
                            code.append(count);
                            code.append('A');
                        }
                        break;

                    case '1':
                        count++;
                        if (i + 1 < word.length()) {
                            if (word.charAt(i + 1) != alphabet[1]) {

                                code.append(count);
                                code.append('B');
                                count = 0;
                            }
                        } else if (i == word.length() - 1) {
                            code.append(count);
                            code.append('B');
                        }
                        break;

                }
            }
            return code.toString();
        } else {
            throw new IllegalArgumentException("The word  must not be null");
        }
    }

    public String decode(String codeWord) {
        if (codeWord != null) {
            decode = new StringBuilder();
            StringBuilder sum = new StringBuilder();
            String c;
            char l;
            int count = 0;
            for (int i = 0; i < codeWord.length()-1; i++) {
                switch (l=codeWord.charAt(i)) {
                    case 'A':
                        for (int j = 0; j < count; j++) {
                            decode.append(alphabet[0]);
                        }
                        sum.setLength(0);
                        break;

                    case 'B':
                        for (int j = 0; j < count; j++) {
                            decode.append(alphabet[1]);
                        }
                        sum.setLength(0);
                        break;
                        
                   default:
                        c = "" + l;
                        if(!c.isEmpty()){
                        sum.append(c);  
                        count = Integer.parseInt(sum.toString());
                        }
                        break;
                }
            }
            return decode.toString();
        } else {
            throw new IllegalArgumentException("Codeword  must not be null");
        }

    }

    public String getCode() {
        return code.toString();
    }

    public String getDecode() {
        return decode.toString();
    }

}
