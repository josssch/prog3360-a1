package dev.jossh.a1.services;

import io.getunleash.Unleash;
import org.springframework.stereotype.Service;

@Service
public class FeatureFlagService {

    private final Unleash unleash;

    public FeatureFlagService(Unleash unleash) {
        this.unleash = unleash;
    }

    public boolean isPremiumPricingEnabled() {
        try {
            return unleash.isEnabled("premium-pricing", false);
        } catch (Exception e) {
            return false;
        }
    }
}