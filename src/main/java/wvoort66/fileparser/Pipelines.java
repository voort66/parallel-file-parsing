package wvoort66.fileparser;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class Pipelines extends RecursiveAction {

    private Path filePath;

    private int nrOfPipelines;

    public Pipelines(final Path path, int nrOfPipelines) {
        this.filePath = path;
        this.nrOfPipelines = nrOfPipelines;
    }

    @Override
    protected void compute() {
        ForkJoinTask.invokeAll(createSubTasks());
    }


    public List<Pipeline> createSubTasks() {

        List<Pipeline> pipeLines = new ArrayList<>();
        FileDivider fileDivider = new FileDivider(filePath, nrOfPipelines);


        try {
            List<FileSection> fileSections = fileDivider.getFileSections();
            fileSections.stream().forEach(fs -> {
                FileReader fileReader = new FileReader(fs);
                DataTransform dt = new DataTransform();
                Path outputPath = createOutputPath(filePath);
                FileWriter fileWriter = new FileWriter(outputPath);
                pipeLines.add(new Pipeline(fileReader, dt, fileWriter));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pipeLines;
    }

    private Path createOutputPath(Path path) {
        SecureRandom sr = new SecureRandom();

        String newPath = path.toString() + "_" + sr.nextInt(100);
        return Paths.get(newPath);
    }
}
