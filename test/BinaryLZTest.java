/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import lz.BinaryLZ;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author azathoth
 */
public class BinaryLZTest {
    
     
    @Test
    public void BinaryCodeTest() {
        BinaryLZ lz= new BinaryLZ();
        String input="1010";
        lz.code(input);
        System.out.println("input");
        System.out.println(input);
        System.out.println("code");
        System.out.println(lz.getCode());
        assertEquals("B0A0A1",lz.getCode());
    }
    
      @Test
    public void BinaryDecodeTest() {
        BinaryLZ lz= new BinaryLZ();
        String input="1010";
        lz.code(input);
        String code =lz.getCode();
          System.out.println(code);
        lz.decode(code);
        System.out.println("input");
        System.out.println(input);
        System.out.println("decode");
          System.out.println(lz.getDecode());
        System.out.println("code");
        System.out.println(lz.getCode());
        assertEquals(input, lz.getDecode());
    }
    
    @Test
    public void testCompression1() {
        BinaryLZ lz = new BinaryLZ();
        StringBuilder sb = new StringBuilder("");

        int sum = 0;
        int i = 0;
        for (i = 0; i < 150; i++) {
            sum += i;
            sb.append("01");
        }

        System.out.println("code ");
        lz.code(sb.toString());

        String code = lz.getCode();
        System.out.println(code);
        lz.decode(lz.getCode());
        System.out.println(lz.getCode());
        System.out.println(lz.getDecode());
        
        System.out.println(" CODE ");
        System.out.println(code);
        assertEquals(sb.toString(), lz.getDecode().toString());

        assertTrue(sb.toString().length() > lz.getCode().toString().length());
        System.out.println("longueur initiale " + sb.toString().length());
        System.out.println("longueur compressée " + lz.getCode().toString().length());

    }
    
     
    @Test
    public void testRedundantRandomCompression1() {
        BinaryLZ lz = new BinaryLZ();
        StringBuilder sb = new StringBuilder("");

        
        Random random = new Random();
        char[] chars = "01".toCharArray();
        for (int i = 0; i < 10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        StringBuilder sb2 = new StringBuilder("");
        int sum = 0;
        int i = 0;
        for (i = 0; i < 150; i++) {
            sum += i;
            sb2.append(sb.toString());
        }

        System.out.println("code ");
        lz.code(sb2.toString());

        String code = lz.getCode();
        System.out.println(code);
        lz.decode(lz.getCode());
        System.out.println(lz.getCode());
        System.out.println("input");
        System.out.println(sb2);
        System.out.println("decode");        
        System.out.println(lz.getDecode());
        
        System.out.println(" CODE ");
        System.out.println(code);
        assertEquals(sb2.toString(), lz.getDecode().toString());

       assertTrue(sb2.toString().length() > lz.getCode().toString().length());
        System.out.println("longueur initiale " + sb2.toString().length());
        System.out.println("longueur compressée " + lz.getCode().toString().length());

    }
    
    @Test
    public void testRandomCompression1() {
        BinaryLZ lz = new BinaryLZ();
        StringBuilder sb = new StringBuilder("");

        
        Random random = new Random();
        char[] chars = "01".toCharArray();
        for (int i = 0; i < 10_000; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        System.out.println("code ");
        lz.code(sb.toString());
        String code = lz.getCode();
        System.out.println(code);
        lz.decode(lz.getCode());
        System.out.println(lz.getCode());
        System.out.println("input");
        System.out.println(sb);
        System.out.println("decode");        
        System.out.println(lz.getDecode());
        System.out.println(" CODE ");
        System.out.println(code);
        assertEquals(sb.toString(), lz.getDecode().toString());
       assertTrue(sb.toString().length() > lz.getCode().toString().length());
        System.out.println("longueur initiale " + sb.toString().length());
        System.out.println("longueur compressée " + lz.getCode().toString().length());

    }
}
