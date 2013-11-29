/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mtf;

/**
 *
 * @author Felipe
 */
public class Mtf {
    
    private String word;
    private char[] alphabet;    
    private StringBuilder decode;
    private StringBuilder code;
    
    public Mtf(){
        this.alphabet = new char[2];
        this.alphabet[0]='0';
        this.alphabet[1]='1';
    }
    
    public String codage(String word){
        this.word = word;
        code = new StringBuilder();
        for(char c : word.toCharArray()){
            switch (c){
                case '0':
                    if(this.alphabet[0]=='0'){
                        code.append("0");
                    }else{
                        code.append("1");
                        this.alphabet[0]='0';
                        this.alphabet[1]='1';
                    }
                    break;
                case '1':
                    if(this.alphabet[0]=='1'){
                        code.append("0");
                    }else{
                        code.append("1");
                        this.alphabet[0]='1';
                        this.alphabet[1]='0';
                    }
                    break;
            }
        }
        return code.toString();
    }
    
    public String decodage(String word){
        this.word = word;
        decode = new StringBuilder();
        for(char c : this.word.toCharArray()){
            switch (c) {
                case '0':
                    decode.append(this.alphabet[0]);
                    break;
                case '1':
                    char temp;
                    decode.append(this.alphabet[1]);
                    temp = this.alphabet[0];
                    this.alphabet[0] = this.alphabet[1];
                    this.alphabet[1] = temp;
                    break;
            }
        }
        return decode.toString();
    }

    public String getDecode() {
        return decode.toString();
    }

    public String getCode() {
        return code.toString();
    }

    
}
