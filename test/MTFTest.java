/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import mtf.Mtf;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Felipe
 */
public class MTFTest {
    
    public MTFTest() {
    }
  
    @Test
    public void testSimpleCode() {
        Mtf mtf = new Mtf();
        mtf.codage("000");
        assertEquals("000", mtf.getCode());
    }

    @Test
    public void testCodeLongCode() {
        Mtf mtf = new Mtf();
        mtf.codage("10001111101");
        assertEquals("11001000011", mtf.getCode());
    }
    
    @Test(expected=NullPointerException.class)
    public void testCodeNull(){
        Mtf mtf = new Mtf();
        mtf.codage(null);
    }

    @Test
    public void decodeTest() {
        Mtf mtf = new Mtf();
        mtf.decodage("111111111111000100000000000000000000000000000000000");
        assertEquals("101010101010000111111111111111111111111111111111111", mtf.getDecode());        
    }
    
    @Test(expected=NullPointerException.class)
    public void testDecodeNull(){
        Mtf mtf = new Mtf();
        mtf.decodage(null);
    }
}
