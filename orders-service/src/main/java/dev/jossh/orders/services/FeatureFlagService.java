package dev.jossh.orders.services;

import io.getunleash.Unleash;
import org.springframework.stereotype.Service;

@Service
public class FeatureFlagService {

    private final Unleash unleash;

    public FeatureFlagService(Unleash unleash) {
        this.unleash = unleash;
    }

    public boolean isOrderNotificationsEnabled() {
        try {
            return unleash.isEnabled("order-notifications", false);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isBulkOrderDiscountEnabled() {
        try {
            return unleash.isEnabled("bulk-order-discount", false);
        } catch (Exception e) {
            return false;
        }
    }
}