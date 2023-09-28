package org.example.privateinvestigator.service;

import org.example.privateinvestigator.interfaces.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPublisher<T, R> implements Publisher<R> {

  private final Logger LOG = LoggerFactory.getLogger(AbstractPublisher.class);
  private T inputData;

  public boolean publish(T inputData, R output) {
    try {

      this.inputData = inputData;

      publish(output);

      return true;
    } catch (Exception e) {
      LOG.error("Exception occurred while publishing the results to one of the publisher", e);
      return false;
    }
  }

  public T getInputData() {
    return inputData;
  }
}
