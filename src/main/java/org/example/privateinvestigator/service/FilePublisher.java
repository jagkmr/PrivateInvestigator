package org.example.privateinvestigator.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.example.privateinvestigator.model.Result;
import org.example.privateinvestigator.properties.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilePublisher extends AbstractPublisher<List<String>, List<Result>> {

  private final Logger LOG = LoggerFactory.getLogger(FilePublisher.class);

  @Override
  public boolean publish(List<Result> output) {

    String outputFilePath =
        System.getProperty(Constants.OUTPUT_FILE_PATH, Constants.DEFAULT_OUTPUT_FILE_PATH);
    File outputFile = new File(outputFilePath);
    if (!outputFile.exists()) {
      try {
        outputFile.createNewFile();
      } catch (IOException e) {
        LOG.error("Exception occurred - output file was not created", e);
        throw new RuntimeException("output file was not created", e);
      }
    }
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
      List<String> inputData = getInputData();

      for (Result result : output) {
        for (int i : result.getInputSentencePositionsWhichAreSimilar()) {
          writer.write(inputData.get(i));
          writer.newLine();
        }
        writer.write("The changing word was: " + String.join(", ", result.getMismatchedWords()));
        writer.newLine();
      }
    } catch (IOException e) {
      LOG.error("Exception occurred while writing output to file", e);
      throw new RuntimeException("Exception occurred while writiing output to file", e);
    }

    LOG.info("Completed publishing the results to " + outputFilePath);
    return true;
  }
}
