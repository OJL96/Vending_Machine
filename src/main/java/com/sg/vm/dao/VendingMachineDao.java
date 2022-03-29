package com.sg.vm.dao;

import java.util.List;

public interface VendingMachineDao {

    public List getAllItems();

    public void loadItems() throws VendingMachineDaoException;

    public void writeFile() throws VendingMachineDaoException;

}
