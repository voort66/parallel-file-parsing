package wvoort66.fileparser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;

public class FileProcessor {

    private Path filePath;


    public FileProcessor(Path filePath) {
        this.filePath = filePath;
    }




    public void processMultiple(int parallelCount) {
        Pipelines pipeLines = new Pipelines(filePath, parallelCount);

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(pipeLines);


    }

    public static void main(String ... args) {
        long start = System.currentTimeMillis();
        Path filePath = Paths.get("src/test/resources/bigfile.csv");

        FileProcessor fileProcessor = new FileProcessor(filePath);
        fileProcessor.processMultiple(5);

        long end = System.currentTimeMillis();
        System.out.println("Process time:" + (end-start) + " ms");

    }

}
