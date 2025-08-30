# FlipBidder Auction System

## Overview
A comprehensive online auction system implementation following SOLID design principles and Object-Oriented Programming concepts. The system supports multiple simultaneous auctions with extensible winner selection strategies.

## Features
- **User Management**: Add buyers and sellers to the system
- **Auction Management**: Create auctions with configurable bid limits and participation costs
- **Bid Management**: Create, update, and withdraw bids with validation
- **Extensible Winner Selection**: Support for different winner determination strategies
- **Robust Error Handling**: Comprehensive validation and edge case handling

## Design Principles Applied

### SOLID Principles
1. **Single Responsibility Principle (SRP)**
   - `User`: Represents user data only
   - `Auction`: Manages auction state and bids
   - `Bid`: Represents bid information
   - `AuctionService`: Handles business operations
   - `WinnerStrategy`: Focuses solely on winner determination

2. **Open/Closed Principle (OCP)**
   - Winner strategies are extensible through the `WinnerStrategy` interface
   - New winner determination algorithms can be added without modifying existing code

3. **Liskov Substitution Principle (LSP)**
   - All `WinnerStrategy` implementations can be used interchangeably

4. **Interface Segregation Principle (ISP)**
   - Clean, focused interfaces with single responsibilities

5. **Dependency Inversion Principle (DIP)**
   - `AuctionService` depends on the `WinnerStrategy` abstraction, not concrete implementations

### Design Patterns Used
- **Strategy Pattern**: For pluggable winner selection algorithms
- **Facade Pattern**: `AuctionService` provides a simplified interface to the complex auction system

## Class Structure

### Core Classes
- **User**: Represents buyers and sellers
- **Auction**: Manages auction state, bid limits, and bidder interactions
- **Bid**: Immutable representation of a user's bid
- **AuctionService**: Main service layer providing all business operations

### Strategy Classes
- **WinnerStrategy**: Interface defining winner selection contract
- **HighestUniqueBidStrategy**: Selects winner with highest unique bid amount
- **LowestUniqueBidStrategy**: Selects winner with lowest unique bid amount

## Key Business Rules
1. **Unique Bid Definition**: A bid amount that appears exactly once among all participants
2. **Winner Selection**: 
   - **Highest Unique**: Winner has the highest amount that no one else bid
   - **Lowest Unique**: Winner has the lowest amount that no one else bid
3. **No Winner**: When no unique bids exist
4. **Bid Validation**: Bids must be within auction's min/max limits
5. **Participation Cost**: Users must pay to participate (tracked but not enforced in this demo)

## Usage Example

```java
// Create service with default strategy (Highest Unique Bid)
AuctionService service = new AuctionService();

// Add users
service.addBuyer("buyer1");
service.addSeller("seller1");

// Create auction
service.createAuction("A1", "10", "50", "1", "seller1");

// Place bids
service.createBid("buyer1", "A1", "25");
service.updateBid("buyer1", "A1", "30");

// Close auction and get winner
String result = service.closeAuction("A1");
```

## Testing
Run the demo class to see all test cases:
```bash
javac -cp src src/org/ritwik/flipbidder/*.java
java -cp src org.ritwik.flipbidder.FlipBidderDemo
```

## Extensibility
- **New Winner Strategies**: Implement `WinnerStrategy` interface
- **Additional Validation**: Extend classes with new validation rules
- **Auction Types**: Subclass `Auction` for specialized auction types
- **User Roles**: Extend `User` class for different user types

## Edge Cases Handled
- Invalid bid amounts (negative, out of range)
- Bidding on closed auctions
- Withdrawing non-existent bids
- Duplicate auction IDs
- Non-existent users or auctions
- Empty auction scenarios

This implementation provides a solid foundation for a production auction system while maintaining simplicity and extensibility required for technical interviews.
