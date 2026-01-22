package com.aryan.service.Implementation;

import com.aryan.model.entity.PlanType;
import com.aryan.model.entity.Subscription;
import com.aryan.model.entity.User;
import com.aryan.repository.SubscriptionRepo;
import com.aryan.service.SubscriptionService;
import com.aryan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Autowired
    private UserService userService;

    @Override
    public Subscription createSubscription(User user) throws Exception {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setSubscriptionStartDate(LocalDate.now());
        subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
        subscription.setValid(true);
        subscription.setPlanType(PlanType.FREE);

        return subscriptionRepo.save(subscription);
    }

    @Override
    public Subscription getUserSubscription(Long userId) throws Exception {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        Subscription subscription = subscriptionRepo.findByUserId(userId);
        if (subscription == null) {
            throw new Exception("Subscription not found for user ID: " + userId);
        }

        return subscriptionRepo.save(subscription);
    }

    @Override
    public Subscription updateSubscription(Long userId, PlanType planType) throws Exception {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        if (planType == null) {
            throw new IllegalArgumentException("Plan type cannot be null");
        }

        Subscription subscription = subscriptionRepo.findByUserId(userId);
        if (subscription == null) {
            throw new Exception("Subscription not found for user ID: " + userId);
        }

        subscription.setPlanType(planType);
        subscription.setSubscriptionStartDate(LocalDate.now());
        if (planType == PlanType.MONTHLY) {
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
        } else {
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
        }

        return subscriptionRepo.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription cannot be null");
        }
        if (subscription.getPlanType() == PlanType.FREE) {
            return true;
        }

        LocalDate endDate = subscription.getSubscriptionEndDate();
        LocalDate currentDate = LocalDate.now();

        return endDate.isAfter(currentDate) || currentDate.isEqual(currentDate);
    }

}
