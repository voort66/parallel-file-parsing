package wvoort66.fileparser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class BigFileCreator {
    private static final String HEADER = "id,security_code,occurrence,random_number, seq_number\n";

    private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ISO_DATE_TIME;

    private static final String FILE_NAME = "bigfile_";

    private SecureRandom sr = new SecureRandom();

    private Long idStart = 100L;

    private Long seqStart = 1L;




    public void createBigFile(final long linesCount, final String folderPath) {

        final Path filePath = Paths.get(folderPath, FILE_NAME + linesCount + ".csv");

        try (BufferedWriter bf = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            bf.write(HEADER);
            for (int ndx = 0; ndx < linesCount; ndx++) {
                bf.write(generateLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private String generateLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId()).append(",").append(getSecCode()).append(",").append(getOccurrence()).
                append(",").append(getRandomNumber()).append(",").append(getSeqNr()).append("\n");
        return sb.toString();
    }

    private String getId() {
        String newId = idStart.toString();
        idStart++;
        return newId;
    }

    private String getSecCode() {
        SecureRandom sr = new SecureRandom();
        String secCode = "sc_";
        return secCode + Math.abs(sr.nextLong());
    }

    private String getOccurrence() {
        LocalDateTime dt = LocalDateTime.now();
        SecureRandom sr = new SecureRandom();
        dt = dt.minus(sr.nextInt()%3, ChronoUnit.MINUTES);

        return dt.format(DT_FORMAT);

    }

    private String getRandomNumber() {
        SecureRandom sr = new SecureRandom("random".getBytes());
        return String.valueOf(Math.abs(sr.nextLong()));
    }

    private String getSeqNr() {
        String newSeqNr = seqStart.toString();
        seqStart++;
        return newSeqNr;
    }


}
