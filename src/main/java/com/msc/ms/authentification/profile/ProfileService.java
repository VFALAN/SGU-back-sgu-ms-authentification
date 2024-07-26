package com.msc.ms.authentification.profile;

import com.msc.ms.authentification.common.error.DataBaseObjectNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProfileService {
    private final IProfileRepository iProfileRepository;

    public ProfileService(final IProfileRepository pIProfileRepository) {
        iProfileRepository = pIProfileRepository;
    }

    public ProfileEntity findById(Integer pProfileId) throws DataBaseObjectNotFound {
        final var mOptionalProfileEntity = iProfileRepository.findById(pProfileId);
        if (mOptionalProfileEntity.isPresent()) {
            return mOptionalProfileEntity.get();
        } else {
            throw new DataBaseObjectNotFound("ID", "Profile Not Foud", String.valueOf(pProfileId));
        }
    }


}
