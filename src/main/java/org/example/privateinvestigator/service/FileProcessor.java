package org.example.privateinvestigator.service;

import java.io.*;
import java.util.*;
import org.example.privateinvestigator.interfaces.Algorithm;
import org.example.privateinvestigator.model.Result;
import org.example.privateinvestigator.properties.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileProcessor extends AbstractProcessor<File, List<String>> {

  private final Logger LOG = LoggerFactory.getLogger(FileProcessor.class);

  Map<String, Result> sentenceResult = new LinkedHashMap<>();

  public FileProcessor(Algorithm algorithm, List<AbstractPublisher> publishers) {
    super(algorithm, publishers);
  }

  @Override
  public File getInput() {
    File inputFile = null;
    String inputFilePath =
        System.getProperty(Constants.INPUT_FILE_PATH, Constants.DEFAULT_INPUT_FILE_PATH);

    LOG.info("Input file path is " + inputFilePath);

    inputFile = new File(inputFilePath);
    if (!inputFile.exists()) {
      throw new IllegalArgumentException("inputFile was not found!");
    }
    return inputFile;
  }

  @Override
  public List<String> preProcess(File input) throws IOException {
    return getInputSentences(input);
  }

  @Override
  public List<String> reformatInputData(List<String> preprocessedOutput) {
    List<String> reformattedData = new ArrayList<>();
    for (String s : preprocessedOutput) {
      reformattedData.add(s.substring(s.indexOf(" ", s.indexOf(" ") + 1) + 1));
    }
    return reformattedData;
  }

  public List<String> getInputSentences(File input) throws IOException {
    List<String> inputSentences = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
      String s1 = reader.readLine();
      while (s1 != null) {
        inputSentences.add(s1.trim());
        s1 = reader.readLine();
      }
    }
    return inputSentences;
  }

  @Override
  public List<Result> process(List<String> input, Algorithm algorithm) {
    LOG.info(
        "Started processing the input with the algorithm " + algorithm.getClass().getSimpleName());

    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.size(); j++) {
        if (i != j) {
          Result res = algorithm.compare(input.get(i), input.get(j));
          if (res != null) {
            processAlgorithmResult(i, j, res);
          }
        }
      }
    }

    LOG.info("Completed processing the algorithm " + algorithm.getClass().getSimpleName());

    return new ArrayList<>(sentenceResult.values());
  }

  private void processAlgorithmResult(
      int sentenceOnePosition, int sentenceTwoPosition, Result res) {
    if (Objects.nonNull(res)) {
      if (sentenceResult.get(res.getSimilarSentence()) != null) {
        Result result = sentenceResult.get(res.getSimilarSentence());

        Set<String> mismatchedWords = result.getMismatchedWords();
        mismatchedWords.addAll(res.getMismatchedWords());
        result.setMismatchedWords(mismatchedWords);

        Set<Integer> set = result.getInputSentencePositionsWhichAreSimilar();
        set.add(sentenceOnePosition);
        set.add(sentenceTwoPosition);
        result.setInputSentencePositionsWhichAreSimilar(set);

        sentenceResult.put(result.getSimilarSentence(), result);
      } else {

        HashSet<Integer> set = new HashSet();
        set.add(sentenceOnePosition);
        set.add(sentenceTwoPosition);
        res.setInputSentencePositionsWhichAreSimilar(set);

        sentenceResult.put(res.getSimilarSentence(), res);
      }
    }
  }
}
