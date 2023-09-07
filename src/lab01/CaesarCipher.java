package lab01;

import java.util.*;

public class CaesarCipher {
	public static void main(String args[]) {
		/*
        Main method --> runs the Caesar cipher program by gathering user input and calling the cipher method  
        */
        // get user input using scanner object
        Scanner sc = new Scanner(System.in);

        // user message
        System.out.println("Type your message: ");
        String message_input = sc.nextLine();

        // offset for encryption
        System.out.println("Enter an offset (integer): ");
        int offset = sc.nextInt();

        // close scanner object
        sc.close();

        // call cipher method
        String encrypted_message = cipher(message_input, offset);

        // print encrypted message
        System.out.println(encrypted_message);
	}
	
	public static String cipher(String input, int offset){
		 /*
        Uses an algorithm to encrypt a given message based on an offset (integer)
        Parameters: String (the message to be encrypted), int (the offset by which letters should be shifted)
        Returns: String containing the encrypted message 
        */
        // arrays of lower- and uppercase letters
        String[] lower_alph = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String[] upper_alph = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        
        // holds the encrypted message
        String new_message = "";
        // holds the index value of each new character
        int index;
        // holds the ascii value of each character in the original message
        int ascii_value; 

        for(int i = 0; i < input.length(); i++) {
            // convert the current character to its ascii value
            ascii_value = (int)input.charAt(i);

            // determine if the current character is an uppercase or lowercase letter based on its ascii value 
            if (ascii_value >= 65 && ascii_value <= 90) {
                // character is an UPPERCASE letter --> subtract the ascii value of 'A' to convert to its index in the upper_alph array
                // the modulus allows the cipher to wrap back to 'A' if the index is beyond the end of the array
                index = (ascii_value - 65 + offset) % 26;
                new_message += upper_alph[index];
            } else if (ascii_value >= 97 && ascii_value <= 122) {
                // character is a LOWERCASE letter --> subtract the ascii value of 'a' to convert to its index in the lower_alph array
                // the modulus allows the cipher to wrap back to 'a' if the index is beyond the end of the array
                index = (ascii_value - 97 + offset) % 26;
                new_message += lower_alph[index];
            } else {
                // character is not a letter
                new_message += input.charAt(i);
            }   
        }
        return new_message;
	}
}
