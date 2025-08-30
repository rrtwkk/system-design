package org.ritwik.flipbidder;

/**
 * Represents a bid made by a user in an auction
 */
public class Bid {
    private final User buyer;
    private final String auctionId;
    private double amount;
    
    public Bid(User buyer, String auctionId, double amount) {
        if (buyer == null) {
            throw new IllegalArgumentException("Buyer cannot be null");
        }
        if (auctionId == null || auctionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Auction ID cannot be null or empty");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Bid amount must be positive");
        }
        
        this.buyer = buyer;
        this.auctionId = auctionId.trim();
        this.amount = amount;
    }
    
    public User getBuyer() {
        return buyer;
    }
    
    public String getAuctionId() {
        return auctionId;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void updateAmount(double newAmount) {
        if (newAmount <= 0) {
            throw new IllegalArgumentException("Bid amount must be positive");
        }
        this.amount = newAmount;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bid bid = (Bid) obj;
        return buyer.equals(bid.buyer) && auctionId.equals(bid.auctionId);
    }
    
    @Override
    public int hashCode() {
        return buyer.hashCode() * 31 + auctionId.hashCode();
    }
    
    @Override
    public String toString() {
        return "Bid{buyer=" + buyer.getName() + ", auctionId='" + auctionId + "', amount=" + amount + "}";
    }
}
