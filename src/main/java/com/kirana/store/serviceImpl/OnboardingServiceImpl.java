package com.kirana.store.serviceImpl;

import com.kirana.store.collections.Store;
import com.kirana.store.repository.OnboardingRepository;
import com.kirana.store.service.OnBoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnboardingServiceImpl implements OnBoardingService {

    @Autowired
    OnboardingRepository onboardingRepository;

    @Override
    public String save(Store store) {
        return onboardingRepository.save(store).getId();
    }

    @Override
    public List<Store> getStoreStartsWith(String name) {
        return onboardingRepository.findByDescriptionStartsWith(name);
    }
}
