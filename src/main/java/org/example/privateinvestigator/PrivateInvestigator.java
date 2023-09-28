package org.example.privateinvestigator;

import java.util.ArrayList;
import java.util.List;
import org.example.privateinvestigator.algorithm.SimilarSentenceWithOneWordDifference;
import org.example.privateinvestigator.interfaces.Algorithm;
import org.example.privateinvestigator.interfaces.Processor;
import org.example.privateinvestigator.service.AbstractPublisher;
import org.example.privateinvestigator.service.FileProcessor;
import org.example.privateinvestigator.service.FilePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrivateInvestigator {

  private static Logger LOG = LoggerFactory.getLogger(PrivateInvestigator.class);

  public static void main(String[] args) {

    LOG.info("Started private investigator app");
    List<AbstractPublisher> publishers = new ArrayList<>();
    publishers.add(new FilePublisher());

    Algorithm algorithm = new SimilarSentenceWithOneWordDifference();
    Processor processor = new FileProcessor(algorithm, publishers);
    processor.triggerProcess();
    LOG.info("Completed executing private investigator app");
  }
}
