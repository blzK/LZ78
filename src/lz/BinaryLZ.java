package lz;


import lz.LZ;

public class BinaryLZ {

    private LZ lz = new LZ();

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

}
