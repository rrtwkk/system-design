package org.ritwik.flipbidder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Winner strategy that selects the lowest unique bid
 * A unique bid is one that no other participant has made
 */
public class LowestUniqueBidStrategy implements WinnerStrategy {
    
    @Override
    public Optional<Bid> determineWinner(Collection<Bid> bids) {
        if (bids == null || bids.isEmpty()) {
            return Optional.empty();
        }
        
        // Group bids by amount to find unique bids
        Map<Double, List<Bid>> bidsByAmount = bids.stream()
            .collect(Collectors.groupingBy(Bid::getAmount));
        
        // Find unique bids (amounts that appear only once)
        List<Bid> uniqueBids = bidsByAmount.entrySet().stream()
            .filter(entry -> entry.getValue().size() == 1)
            .map(entry -> entry.getValue().get(0))
            .collect(Collectors.toList());
        
        if (uniqueBids.isEmpty()) {
            return Optional.empty();
        }
        
        // Return the lowest unique bid
        return uniqueBids.stream()
            .min(Comparator.comparing(Bid::getAmount));
    }
}
