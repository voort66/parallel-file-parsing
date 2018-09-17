package wvoort66.fileparser;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DataTransform {

    private final Map<String, Object> headerMap = new LinkedHashMap<>();

    public Map<String, Object> processLineOfData(final String line) {
        if(headerMap.isEmpty()) { //it's the header
            List<String> headers = split(line);
            headers.stream().forEach(h -> headerMap.put(h, null));
        }

        AtomicInteger counter = new AtomicInteger(0);
        Map<String, Object> valueMap = new LinkedHashMap<>(headerMap);
        List<String>  values = split(line);
        valueMap.keySet().stream().forEach(k-> valueMap.put(k, values.get(counter.getAndIncrement())));

        return valueMap;
    }

    private List<String> split(final String line) {
        String[] parts = line.split(",");
        return new LinkedList<>(Arrays.asList(parts));
    }

}
