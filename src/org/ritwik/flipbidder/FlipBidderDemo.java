package org.ritwik.flipbidder;

/**
 * Demo class showcasing the FlipBidder auction system
 * Includes the test cases mentioned in the requirements
 */
public class FlipBidderDemo {
    
    public static void main(String[] args) {
        System.out.println("=== FlipBidder Auction System Demo ===\n");
        
        // Test Case 1: Highest Unique Bid Strategy
        testCase1();
        
        // Test Case 2: Edge cases and validation
        testCase2();
        
        // Test Case 3: Demonstrate Lowest Unique Bid Strategy
        testCase3();
        
        // Test Case 4: Complex scenario with multiple participants
        testCase4();
    }
    
    private static void testCase1() {
        System.out.println("--- Test Case 1: Basic Auction with Highest Unique Bid ---");
        
        AuctionService service = new AuctionService();
        
        try {
            // Add users
            service.addBuyer("buyer1");
            service.addBuyer("buyer2");
            service.addBuyer("buyer3");
            service.addSeller("seller1");
            
            // Create auction
            service.createAuction("A1", "10", "50", "1", "seller1");
            
            // Create bids
            service.createBid("buyer1", "A1", "17");
            service.createBid("buyer2", "A1", "15");
            service.updateBid("buyer2", "A1", "19");  // Update buyer2's bid
            service.createBid("buyer3", "A1", "19");  // Same amount as buyer2
            
            // Close auction and get winner
            String result = service.closeAuction("A1");
            System.out.println("Result: " + result);
            System.out.println("Expected: buyer1 wins with bid 17.0 (highest unique bid)\n");
            
        } catch (Exception e) {
            System.out.println("Error in Test Case 1: " + e.getMessage() + "\n");
        }
    }
    
    private static void testCase2() {
        System.out.println("--- Test Case 2: Edge Cases and Validation ---");
        
        AuctionService service = new AuctionService();
        
        try {
            // Add users
            service.addBuyer("buyer2");
            service.addBuyer("buyer3");
            service.addSeller("seller2");
            
            // Create auction
            service.createAuction("A2", "5", "20", "2", "seller2");
            
            // Try to bid above limit (should fail)
            try {
                service.createBid("buyer3", "A2", "25");
                System.out.println("ERROR: Should have failed for bid above limit");
            } catch (Exception e) {
                System.out.println("✓ Correctly rejected bid above limit: " + e.getMessage());
            }
            
            // Valid bid
            service.createBid("buyer2", "A2", "5");
            
            // Withdraw bid
            service.withdrawBid("buyer2", "A2");
            
            // Close auction (should have no winner)
            String result = service.closeAuction("A2");
            System.out.println("Result: " + result);
            System.out.println("Expected: No winner for auction A2\n");
            
        } catch (Exception e) {
            System.out.println("Error in Test Case 2: " + e.getMessage() + "\n");
        }
    }
    
    private static void testCase3() {
        System.out.println("--- Test Case 3: Lowest Unique Bid Strategy ---");
        
        AuctionService service = new AuctionService(new LowestUniqueBidStrategy());
        
        try {
            // Add users
            service.addBuyer("buyer1");
            service.addBuyer("buyer2");
            service.addBuyer("buyer3");
            service.addBuyer("buyer4");
            service.addSeller("seller3");
            
            // Create auction
            service.createAuction("A3", "10", "100", "5", "seller3");
            
            // Create bids
            service.createBid("buyer1", "A3", "25");  // Unique
            service.createBid("buyer2", "A3", "30");  // Unique
            service.createBid("buyer3", "A3", "25");  // Same as buyer1, now both are not unique
            service.createBid("buyer4", "A3", "35");  // Unique
            
            // Close auction
            String result = service.closeAuction("A3");
            System.out.println("Result: " + result);
            System.out.println("Expected: buyer2 wins with bid 30.0 (lowest unique bid)\n");
            
        } catch (Exception e) {
            System.out.println("Error in Test Case 3: " + e.getMessage() + "\n");
        }
    }
    
    private static void testCase4() {
        System.out.println("--- Test Case 4: Complex Scenario ---");
        
        AuctionService service = new AuctionService();
        
        try {
            // Add multiple users
            service.addBuyer("alice");
            service.addBuyer("bob");
            service.addBuyer("charlie");
            service.addBuyer("diana");
            service.addBuyer("eve");
            service.addSeller("seller4");
            
            // Create auction
            service.createAuction("A4", "20", "200", "10", "seller4");
            
            // Create multiple bids
            service.createBid("alice", "A4", "50");     // Unique
            service.createBid("bob", "A4", "90");       // Not unique (same as diana)
            service.createBid("charlie", "A4", "100");  // Not unique (same as eve)
            service.createBid("diana", "A4", "90");     // Not unique (same as bob)
            service.createBid("eve", "A4", "100");      // Not unique (same as charlie)
            
            // Update a bid
            service.updateBid("alice", "A4", "70");     // Still unique, now 70
            
            // Withdraw and re-add
            service.withdrawBid("bob", "A4");           // Now diana's 90 becomes unique
            
            // Close auction
            String result = service.closeAuction("A4");
            System.out.println("Result: " + result);
            System.out.println("Expected: diana wins with bid 90.0 (highest unique bid after bob withdrew)\n");
            
        } catch (Exception e) {
            System.out.println("Error in Test Case 4: " + e.getMessage() + "\n");
        }
    }
}
