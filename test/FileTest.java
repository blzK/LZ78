/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author azathoth
 */
public class FileTest {
    
    public FileTest() {
    }
    
    
    public long code(String urlInput) throws IOException {
       String[] args={"-c",urlInput};
       Main.main(args);
       File file = new File(urlInput);
       return file.length();
    }
    
       
    public  long decode(String urlInput) throws IOException {
       String[] args={"-d",urlInput+".mz78"};
       Main.main(args);
       File file = new File(urlInput);
       return file.length();
    }
    
       @Test
    public  void compressDecompress() throws IOException {
       assertTrue(code("simpleImage.png")==decode("simpleImage.png"));
       assertTrue(code("verySimpleUncompressedImage")==decode("verySimpleUncompressedImage"));
    }
    
  
}
