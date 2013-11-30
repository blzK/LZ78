package lz;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * The BinaryLZ Class encapsulates LZ class in order to simplify binary input. 
 * BinaryLZ does take only binary input.
 * The BinaryLZ class enables LZ to get rid of any number delimiter.
 * Thus it generates smaller output.
 * 
 */


public class BinaryLZ {

    private final LZ lz = new LZ();
  /**
     *
     * @param input is the String input to be coded.
     * The input String must only contain '0' and '1' characters.
     */
    public void code(String input) {

        StringBuilder s1 = new StringBuilder();
        for (int k = 0; k < input.length(); k++) {
            if (input.charAt(k) == '0') {
                s1.append('A');
            } else if (input.charAt(k) == '1') {
                s1.append('B');
            }
        }

        lz.code(s1.toString());

    }

    public String getCode() {

        return lz.getCode();
    }
    /**
     *
     * @param input is the String code input to be decoded.
     */
    public void decode(String input) {
        lz.decode(input);
    }

    public String getDecode() {
        if (lz.getDecode().isEmpty()) {
            throw new IllegalStateException("Has not been coded");
        }
        String decode = lz.getDecode();
        StringBuilder s1 = new StringBuilder("");
        for (int k = 0; k < decode.length(); k++) {
            if (decode.charAt(k) == 'A') {
                s1.append('0');
            } else if (decode.charAt(k) == 'B') {
                s1.append('1');
            }
        }

        return s1.toString();
    }

    public boolean write(String input, String urlOutput) throws IOException{
        lz.write(input,urlOutput);
        return true;
    }
    
    public String read(String url) throws FileNotFoundException, IOException{
        return lz.read(url);
    }
}
