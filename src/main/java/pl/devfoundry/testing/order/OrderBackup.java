package pl.devfoundry.testing.order;

import org.junit.jupiter.api.function.Executable;
import pl.devfoundry.testing.order.Order;

import java.io.*;

public class OrderBackup {
    private Writer writer;

    public Writer getWriter(){
        return writer;
    }

    void createFile () throws FileNotFoundException {
        File file = new File("orderBackup.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        writer = new BufferedWriter(outputStreamWriter);
    }

    Executable backuporder(Order order) throws IOException {

        if(writer == null){
            throw new IOException("Backup file not created");
        }
        writer.append(order.toString());
        return null;
    }

    void closeFile() throws IOException {
        writer.close();
    }
}
