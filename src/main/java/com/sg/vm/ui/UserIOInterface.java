/*

 */
package com.sg.vm.ui;

import com.sg.vm.dto.VendingMachine;

public interface UserIOInterface {
  
    void print(String msg);
    double readDouble(String prompt);
    double readDouble(String prompt, double min, double max);
    int readInt(String prompt);
    int readInt(String prompt, int min, int max);
    String readString(String prompt); 

    
}
