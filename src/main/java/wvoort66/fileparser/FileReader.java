package wvoort66.fileparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

public class FileReader {
    private FileSection fileSection;

    private BufferedReader bufferedReader;

    private int linesRead;

    private int bytesRead;

    public FileReader(FileSection fileSection) {
        this.fileSection = fileSection;

    }

    public void start() throws IOException {
        bufferedReader = Files.newBufferedReader(fileSection.getFilePath());
        bufferedReader.skip(fileSection.getOffset());

    }

    public void stop() throws IOException {
        bufferedReader.close();
    }


    public String read() throws IOException {
        if (bytesRead >= fileSection.getBytesToRead()){
            return null;
        }

        linesRead ++;
        String line =  bufferedReader.readLine();
        if (line != null) {
            bytesRead += line.getBytes().length;
        }
        return line;

    }




}
