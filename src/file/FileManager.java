/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public String readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        byte[] bytes = Files.readAllBytes(path);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8 * bytes.length; i++) {
            if (((bytes[i / 8] << (i % 8)) & 128) == 0) {
                sb.append('0');
            } else {
                sb.append('1');
            }
        }
        return sb.toString();
    }

    public void writeFile(String s,String codedFileName) throws IOException {
        String fileName = codedFileName.substring(0, codedFileName.length() - 5);
        Path path = Paths.get(fileName);
        int sLen = s.length();
        byte[] bytes = new byte[sLen / 8];
        for (int i = 0; i < sLen; i++) {
            if (s.charAt(i) == '1') {
                bytes[i / 8] = (byte) (bytes[i / 8] | (128 >>> (i % 8)));
            }
        }
        Files.write(path, bytes);
    }

    public String readD(String path) throws Exception {
        StringBuilder data = new StringBuilder("");
            FileInputStream in = new FileInputStream(path);
            BufferedInputStream in2 = new BufferedInputStream(in);
            DataInputStream in3 = new DataInputStream(in2);
            while (in3.available() > 0) {
                data.append(in3.readLine());
                data.append("\n");
            }
        return data.toString();
    }


    public void writeC(String decodage, String format, String path) throws FileNotFoundException {
        try (FileWriter fichero = new FileWriter(path + format)) {
            fichero.write(decodage + "\r\n");
        } catch (IOException ex) {
        }
    }
}
