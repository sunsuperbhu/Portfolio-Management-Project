package com.klu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolioproject.model.Asset;
import com.portfolioproject.model.Holding;
import com.portfolioproject.model.Stock;
import com.portfolioproject.model.User;
import com.portfolioproject.service.PortfolioService;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void userStoresDetailsAndStartsWithoutHoldings() {
        User user = new User("user-1", "Alex", "alex@example.com");

        assertEquals("user-1", user.getUserid());
        assertEquals("Alex", user.getName());
        assertEquals("alex@example.com", user.getEmail());
        assertTrue(user.getHoldings().isEmpty());
    }

    @Test
    void userCanAddStockHolding() {
        User user = new User("user-1", "Alex", "alex@example.com");
        Stock stock = new Stock("stock-1", "Example Corp", 10.0, 12.5);
        Holding holding = new Holding("holding-1", stock, 4);

        user.addHolding(holding);

        assertEquals(1, user.getHoldings().size());
        assertSame(holding, user.getHoldings().get(0));
        assertEquals(50.0, holding.getCurrentValue(), 0.0);
    }

    @Test
    void portfolioServiceStoresUsersById() {
        PortfolioService service = new PortfolioService();
        User user = new User("user-1", "Alex", "alex@example.com");

        service.addUser(user);

        assertTrue(service.userExists("user-1"));
        assertSame(user, service.getUser("user-1"));
        assertEquals(1, service.getAllUsers().size());
    }

    @Test
    void jacksonRoundTripsPolymorphicHoldings() throws Exception {
        User user = new User("user-1", "Alex", "alex@example.com");
        user.addHolding(new Holding(
                "holding-1",
                new Stock("stock-1", "Example Corp", 10.0, 12.5),
                4
        ));
        ObjectMapper mapper = new ObjectMapper();

        User restored = mapper.readValue(mapper.writeValueAsString(user), User.class);
        Asset restoredAsset = restored.getHoldings().get(0).getAsset();

        assertEquals("user-1", restored.getUserid());
        assertInstanceOf(Stock.class, restoredAsset);
        assertEquals(50.0, restored.getHoldings().get(0).getCurrentValue(), 0.0);
    }
}
