package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

/**
 * Implementation for vending machine audit
 * @author benat
 *
 */
@Component
public class VendingMachineAuditDaoImpl implements VendingMachineAuditDao {
	
    private final String AUDIT_FILE;

    /**
     * Constructor for implementation. Specify audit text file location
     * @param auditFile
     */
    public VendingMachineAuditDaoImpl(String auditFile) {
        this.AUDIT_FILE = auditFile;
    }

    /**
     * Constructor for implementation. Specify audit text file location
     * @param auditFile
     */
    public VendingMachineAuditDaoImpl() {
        this.AUDIT_FILE = "Audit.txt";
    }

	/**
	 * Write an entry to the .txt audit file
	 */
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
