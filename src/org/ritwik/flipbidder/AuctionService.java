package org.ritwik.flipbidder;

import java.util.*;

/**
 * Main service class for managing the auction system
 * Provides all the required functionalities for the FlipBidder system
 */
public class AuctionService {
    private final Map<String, User> buyers;
    private final Map<String, User> sellers;
    private final Map<String, Auction> auctions;
    private WinnerStrategy winnerStrategy;
    
    public AuctionService() {
        this.buyers = new HashMap<>();
        this.sellers = new HashMap<>();
        this.auctions = new HashMap<>();
        this.winnerStrategy = new HighestUniqueBidStrategy(); // Default strategy
    }
    
    public AuctionService(WinnerStrategy winnerStrategy) {
        this();
        this.winnerStrategy = winnerStrategy;
    }
    
    public void setWinnerStrategy(WinnerStrategy winnerStrategy) {
        if (winnerStrategy == null) {
            throw new IllegalArgumentException("Winner strategy cannot be null");
        }
        this.winnerStrategy = winnerStrategy;
    }
    
    /**
     * Add a buyer to the system
     */
    public void addBuyer(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Buyer name cannot be null or empty");
        }
        
        User buyer = new User(name);
        buyers.put(buyer.getName(), buyer);
    }
    
    /**
     * Add a seller to the system
     */
    public void addSeller(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Seller name cannot be null or empty");
        }
        
        User seller = new User(name);
        sellers.put(seller.getName(), seller);
    }
    
    /**
     * Create a new auction
     */
    public void createAuction(String auctionId, String lowestBid, String highestBid, 
                             String participationCost, String sellerName) {
        try {
            double lowest = Double.parseDouble(lowestBid);
            double highest = Double.parseDouble(highestBid);
            double cost = Double.parseDouble(participationCost);
            
            User seller = sellers.get(sellerName);
            if (seller == null) {
                throw new IllegalArgumentException("Seller " + sellerName + " not found");
            }
            
            if (auctions.containsKey(auctionId)) {
                throw new IllegalArgumentException("Auction with ID " + auctionId + " already exists");
            }
            
            Auction auction = new Auction(auctionId, lowest, highest, cost, seller);
            auctions.put(auctionId, auction);
            
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric values provided");
        }
    }
    
    /**
     * Create or update a bid
     */
    public void createBid(String buyerName, String auctionId, String amount) {
        try {
            double bidAmount = Double.parseDouble(amount);
            
            User buyer = buyers.get(buyerName);
            if (buyer == null) {
                throw new IllegalArgumentException("Buyer " + buyerName + " not found");
            }
            
            Auction auction = auctions.get(auctionId);
            if (auction == null) {
                throw new IllegalArgumentException("Auction " + auctionId + " not found");
            }
            
            auction.addOrUpdateBid(buyer, bidAmount);
            
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid bid amount: " + amount);
        }
    }
    
    /**
     * Update an existing bid
     */
    public void updateBid(String buyerName, String auctionId, String newAmount) {
        // Same logic as createBid - the Auction class handles create vs update internally
        createBid(buyerName, auctionId, newAmount);
    }
    
    /**
     * Withdraw a bid from an auction
     */
    public void withdrawBid(String buyerName, String auctionId) {
        User buyer = buyers.get(buyerName);
        if (buyer == null) {
            throw new IllegalArgumentException("Buyer " + buyerName + " not found");
        }
        
        Auction auction = auctions.get(auctionId);
        if (auction == null) {
            throw new IllegalArgumentException("Auction " + auctionId + " not found");
        }
        
        boolean removed = auction.withdrawBid(buyer);
        if (!removed) {
            throw new IllegalArgumentException("No bid found for buyer " + buyerName + " in auction " + auctionId);
        }
    }
    
    /**
     * Close an auction and return the winner information
     */
    public String closeAuction(String auctionId) {
        Auction auction = auctions.get(auctionId);
        if (auction == null) {
            throw new IllegalArgumentException("Auction " + auctionId + " not found");
        }
        
        if (!auction.isOpen()) {
            throw new IllegalStateException("Auction " + auctionId + " is already closed");
        }
        
        auction.closeAuction();
        
        Optional<Bid> winningBid = winnerStrategy.determineWinner(auction.getBids());
        
        if (winningBid.isPresent()) {
            Bid winner = winningBid.get();
            return winner.getBuyer().getName() + " wins with bid " + winner.getAmount();
        } else {
            return "No winner for auction " + auctionId;
        }
    }
    
    /**
     * Get auction details for debugging/testing
     */
    public Auction getAuction(String auctionId) {
        return auctions.get(auctionId);
    }
    
    /**
     * Get all buyers for debugging/testing
     */
    public Collection<User> getBuyers() {
        return new ArrayList<>(buyers.values());
    }
    
    /**
     * Get all sellers for debugging/testing
     */
    public Collection<User> getSellers() {
        return new ArrayList<>(sellers.values());
    }
}
