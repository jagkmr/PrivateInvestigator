package org.example.privateinvestigator.model;

import java.util.Set;

/**
 * Result model stores the results of the algorithm applied on the input.
 *
 * <p>similarSentence - sentence which is similar between both the sentences mismathcedWords -
 * single words that are mismatch in both the sentences inputSentencePositionsWhichAreSimilar -
 * stores the input position at which the two sentences appear in the input positionInTheSentence -
 * denotes the position in the similarSentence at which the mismatched word occurs.
 */
public class Result {
  String similarSentence;
  Set<String> mismatchedWords;
  Set<Integer> inputSentencePositionsWhichAreSimilar;
  int positionInTheSentence;

  public String getSimilarSentence() {
    return similarSentence;
  }

  public void setSimilarSentence(String similarSentence) {
    this.similarSentence = similarSentence;
  }

  public Set<String> getMismatchedWords() {
    return mismatchedWords;
  }

  public void setMismatchedWords(Set<String> mismatchedWords) {
    this.mismatchedWords = mismatchedWords;
  }

  public Set<Integer> getInputSentencePositionsWhichAreSimilar() {
    return inputSentencePositionsWhichAreSimilar;
  }

  public void setInputSentencePositionsWhichAreSimilar(
      Set<Integer> inputSentencePositionsWhichAreSimilar) {
    this.inputSentencePositionsWhichAreSimilar = inputSentencePositionsWhichAreSimilar;
  }

  public int getPositionInTheSentence() {
    return positionInTheSentence;
  }

  public void setPositionInTheSentence(int positionInTheSentence) {
    this.positionInTheSentence = positionInTheSentence;
  }

  @Override
  public String toString() {
    return "Result{"
        + "similarSentence='"
        + similarSentence
        + '\''
        + ", mismatchedWords="
        + mismatchedWords
        + ", inputSentencePositionsWhichAreSimilar="
        + inputSentencePositionsWhichAreSimilar
        + ", positionInTheSentence="
        + positionInTheSentence
        + '}';
  }
}
