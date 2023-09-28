package org.example.privateinvestigator.interfaces;

import java.io.IOException;
import java.util.List;
import org.example.privateinvestigator.model.Result;

/**
 * Processor interface defines the processing template
 *
 * @param <T> - input type
 * @param <R> - intermediate output
 */
public interface Processor<T, R> {

  T getInput();

  R preProcess(T input) throws IOException;

  R reformatInputData(R preprocessedOutput);

  List<Result> process(R reformattedData, Algorithm algorithm);

  void postProcess(List<Result> output);

  void triggerProcess();
}
