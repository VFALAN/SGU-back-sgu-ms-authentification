package com.msc.ms.authentification.module;

import com.msc.ms.authentification.profile.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {
    private final IModuleRepository iModuleRepository;


    List<ModuleEntity> listAllByProfile(ProfileEntity profile) {
        return this.iModuleRepository.loadModulesByProfile(profile);
    }


}
