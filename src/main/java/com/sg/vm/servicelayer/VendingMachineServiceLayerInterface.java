/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.vm.servicelayer;

import com.sg.vm.dao.VendingMachineDaoException;
import com.sg.vm.dto.VendingMachine;
import java.util.List;
import java.util.Map;

/**
 *
 * @author OmarL
 */
public interface VendingMachineServiceLayerInterface {
    
    
    public Map<String, Integer> getChange(VendingMachine vm) throws VendingMachineDaoException;

    public void buyItem(int id, VendingMachine vm) throws VendingMachineDaoException;

    public List getAllItems();

    public double addingCredit(double amount, VendingMachine vm) throws VendingMachineDaoException;
    
    public void writeFile() throws VendingMachineDaoException;
    
    public void loadItems() throws VendingMachineDaoException;
    
}
