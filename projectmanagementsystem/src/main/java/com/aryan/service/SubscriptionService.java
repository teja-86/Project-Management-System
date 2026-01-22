package com.aryan.service;

import com.aryan.model.entity.PlanType;
import com.aryan.model.entity.Subscription;
import com.aryan.model.entity.User;

public interface SubscriptionService {

    Subscription createSubscription(User user) throws Exception;

    Subscription getUserSubscription(Long userId) throws Exception;

    Subscription updateSubscription(Long userId, PlanType planType) throws Exception;

    boolean isValid(Subscription subscription);

}
