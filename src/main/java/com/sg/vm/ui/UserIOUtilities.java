/*

 */
package com.sg.vm.ui;

import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class UserIOUtilities implements UserIOInterface {
    
    final private Scanner sc = new Scanner(System.in);
    
    @Override
    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return sc.nextLine();
    }

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
    
    @Override
    public double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(this.readString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Conversion from String to double failed! ");
            }
        }
    } 
    
    @Override
    public double readDouble(String prompt, double min, double max) {
        while (true) {
            double input = readDouble(prompt);
            if (input >= min && input <= max) {
                return input;
            }
        }
    }
    
    @Override
    public int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(this.readString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Conversion from String to int failed!");
            }
        }
    }
    
    @Override
    public int readInt(String prompt, int min, int max) {
        while (true) {
            int input = readInt(prompt);
            if (input >= min && input <= max) {
                return input;
            }
        }
    }    
} // END OF CLASS
        
        
        

    

