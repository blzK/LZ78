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
        System.out.println(lz.dict);
        System.out.println("Code");
        System.out.println(lz.code);
        System.out.println("a0a1b0b2b1E1");
        assertEquals("a0a1b0b2b1E1", lz.getCode());

    }

    @Test
    public void testLongCode2() {
        LZ lz = new LZ();
        lz.code("abbbbbbaccccbb");
        System.out.println("Dictionnaire");
        System.out.println(lz.dict);
        System.out.println("Code");
        System.out.println(lz.code);
        //System.out.println("a0a1b0b2aE");

        assertEquals("a0b0b2b3c1c0c6E3", lz.getCode());

    }

    @Test
    public void testLongCode3() {
        LZ lz = new LZ();
        lz.code("ababababababababab");
        System.out.println("Dictionnaire");
        System.out.println(lz.dict);
        System.out.println("Code");
        System.out.println(lz.code);
        //System.out.println("a0a1b0b2aE");

        assertEquals("a0b0b1a3a2b5b4E3", lz.getCode());

    }
//    @Test
//    public void testLongCode4() {
//        LZ lz = new LZ();
//        lz.code("bed spreaders spread spreads on beds");
//        System.out.println("Dictionnaire");
//        System.out.println(lz.dict);
//        System.out.println("Calculated Code");
//        System.out.println(lz.code);
//        System.out.println("Expected Code");
//        System.out.println("b0e0d0 0s0p0r0a2e3s7s4r6d8p11e7a0 3r14s13o4n0b4d2E5");
//        assertEquals("b0e0d0 0s0p0r0a2e3s7s4r6d8p11e7a0 3r14s13o4n0b4d2E5", lz.getCode());
//
//    }

    @Test
    public void decodeTest() {
        LZ lz = new LZ();
        lz.code("abbbbbbaccccbb");
        lz.decode(lz.code.toString());
        System.out.println(lz.decode);
        System.out.println("abbbbbbaccccbb");
        assertEquals("abbbbbbaccccbb", lz.decode.toString());

    }
    
        @Test
    public void decodeTest2() {
        LZ lz = new LZ();
        lz.code("1010");
        lz.decode(lz.code.toString());
//        lz.decode("B0A0A1");
        System.out.println(lz.decode);
       // System.out.println("1010");
        assertEquals("1010", lz.decode.toString());

    }
}
