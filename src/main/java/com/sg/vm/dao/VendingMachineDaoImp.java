/*
DAO
 */
package com.sg.vm.dao;

import com.sg.vm.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class VendingMachineDaoImp implements VendingMachineDao {

    private List<Item> inventory = new ArrayList();
    private final String FILENAME = "Items.txt";
    
    public List<Item> getInventory() {
        return inventory;
    }
    
    @Override
    public List<Item> getAllItems() {
        return this.inventory;
    }
 
     // assigning string values to Item object
    public Item unmarshallItem(String items) {
     
        String[] split = items.split(", ");
        Item item = new Item();
        item.setItemName(split[0]);
        item.setItemCost(Integer.parseInt(split[1]));
        item.setNumOfItems(Integer.parseInt(split[2]));

        return item;
    }

    // convert Object to String
    public String marshallItem(Item item) {
        return item.getItemName()
                + ", "
                + item.getItemCost()
                + ", "
                + item.getNumOfItems();
    }

    @Override
    public void loadItems() throws VendingMachineDaoException{

        Item item;
        try {
            FileReader fReader = new FileReader(FILENAME);
            BufferedReader br = new BufferedReader(fReader);
            String line;

            while ((line = br.readLine()) != null) {
                item = unmarshallItem(line); // convert String to Item object...
                // so we can add them directly to object arraylist
                this.inventory.add(item);

            }
            fReader.close();
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoException ("File not found", e);
        } catch (IOException e) {
            throw new VendingMachineDaoException("Could not read from file", e);
        }
        
    }

    @Override
    public void writeFile() throws VendingMachineDaoException {

        try {
            FileWriter fw = new FileWriter(this.FILENAME);
            for (Item item : this.inventory) {
                fw.write(marshallItem(item) + "\n");
            }
            fw.flush(); // forces everything to be written to file
            fw.close(); // close to avoid resource leaks
        } catch (IOException e) {
            throw new VendingMachineDaoException("File not saved", e);
        }
    }
    
}
