package com.manning.siia.trip.diary;

import org.springframework.integration.Message;
import org.springframework.integration.file.FileNameGenerator;

/**
 * @author Iwein Fuld
 */
public class ChangeFileNameGenerator implements FileNameGenerator {

    private final String processId;

    public ChangeFileNameGenerator(String processId) {
        this.processId = processId;
    }

    public String generateFileName(Message<?> message) {
        return Long.toString(System.currentTimeMillis()) + "_" + processId;
    }

}
