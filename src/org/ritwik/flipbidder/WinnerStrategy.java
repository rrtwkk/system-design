package org.ritwik.flipbidder;

import java.util.Collection;
import java.util.Optional;

/**
 * Strategy interface for determining the winner of an auction
 */
public interface WinnerStrategy {
    /**
     * Determines the winner from a collection of bids
     * @param bids Collection of bids in the auction
     * @return Optional containing the winning bid, or empty if no winner
     */
    Optional<Bid> determineWinner(Collection<Bid> bids);
}
