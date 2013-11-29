/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import lz.LZ;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author azathoth
 */
public class LZTests {
    
    public LZTests() {
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
        //System.out.println("a0a1b0b2aE");

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
        //System.out.println("a0a1b0b2aE");

        assertEquals("a0b0b1a3a2b5b4E3", lz.getCode());
        
    }
    
    @Test
    public void testLongCode4() {
        LZ lz = new LZ();
        lz.code("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); //55
        //
        System.out.println("Dictionnaire");
        System.out.println(lz.getDict());
        System.out.println("Code");
        System.out.println(lz.getCode());
        //System.out.println("a0a1b0b2aE");

        assertEquals("a0a1a2a3a4a5a6a7a8a9a10", lz.getCode());
        
    }
    
    @Test
    public void testLongCode6() {
        //This test only applies to codes not ending with Epsilone
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
//        System.out.println("i " + i + " j " + j);
//        if (j != i) {
//            sbResult.append("E").append(lz.getDictDecod())(i-j));
//        }
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

//    @Test
//    public void testLongCode6() {
//        LZ lz = new LZ();
//        lz.code("bed spreaders spread spreads on beds");
//        System.out.println("Dictionnaire");
//        System.out.println(lz.getDict());
//        System.out.println("Calculated Code");
//        System.out.println(lz.getCode());
//        System.out.println("Expected Code");
//        System.out.println("b0e0d0 0s0p0r0a2e3s7s4r6d8p11e7a0 3r14s13o4n0b4d2E5");
//        assertEquals("b0e0d0 0s0p0r0a2e3s7s4r6d8p11e7a0 3r14s13o4n0b4d2E5", lz.getCode());
//
//    }
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
    public void decodeTest5() {
        LZ lz = new LZ();
        StringBuilder sb = new StringBuilder("");
        StringBuilder sbResult = new StringBuilder("");
        
        int sum = 0;
        int i = 0;
        for (i = 0; i < 147; i++) {
            sum += i;
            sb.append("1");
        }
        System.out.println("code ");
        lz.code(sb.toString());
        System.out.println(lz.getCode());
        lz.decode(lz.getCode());
        System.out.println(lz.getDecode());
        System.out.println(lz.getDictDecod());
        
        assertEquals(sb.toString(), lz.getDecode().toString());
        
    }
    
    @Test
    public void decodeTest4() {
        LZ lz = new LZ();
        
        lz.code("La religion est l'esprit d'un monde sans esprit comme elle est le coeur d'un monde sans coeur");
        System.out.println("code ");
        System.out.println(lz.getCode());
        String decode = lz.decode(lz.getCode());
        System.out.println("Dictionnaire");
        System.out.println(lz.getDictDecod());
        System.out.println("Calculated decode");
        System.out.println(lz.getDecode());
        System.out.println("Expected decode");
        System.out.println("La religion est l'esprit d'un monde sans esprit comme elle est le coeur d'un monde sans coeur");
        assertEquals("La religion est l'esprit d'un monde sans esprit comme elle est le coeur d'un monde sans coeur", lz.getDecode());
        
    }
}
