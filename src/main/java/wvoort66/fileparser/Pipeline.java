package wvoort66.fileparser;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class Pipeline extends RecursiveTask<Long> {

    private FileReader fileReader;
    private DataTransform dataTransform;
    private FileWriter fileWriter;

    public Pipeline(FileReader fileReader, DataTransform dataTransform, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.dataTransform = dataTransform;
        this.fileWriter = fileWriter;
    }

    public void start() {
        try {
            fileReader.start();
            fileWriter.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void stop() {
        try {
            fileReader.stop();
            fileWriter.stop();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void process() {

        try {
            String line;
            while ((line = fileReader.read()) != null ) {
                Map<String, Object> valueMap = dataTransform.processLineOfData(line);
                fileWriter.write(valueMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected Long compute() {
        try {
            start();
            process();
        } finally {
            stop();
        }

        return 0L;
    }
}
