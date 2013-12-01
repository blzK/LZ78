import java.util.Random;
import lz.LZ;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class LZTest {

    public LZTest() {
    }

    @Test
    public void testCodeDifferentChars() {
        LZ lz = new LZ();
        lz.code("abc");
        assertEquals("a0b0c0", lz.getCode());
    }

    @Test
    public void testSimpleCode() {
        LZ lz = new LZ();
        lz.code("aab");
        assertEquals("a0b1", lz.getCode());
    }

    @Test
    public void testCodeInteger1() {
        LZ lz = new LZ();
        lz.code("1");
        assertEquals("|1|0", lz.getCode());
    }

    @Test
    public void testCodeInteger2() {
        LZ lz = new LZ();
        lz.code("111111");
        System.out.println(lz.getCode());
        assertEquals("|1|0|1|1|1|2", lz.getCode());
    }

    @Test
    public void testEpsilone() {
        LZ lz = new LZ();
        lz.code("aa");
        assertEquals("a0E1", lz.getCode());
    }

    @Test
    public void testEpsiloneBinaryInput() {
        LZ lz = new LZ();
        lz.code("1111");
        assertEquals("|1|0|1|1E1", lz.getCode());
    }

    @Test
    public void testCodeLongDifferentChars() {
        LZ lz = new LZ();
        lz.code("aaabc");
        assertEquals("a0a1b0c0", lz.getCode());
    }

    @Test
    public void redundantChars() {
        LZ lz = new LZ();
        lz.code("aaa");
        assertEquals("a0a1", lz.getCode());
    }

    @Test
    public void testLongCode() {
        LZ lz = new LZ();
        lz.code("aaabaababa");
        System.out.println("Dictionnaire");
        System.out.println(lz.getDict());
        System.out.println("Code");
        System.out.println(lz.getCode());
        System.out.println("a0a1b0b2b1E1");
        assertEquals("a0a1b0b2b1E1", lz.getCode());
    }

    @Test
    public void testLongCode2() {
        LZ lz = new LZ();
        lz.code("abbbbbbaccccbb");
        System.out.println("Dictionnaire");
        System.out.println(lz.getDict());
        System.out.println("Code");
        System.out.println(lz.getCode());
        assertEquals("a0b0b2b3c1c0c6E3", lz.getCode());

    }

    @Test
    public void testLongCode3() {
        LZ lz = new LZ();
        lz.code("ababababababababab");
        System.out.println("Dictionnaire");
        System.out.println(lz.getDict());
        System.out.println("Code");
        System.out.println(lz.getCode());
        assertEquals("a0b0b1a3a2b5b4E3", lz.getCode());

    }

    @Test
    public void testLongCode4() {
        LZ lz = new LZ();
        lz.code("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); //55
        System.out.println("Dictionnaire");
        System.out.println(lz.getDict());
        System.out.println("Code");
        System.out.println(lz.getCode());
        assertEquals("a0a1a2a3a4a5a6a7a8a9a10", lz.getCode());

    }

    @Test
    public void testLongCode6() {//This test only applies to codes not ending with Epsilone
        LZ lz = new LZ();
        StringBuilder sb = new StringBuilder("");
        StringBuilder sbResult = new StringBuilder("");
        int sum = 0;
        int i = 0;
        for (i = 0; i < 55; i++) {
            sum += i;
            sb.append("a");

        }
        int len = sb.toString().length();
        int j = 1;
        while (len > 0) {
            sbResult.append("a").append(j - 1);
            j++;
            len = len - j;
        }
        System.out.println(sum + " SUM ");
        System.out.println("len " + len + " j " + j);
        System.out.println("Code ");
        System.out.println(sb.toString());
        lz.code(sb.toString());
        System.out.println("Dictionnaire");
        System.out.println(lz.getDict());
        System.out.println("Calculated Code");
        System.out.println(lz.getCode());
        System.out.println("Expected Code");
        System.out.println(sbResult.toString());
        assertEquals(sbResult.toString(), lz.getCode());

    }

    @Test
    public void testCompression1() {
        LZ lz = new LZ();
        StringBuilder sb = new StringBuilder("");
        int sum = 0;
        int i = 0;
        for (i = 0; i < 150; i++) {
            sum += i;
            sb.append("blablablejrbaejrbakerjbhaeklrjb");
        }
        System.out.println("code ");
        lz.code(sb.toString());
        String code = lz.getCode();
        System.out.println(code);
        lz.decode(lz.getCode());
        System.out.println(lz.getCode());
        System.out.println(lz.getDecode());
        System.out.println(lz.getDictDecod());
        System.out.println(" CODE ");
        System.out.println(code);
        assertEquals(sb.toString(), lz.getDecode().toString());
        assertTrue(sb.toString().length() > lz.getCode().toString().length());
        System.out.println("longueur initiale " + sb.toString().length());
        System.out.println("longueur compressée " + lz.getCode().toString().length());

    }

    @Test
    public void testCompressionredundantRandomString() {
        LZ lz = new LZ();
        StringBuilder sb = new StringBuilder("");
        Random random = new Random();
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (int i = 0; i < 10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        for (int i = 0; i < 4; i++) {
            sb.append(sb.toString());
        }
        System.out.println("code ");
        lz.code(sb.toString());
        String code = lz.getCode();
        System.out.println(code);
        lz.decode(lz.getCode());
        System.out.println(lz.getDecode());
        System.out.println(lz.getDictDecod());
        System.out.println(" CODE ");
        System.out.println(code);
        assertEquals(sb.toString(), lz.getDecode().toString());
        assertTrue(sb.toString().length() > lz.getCode().toString().length());
        System.out.println("longueur initiale " + sb.toString().length());
        System.out.println("longueur compressée " + lz.getCode().toString().length());
    }

    @Test
    public void decodeTest() {
        LZ lz = new LZ();
        lz.code("abbbbbbaccccbb");
        lz.decode(lz.getCode().toString());
        System.out.println(lz.getDecode());
        System.out.println("abbbbbbaccccbb");
        assertEquals("abbbbbbaccccbb", lz.getDecode().toString());
    }

    @Test
    public void decodeTest2() {
        LZ lz = new LZ();
        lz.code("111111");
        System.out.println("code ");
        System.out.println(lz.getCode());
        lz.decode(lz.getCode().toString());
        System.out.println(lz.getDecode());
        System.out.println(lz.getDictDecod());
        assertEquals("111111", lz.getDecode().toString());
    }

    @Test
    public void decodeTest3() {
        LZ lz = new LZ();
        lz.decode("a0a1a2a3a4a5a6a7a8a9a10");
        System.out.println(lz.getDecode());
        System.out.println(lz.getDictDecod());
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", lz.getDecode().toString());
    }

    @Test
    public void decodeVeryLongMixedTest() {
        LZ lz = new LZ();
        StringBuilder sb = new StringBuilder("");
        int sum = 0;
        int i = 0;
        for (i = 0; i < 150_000; i++) {
            sum += i;
            sb.append("01123549wsf8402106846bcvbx084065410687408/0/70");
        }
        System.out.println("code ");
        lz.code(sb.toString());
        String code = lz.getCode();
        System.out.println(code);
        lz.decode(lz.getCode());
        System.out.println(lz.getDecode());
        System.out.println(lz.getDictDecod());
        System.out.println(" CODE ");
        System.out.println(code);
        assertEquals(sb.toString(), lz.getDecode().toString());
    }

    @Test
    public void decodeTest4() {
        LZ lz = new LZ();
        lz.code("La religion est l'esprit d'un monde sans esprit comme elle est le coeur d'un monde sans coeur");
        System.out.println("code ");
        System.out.println(lz.getCode());
        lz.decode(lz.getCode());
        System.out.println("Dictionnaire");
        System.out.println(lz.getDictDecod());
        System.out.println("Calculated decode");
        System.out.println(lz.getDecode());
        System.out.println("Expected decode");
        System.out.println("La religion est l'esprit d'un monde sans esprit comme elle est le coeur d'un monde sans coeur");
        assertEquals("La religion est l'esprit d'un monde sans esprit comme elle est le coeur d'un monde sans coeur", lz.getDecode());

    }

    @Test
    public void decodeTestMixedInput() {
        LZ lz = new LZ();
        String input = "La religion est l'es45465prit d'un monde sans esprit 486486comme elle e86456st le coeur d'un mo5456nde sans coeur";
        lz.code(input);
        System.out.println("code ");
        System.out.println(lz.getCode());
        lz.decode(lz.getCode());
        System.out.println("Dictionnaire");
        System.out.println(lz.getDictDecod());
        System.out.println("Calculated decode");
        System.out.println(lz.getDecode());
        System.out.println("Expected decode");
        System.out.println(input);
        assertEquals(input, lz.getDecode());

    }

}
