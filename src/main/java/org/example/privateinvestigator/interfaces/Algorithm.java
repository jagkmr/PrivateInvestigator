package org.example.privateinvestigator.interfaces;

import org.example.privateinvestigator.model.Result;

/** Interface to implement multiple algorithms */
public interface Algorithm {
  Result compare(String sentence1, String sentence2);
}
