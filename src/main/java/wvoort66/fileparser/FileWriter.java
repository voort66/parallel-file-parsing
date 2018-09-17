package wvoort66.fileparser;

import com.sun.deploy.util.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class FileWriter {

    private Path filePath;

    private BufferedWriter writer;

    public FileWriter(Path filePath) {
        this.filePath = filePath;
    }


    public void start() throws IOException {
        writer =  Files.newBufferedWriter(filePath, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
    }

    public void stop() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }

    public void write(Map<String, Object> valueMap) throws IOException {
        String writableLine =  StringUtils.join(valueMap.values(), ",");
        writer.write(writableLine);
        writer.write("\n");
    }

}
