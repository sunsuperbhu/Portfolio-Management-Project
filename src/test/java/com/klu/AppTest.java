package com.klu;

import com.portfolioproject.model.Holding;
import com.portfolioproject.model.Stock;
import com.portfolioproject.model.User;
import junit.framework.TestCase;

public class AppTest extends TestCase {

    public void testUserStoresDetailsAndStartsWithoutHoldings() {
        User user = new User("user-1", "Alex", "alex@example.com");

        assertEquals("user-1", user.getUserid());
        assertEquals("Alex", user.getName());
        assertEquals("alex@example.com", user.getEmail());
        assertTrue(user.getHoldings().isEmpty());
    }

    public void testUserCanAddStockHolding() {
        User user = new User("user-1", "Alex", "alex@example.com");
        Stock stock = new Stock("stock-1", "Example Corp", 10.0, 12.5);
        Holding holding = new Holding("holding-1", stock, 4);

        user.addHolding(holding);

        assertEquals(1, user.getHoldings().size());
        assertSame(holding, user.getHoldings().get(0));
        assertEquals(50.0, holding.getCurrentValue(), 0.0);
    }
}
