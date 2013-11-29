package main;

import file.FileManager;
import codageParPlages.CodageParPlages;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lz.LZ;
import mtf.Mtf;

public class Main {

    public static void main(String[] args) {

        FileManager file = new FileManager();
        String word = null;
        Mtf mtf;
        LZ lz;
        CodageParPlages cp;

        switch (args[0]) {
            case "-c":
                try {
                    word = file.readFile(args[1]);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                mtf = new Mtf();
                mtf.codage(word);
                lz = new LZ();
                lz.code(mtf.getCode());
                try {
                    file.writeC(lz.getCode(), ".mz78", args[1]);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "-d":
                try {
                    word = file.readD(args[1]);
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                lz = new LZ();
                lz.decode(word);
                mtf = new Mtf();
                mtf.decodage(lz.getDecode());
                try {
                    file.writeFile(mtf.getDecode(), args[1]);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case "-p":
                try {
                    word = file.readFile(args[1]);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                mtf = new Mtf();
                cp = new CodageParPlages();
                mtf.codage(word);
                cp.code(mtf.getCode());
                try {
                    file.writeC(cp.getCode(), ".codp", args[1]);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "-q":
                try {
                    word = file.readD(args[1]);
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                cp = new CodageParPlages();
                cp.decode(word);
                mtf = new Mtf();
                mtf.decodage(cp.getDecode());
                try {
                    file.writeFile(mtf.getDecode(), args[1]);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            default:
                System.out.println("L'option n'est pas valide");
                break;
        }
    }
}
