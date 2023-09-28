package org.example.privateinvestigator.interfaces;

/**
 * Interface to publish the results to different types of publishers
 *
 * @param <R>
 */
public interface Publisher<R> {
  boolean publish(R output);
}
