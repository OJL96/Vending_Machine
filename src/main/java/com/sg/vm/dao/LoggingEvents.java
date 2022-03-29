package com.sg.vm.dao;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class LoggingEvents {
    private LocalDateTime time;
    private final DateTimeFormatter myFormatObj =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final String LOGGERFILE = "logger.txt";
    
        // records events. helps with debugging
    public void log(String message) throws VendingMachineDaoException {
        try {
            FileWriter fw = new FileWriter(this.LOGGERFILE, true);
            this.time = LocalDateTime.now();
            String formatTime = this.time.format(this.myFormatObj);
            fw.write(formatTime + " : " + message + "\n");
            fw.close();

        } catch (FileNotFoundException e) {
             throw new VendingMachineDaoException ("File not found", e);
        } catch (IOException e) {
             throw new VendingMachineDaoException ("Could not append file", e);
        }

    }
    
}
