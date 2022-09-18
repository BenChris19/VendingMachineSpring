package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class VendingMachineAuditDaoImpl implements VendingMachineAuditDao {
	
    private final String AUDIT_FILE;
    //Default constructor
    //Contractor for testing
    public VendingMachineAuditDaoImpl(String auditFile) {
        this.AUDIT_FILE = auditFile;
    }
    public VendingMachineAuditDaoImpl() {
        this.AUDIT_FILE = "Audit.txt";
    }


    @SuppressWarnings("resource")
	@Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not persist audit information", e);
        }
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " +entry);
        out.flush();
    }

}
