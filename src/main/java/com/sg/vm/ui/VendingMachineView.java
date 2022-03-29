/*


 */
package com.sg.vm.ui;

import com.sg.vm.dto.Item;
import com.sg.vm.dto.VendingMachine;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendingMachineView {

    private UserIOInterface io;
    
    @Autowired
    public VendingMachineView(UserIOInterface io) {
        this.io = io;
    }

    public int mainMenuAndGetSelection(VendingMachine vm) {
        // only displays buy item option when funds are in the vending machine
        if (vm.getCredit() > 0) {
            io.print("1 - Buy Item");
            io.print("2 - Input Credit");
            io.print("3 - View Items");
            io.print("4 - Exit");
            return io.readInt("Enter Command > ", 1, 4);
        } else {
            io.print("2 - Input Credit");
            io.print("3 - View Items");
            io.print("4 - Exit");
            return io.readInt("Enter Command > ", 2, 4);
        }
    }

    public void viewItem(Item item) {
        if (item.getNumOfItems() > 0) {
            System.out.println(item.getId());
            System.out.println(item.getItemName());
            System.out.println(item.getItemCost());
            System.out.println(item.getNumOfItems());
        } else {
            System.out.println("Item " + item.getItemName() + " not available!");
        }
    }

    public void viewAllItems(List<Item> inventory, boolean flag) {
        io.print("**** ITEMS ****");
        for (Item item : inventory) {
            if (item.getNumOfItems() <= 0) {
                continue;
            }
            String itemAttributes = String.format("#%s : %s, £%.2f, %s",
                    item.getId(),
                    item.getItemName(),
                    (double) item.getItemCost() / 100,
                    item.getNumOfItems());
            io.print(itemAttributes);

        }
        if (flag == true) {
            io.print(io.readString("Hit Enter to Continue..."));
        }
    }

    public void creditIn(double moneyIn) {
        io.print(String.format("\nCredit: £%s", moneyIn / 100));
    }

    public double addCredit(VendingMachine vm) {

        double inputCredit = io.readDouble("Input Credit <e.g. 2.50> :  ");
        if (inputCredit > 0) {
            vm.setStatus(1);
            return inputCredit;

        }
        io.print("Please enter value > 0");
        return 0;

    }

    public int getId(int min, int max) {
        return io.readInt("Select ID: ", min, max);
    }

    public void checkStatus(VendingMachine vm) {

        switch (vm.getStatus()) {
            case 1 ->
                io.print("Status: Item Successfully Purchased");
            case -1 -> {
                io.print("Status: Insufficent Funds!");
                io.print("Current Funds: £" + vm.getCredit() / 100);
            }
            case -2 ->
                io.print("Status: Item is out of stock!");
            default ->
                io.print("Error: ID Does Not Exist!"); // this never gets called

        }
    }

    public void displayChange(Map<String, Integer> change, VendingMachine vm) {
        if (change.isEmpty()) {
            return;
        }
        if (vm.getStatus() == 1) {
            io.print("**** CHANGED DEPOSITED ****");
            // lambda function instead of for each loop 
            change.entrySet()
                    .stream()
                    .map(entry -> String.format("%s : %s", entry.getKey(), entry.getValue()))
                    .forEachOrdered(layout -> {
                io.print(layout);
            });
            vm.setCredit(0);
        }
    }
    
    public void exitMessage(){
        io.print("Terminating Program. Goodbye!");
    }
    
    public void invalidCmdMessage(){
        io.print("Invalid Command!");
    }
} // END OF CLASS
