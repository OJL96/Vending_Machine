package com.sg.vm.servicelayer;
import com.sg.vm.dao.VendingMachineDaoException;
import com.sg.vm.dto.VendingMachine;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class VendingMachineServiceLayerTest {

    public VendingMachineServiceLayerTest() {
    }

    @Test
    public void testChangeOnePound() throws VendingMachineDaoException {

        AnnotationConfigApplicationContext appContext
                = new AnnotationConfigApplicationContext();
        appContext.scan("com.sg.vm");
        appContext.refresh();

        VendingMachine vm = appContext
                .getBean("vendingMachine", VendingMachine.class);

        VendingMachineServiceLayer service = appContext
                .getBean("vendingMachineServiceLayer", VendingMachineServiceLayer.class);
        
        double credit = 100;
        vm.setCredit(credit);

        Map<String, Integer> changeList = service.getChange(vm);

        Map<String, Integer> correctChangeList = new HashMap();
        correctChangeList.put(VendingMachineServiceLayer.Change.ONEPOUND.name(), 1);

        assertEquals(changeList, correctChangeList);

    }

    @Test
    public void testChangeZero() throws VendingMachineDaoException {
        AnnotationConfigApplicationContext appContext
                = new AnnotationConfigApplicationContext();
        appContext.scan("com.sg.vm");
        appContext.refresh();

        VendingMachine vm = appContext
                .getBean("vendingMachine", VendingMachine.class);

        VendingMachineServiceLayer service = appContext
                .getBean("vendingMachineServiceLayer", VendingMachineServiceLayer.class);

        double credit = 0;
        vm.setCredit(credit);

        Map<String, Integer> changeList = service.getChange(vm);

        Map<String, Integer> correctChangeList = new HashMap();

        assertEquals(changeList, correctChangeList);

    }

    @Test
    public void testChangeDouble() throws VendingMachineDaoException {
        AnnotationConfigApplicationContext appContext
                = new AnnotationConfigApplicationContext();
        appContext.scan("com.sg.vm");
        appContext.refresh();

        VendingMachine vm = appContext
                .getBean("vendingMachine", VendingMachine.class);

        VendingMachineServiceLayer service = appContext
                .getBean("vendingMachineServiceLayer", VendingMachineServiceLayer.class);

        double credit = 173;
        vm.setCredit(credit);

        Map<String, Integer> changeList = service.getChange(vm);
        Map<String, Integer> correctChangeList = new HashMap();

        correctChangeList.put(VendingMachineServiceLayer.Change.ONEPOUND.name(), 1);
        correctChangeList.put(VendingMachineServiceLayer.Change.FIFTYP.name(), 1);
        correctChangeList.put(VendingMachineServiceLayer.Change.TWENTYP.name(), 1);
        correctChangeList.put(VendingMachineServiceLayer.Change.P.name(), 3);

        assertEquals(changeList, correctChangeList);

    }

    @Test
    public void testChangenegative() throws VendingMachineDaoException {
        AnnotationConfigApplicationContext appContext
                = new AnnotationConfigApplicationContext();
        appContext.scan("com.sg.vm");
        appContext.refresh();

        VendingMachine vm = appContext
                .getBean("vendingMachine", VendingMachine.class);

        VendingMachineServiceLayer service = appContext
                .getBean("vendingMachineServiceLayer", VendingMachineServiceLayer.class);

        double credit = -100;
        vm.setCredit(credit);

        Map<String, Integer> changeList = service.getChange(vm);
        Map<String, Integer> correctChangeList = new HashMap();

        assertEquals(changeList, correctChangeList);
    }

}
