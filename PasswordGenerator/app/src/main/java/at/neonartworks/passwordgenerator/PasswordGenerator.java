package at.neonartworks.passwordgenerator;

import java.util.Random;

/**
 * Created by NEON on 26.11.2016.
 */

public class PasswordGenerator {
    private static String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "!", "@", "ö", "ä", "<", ">", "|", "ß", "\\", "/", "§", "$", "%", ";", ",", "&"};
    private static String password;
    private String length;
    private int iLength;

    public PasswordGenerator(String length) {
        try{
            Integer.parseInt(length);
        }catch(Exception e){
            return;
        }

        this.length = length;
        this.iLength = Integer.parseInt(length);
    }

    public String getPassword(){
        if (password == null || password.equals("")||password.equals(" ")){
            return " ";
        }else{
            return password;
        }

    }

    public String generate() {

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < iLength; i++) {
            int indexRandom = random.nextInt(symbols.length);
            sb.append(symbols[indexRandom]);
        }
        password = sb.toString();

        return password;
    }


}
