package com.aryan;

import com.aryan.model.entity.Subscription;
import com.aryan.model.entity.User;
import com.aryan.model.entity.PlanType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionTest {

    @Test
    public void testSubscription() {
        Subscription subscription = new Subscription();
        subscription.setId(1L);
        subscription.setSubscriptionStartDate(LocalDate.now());
        subscription.setSubscriptionEndDate(LocalDate.now().plusDays(30));
        subscription.setPlanType(PlanType.FREE);
        subscription.setUser(new User());
        subscription.setValid(true);

        assertNotNull(subscription);
        assertEquals(1L, subscription.getId());
        assertNotNull(subscription.getSubscriptionStartDate());
        assertNotNull(subscription.getSubscriptionEndDate());
        assertEquals(PlanType.FREE, subscription.getPlanType());
        assertNotNull(subscription.getUser());
        assertTrue(subscription.isValid());
    }
}
