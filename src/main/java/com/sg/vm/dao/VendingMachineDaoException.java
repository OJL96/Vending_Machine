
package com.sg.vm.dao;

/**
 *
 * @author OmarL
 */
public class VendingMachineDaoException extends Exception {
    
    // catch error that isnt caused by anohter Exception e.g. validation
    public VendingMachineDaoException(String message) { 
        super(message);
    }
    
    // catch error that is caused by another excpetion
    public VendingMachineDaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
