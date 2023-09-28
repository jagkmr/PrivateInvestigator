package org.example.privateinvestigator.service;

import java.util.List;
import org.example.privateinvestigator.interfaces.Algorithm;
import org.example.privateinvestigator.interfaces.Processor;
import org.example.privateinvestigator.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This abstract class abstracts out the common functionalities and triggerProcess() function
 * provides the template with which the processing would happen.
 *
 * @param <T>
 * @param <R>
 */
public abstract class AbstractProcessor<T, R> implements Processor<T, R> {

  private final Logger LOG = LoggerFactory.getLogger(AbstractProcessor.class);

  private Algorithm algorithm;
  private List<AbstractPublisher> publishers;
  private R inputSentences;

  AbstractProcessor(Algorithm algorithm, List<AbstractPublisher> publishers) {
    this.algorithm = algorithm;
    this.publishers = publishers;
  }

  public void triggerProcess() {

    LOG.info("Triggered the processor");
    try {
      // get input and pre-process
      inputSentences = preProcess(getInput());

      R reformattedOutput = reformatInputData(inputSentences);

      // process the pre-processed input with the algorithm
      List<Result> processedResults = process(reformattedOutput, algorithm);

      // publish the result to respective streams
      postProcess(processedResults);
    } catch (Exception ex) {
      LOG.error("Exception occurred in the abstract processor " + ex.getMessage(), ex);
    }
  }

  @Override
  public void postProcess(List<Result> output) {
    for (AbstractPublisher publisher : publishers) {
      LOG.info("Start publishing the results");

      publisher.publish(inputSentences, output);

      LOG.info("published the results");
    }
    LOG.info("Completed publishing the results to all the publishers");
  }
}
