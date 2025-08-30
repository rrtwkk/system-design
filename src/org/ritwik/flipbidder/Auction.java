package org.ritwik.flipbidder;

import java.util.*;

/**
 * Represents an auction with bid limits and participation cost
 */
public class Auction {
    private final String id;
    private final double lowestBidLimit;
    private final double highestBidLimit;
    private final double participationCost;
    private final User seller;
    private final Map<User, Bid> bids; // One bid per user
    private boolean isOpen;
    
    public Auction(String id, double lowestBidLimit, double highestBidLimit, 
                   double participationCost, User seller) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Auction ID cannot be null or empty");
        }
        if (lowestBidLimit < 0 || highestBidLimit < 0) {
            throw new IllegalArgumentException("Bid limits cannot be negative");
        }
        if (lowestBidLimit > highestBidLimit) {
            throw new IllegalArgumentException("Lowest bid limit cannot be greater than highest bid limit");
        }
        if (participationCost < 0) {
            throw new IllegalArgumentException("Participation cost cannot be negative");
        }
        if (seller == null) {
            throw new IllegalArgumentException("Seller cannot be null");
        }
        
        this.id = id.trim();
        this.lowestBidLimit = lowestBidLimit;
        this.highestBidLimit = highestBidLimit;
        this.participationCost = participationCost;
        this.seller = seller;
        this.bids = new HashMap<>();
        this.isOpen = true;
    }
    
    public String getId() {
        return id;
    }
    
    public double getLowestBidLimit() {
        return lowestBidLimit;
    }
    
    public double getHighestBidLimit() {
        return highestBidLimit;
    }
    
    public double getParticipationCost() {
        return participationCost;
    }
    
    public User getSeller() {
        return seller;
    }
    
    public boolean isOpen() {
        return isOpen;
    }
    
    public void closeAuction() {
        this.isOpen = false;
    }
    
    public Collection<Bid> getBids() {
        return new ArrayList<>(bids.values());
    }
    
    public boolean hasBid(User buyer) {
        return bids.containsKey(buyer);
    }
    
    public Bid getBid(User buyer) {
        return bids.get(buyer);
    }
    
    public void addOrUpdateBid(User buyer, double amount) {
        if (!isOpen) {
            throw new IllegalStateException("Cannot bid on a closed auction");
        }
        if (amount < lowestBidLimit || amount > highestBidLimit) {
            throw new IllegalArgumentException(
                "Bid amount must be between " + lowestBidLimit + " and " + highestBidLimit);
        }
        
        if (bids.containsKey(buyer)) {
            // Update existing bid
            bids.get(buyer).updateAmount(amount);
        } else {
            // Create new bid
            Bid newBid = new Bid(buyer, id, amount);
            bids.put(buyer, newBid);
        }
    }
    
    public boolean withdrawBid(User buyer) {
        if (!isOpen) {
            throw new IllegalStateException("Cannot withdraw bid from a closed auction");
        }
        return bids.remove(buyer) != null;
    }
    
    @Override
    public String toString() {
        return "Auction{id='" + id + "', bidLimits=[" + lowestBidLimit + ", " + highestBidLimit + 
               "], participationCost=" + participationCost + ", seller=" + seller.getName() + 
               ", bidsCount=" + bids.size() + ", isOpen=" + isOpen + "}";
    }
}
