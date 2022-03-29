package com.sg.vm.controller;

import com.sg.vm.servicelayer.VendingMachineServiceLayer;
import com.sg.vm.dto.VendingMachine;
import com.sg.vm.ui.VendingMachineView;
import com.sg.vm.dao.VendingMachineDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachine vm;
    private VendingMachineServiceLayer service;
    
    @Autowired
    public VendingMachineController(VendingMachineView view,
            VendingMachineServiceLayer service, VendingMachine vm) {
        this.view = view;
        this.service = service;
        this.vm = vm;

    }

    public void run() {
        try {
            loadFromFile();
            viewEverything(false); // items display on startup
            int menuSelection;

            do {
                credit();
                menuSelection = menuSelection();
                switch (menuSelection) {
                    case 1 -> {
                        buyItem();
                        //writeToFile(); // saves every time purchase is made
                    }
                    case 2 -> {
                        addCreditToMachine();
                    }
                    case 3 -> {
                        viewEverything(true);
                    }
                    case 4 -> {
                        writeToFile();
                        exitMsg();
                    }
                    default -> {
                        invalidCmdMsg();
                    }
                }
            } while (menuSelection != 4);
        } catch (VendingMachineDaoException e) {
            e.printStackTrace();
        }
    }

    private int menuSelection() {
        return view.mainMenuAndGetSelection(vm);
    }

    private void viewEverything(boolean flag) {
        view.viewAllItems(service.getAllItems(), flag);
    }

    private void credit() {
        view.creditIn(vm.getCredit());
    }

    private void addCreditToMachine() throws VendingMachineDaoException {
        service.addingCredit(view.addCredit(vm), vm);
    }

    private void buyItem() throws VendingMachineDaoException {
        int id = view.getId(1, service.getAllItems().size());
        service.buyItem(id, vm);
        view.checkStatus(vm);
        view.displayChange(service.getChange(vm), vm);
    }

    private void writeToFile() throws VendingMachineDaoException {
        service.writeFile();
    }

    private void loadFromFile() throws VendingMachineDaoException {
        service.loadItems();
    }

    private void invalidCmdMsg() {
        view.invalidCmdMessage();
    }

    private void exitMsg() {
        view.exitMessage();
    }

}
