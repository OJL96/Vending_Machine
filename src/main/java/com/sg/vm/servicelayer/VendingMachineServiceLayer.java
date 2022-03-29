
package com.sg.vm.servicelayer;

import com.sg.vm.dao.LoggingEvents;
import com.sg.vm.dao.VendingMachineDaoException;
import com.sg.vm.dao.VendingMachineDaoImp;
import com.sg.vm.dto.Item;
import com.sg.vm.dto.VendingMachine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendingMachineServiceLayer implements VendingMachineServiceLayerInterface {
    
    private VendingMachineDaoImp dao;
    private LoggingEvents logger;
    public Map<String, Integer> changeDue;
    
    @Autowired
    public VendingMachineServiceLayer(VendingMachineDaoImp dao, LoggingEvents logger) {
        this.dao = dao;
        this.logger = logger;
    }

    @Override
    public void writeFile() throws VendingMachineDaoException {
        dao.writeFile();
        this.logger.log("Inventory saved");
    }

    @Override
    public void loadItems() throws VendingMachineDaoException {
        dao.loadItems();
        this.logger.log("Inventory loaded");

    }
    
    public enum Change {
        P(1),
        FIVEP(5),
        TENP(10),
        TWENTYP(20),
        FIFTYP(50),
        ONEPOUND(100),
        TWOPOUND(200);
        private int value;

        private Change(int value) {
            this.value = value;
        }
    }
  
    @Override
    public Map<String, Integer> getChange(VendingMachine vm) throws VendingMachineDaoException {
        changeDue = new HashMap<String, Integer>();
        changeDue.put(Change.TWOPOUND.name(), 0);
        changeDue.put(Change.ONEPOUND.name(), 0);
        changeDue.put(Change.FIFTYP.name(), 0);
        changeDue.put(Change.TWENTYP.name(), 0);
        changeDue.put(Change.TENP.name(), 0);
        changeDue.put(Change.FIVEP.name(), 0);
        changeDue.put(Change.P.name(), 0);

        int newCreditValue = (int) vm.getCredit();
        
        // not the best way to cal change but it works
        while (newCreditValue > 0) {
            if (newCreditValue >= Change.TWOPOUND.value) {
                newCreditValue -= Change.TWOPOUND.value;
                changeDue.computeIfPresent(Change.TWOPOUND.name(), (k, v) -> ++v);
            } else if (newCreditValue >= Change.ONEPOUND.value) {
                newCreditValue -= Change.ONEPOUND.value;
                changeDue.computeIfPresent(Change.ONEPOUND.name(), (k, v) -> ++v);
            } else if (newCreditValue >= Change.FIFTYP.value) {
                newCreditValue -= Change.FIFTYP.value;
                changeDue.computeIfPresent(Change.FIFTYP.name(), (k, v) -> ++v);
            } else if (newCreditValue >= Change.TWENTYP.value) {
                newCreditValue -= Change.TWENTYP.value;
                changeDue.computeIfPresent(Change.TWENTYP.name(), (k, v) -> ++v);
            } else if (newCreditValue >= Change.TENP.value) {
                newCreditValue -= Change.TENP.value;
                changeDue.computeIfPresent(Change.TENP.name(), (k, v) -> ++v);
            } else if (newCreditValue >= Change.FIVEP.value) {
                newCreditValue -= Change.FIVEP.value;
                changeDue.computeIfPresent(Change.FIVEP.name(), (k, v) -> ++v);
            } else if (newCreditValue >= Change.P.value) {
                newCreditValue -= Change.P.value;
                changeDue.computeIfPresent(Change.P.name(), (k, v) -> ++v);
            }
        }
        
        // filters return to only include entry set that have a value >= 1
        Map<String, Integer> filteredChange = changeDue.entrySet()
                .stream()
                .filter(x -> x.getValue() >= 1)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        
        if (!(filteredChange.isEmpty())) {
            this.logger.log("Change Returned: " + filteredChange.entrySet());
        } else {
             this.logger.log("Change Returned: NONE");
        }
        
        return filteredChange;
    }

    @Override
    public void buyItem(int id, VendingMachine vm) throws VendingMachineDaoException {
          for (Item item : dao.getInventory()) {
            if (item.getId() == id) {
                if (item.getNumOfItems() > 0) {
                    if (vm.getCredit() >= item.getItemCost()) {
                        // decrement quantity of item by 1
                        item.setNumOfItems(item.getNumOfItems() - 1);
                        this.logger.log(item.getItemName() + " purchased");
                        // alter remaining credits accordingly 
                        vm.setCredit(vm.getCredit() - (item.getItemCost()));
                        vm.setStatus(1); // magic number
                        return;
                    } else {
                        // insufficent funds status
                        vm.setStatus(-1); // magic number  
                        return;
                    }
                } else {
                    // item is out of stock status
                    vm.setStatus(-2); // magic number
                    return;
                }
            }  
        }
    }

    @Override
    public List getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public double addingCredit(double amount, VendingMachine vm) throws VendingMachineDaoException {
        vm.setCredit(vm.getCredit() + (amount * 100));
        this.logger.log("£" + Double.toString(amount) + " credits added");
        return vm.getCredit();
    }
    
    
    
}
