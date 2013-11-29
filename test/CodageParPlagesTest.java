/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import codageParPlages.CodageParPlages;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maravillas
 */
public class CodageParPlagesTest {
    
    public CodageParPlagesTest() {
    }
    
    @Test
    public void testSimpleCode() {
        CodageParPlages cp = new CodageParPlages();
        cp.code("000");
        System.out.println(cp.getCode());
        assertEquals("3A", cp.getCode());
    }

    @Test
    public void testCodeLongCode() {
        CodageParPlages cp = new CodageParPlages();
        cp.code("10001111101");
        assertEquals("1B3A5B1A1B", cp.getCode());

    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCodeNull(){
        CodageParPlages cp = new CodageParPlages();
        cp.code(null);
    }

    @Test
    public void decodeTest() {
        CodageParPlages cp = new CodageParPlages();
        cp.code("111111111111000100000000000000000000000000000000000");
        cp.decode(cp.getCode());
        assertEquals("111111111111000100000000000000000000000000000000000", cp.getDecode());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testDecodeNull(){
        CodageParPlages cp = new CodageParPlages();
        cp.decode(null);
    }
}
