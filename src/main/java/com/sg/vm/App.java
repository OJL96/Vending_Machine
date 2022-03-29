/*

 */
package com.sg.vm;

import com.sg.vm.controller.VendingMachineController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        
        /*

        UserIOInterface io = new UserIOUtilities();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineDaoImp dao = new VendingMachineDaoImp();
        VendingMachineServiceLayer service = new VendingMachineServiceLayer(dao);
        VendingMachine vm = new VendingMachine();
        VendingMachineController controller = 
                new VendingMachineController(view, service, vm);
        controller.run();
        */
          
        AnnotationConfigApplicationContext appContext =
                                    new AnnotationConfigApplicationContext();
        appContext.scan("com.sg.vm"); 
        appContext.refresh();
        VendingMachineController controller = appContext
        .getBean("vendingMachineController", VendingMachineController.class); 
        controller.run();
        
    }

}
