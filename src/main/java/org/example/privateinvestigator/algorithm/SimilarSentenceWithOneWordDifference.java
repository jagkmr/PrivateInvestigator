package org.example.privateinvestigator.algorithm;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.example.privateinvestigator.interfaces.Algorithm;
import org.example.privateinvestigator.model.Result;

/**
 * SimilarSentenceWithOneWordDifference this algorithm uses StringUtils.java class from
 * commons-lang3 library to find the difference between two sentences
 */
public class SimilarSentenceWithOneWordDifference implements Algorithm {

  @Override
  public Result compare(String sentence1, String sentence2) {

    // check for null and lengths of sentences
    if (Objects.nonNull(sentence1)
        && Objects.nonNull(sentence2)
        && (sentence1.split(" ").length == sentence2.split(" ").length)) {
      int firstDiffPosition = StringUtils.indexOfDifference(sentence1, sentence2);
      if (firstDiffPosition == -1) {
        return null;
      }

      // Ensure only one word difference is found, if any other difference is found then its not
      // similar sentence
      if ((sentence1.indexOf(" ", firstDiffPosition) != -1)
          && (sentence2.indexOf(" ", firstDiffPosition) != -1)) {
        int i =
            StringUtils.indexOfDifference(
                sentence1.substring(sentence1.indexOf(" ", firstDiffPosition)),
                sentence2.substring(sentence2.indexOf(" ", firstDiffPosition)));
        if (i != -1) return null;
      }

      // Re-position the startPosition and endPosition of both the sentences to handle multiple
      // use-cases
      int s1StartPosition = sentence1.lastIndexOf(" ", firstDiffPosition);
      if (s1StartPosition == -1) s1StartPosition = 0;
      int s1EndPosition = sentence1.indexOf(" ", firstDiffPosition);
      if (s1EndPosition == -1) s1EndPosition = sentence1.length();
      int s2StartPosition = sentence2.lastIndexOf(" ", firstDiffPosition);
      if (s2StartPosition == -1) s2StartPosition = 0;
      int s2EndPosition = sentence2.indexOf(" ", firstDiffPosition);
      if (s2EndPosition == -1) s2EndPosition = sentence2.length();

      String lhs = "";
      if (s1StartPosition != 0) {
        lhs = sentence1.substring(0, s1StartPosition) + " ";
      }
      // this is the similar sentence in both sentence excluding the one word difference
      String similarSentence = lhs + sentence1.substring(s1EndPosition).trim();

      // These are mismatched single words in both the sentences
      String mismatchedWord1 = sentence1.substring(s1StartPosition, s1EndPosition).trim();
      String mismatchedWord2 = sentence2.substring(s2StartPosition, s2EndPosition).trim();

      Set<String> mismatchedWords = new LinkedHashSet<>();
      mismatchedWords.add(mismatchedWord1.trim());
      mismatchedWords.add(mismatchedWord2.trim());

      // set the results to Result model
      Result result = new Result();
      result.setSimilarSentence(similarSentence);
      result.setMismatchedWords(mismatchedWords);
      result.setPositionInTheSentence(s1StartPosition);
      return result;
    }
    return null;
  }
}
