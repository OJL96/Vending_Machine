/*
DTO
 */
package com.sg.vm.dto;;


import org.springframework.stereotype.Component;


@Component
public class VendingMachine {
    
    private Item item; // composition
    private double credit = 0;
    private int status = 0;
    
    public VendingMachine(Item item, double credit, int change) {
        this.item = item;
        this.credit = credit;
    }

    public VendingMachine() {
    }

    public Item getItem() {
        return item;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
