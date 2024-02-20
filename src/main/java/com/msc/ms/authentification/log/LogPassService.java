package com.msc.ms.authentification.log;

import com.msc.ms.authentification.log.model.LogPassEntity;
import com.msc.ms.authentification.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogPassService {

    private final ILogPassRepository iLogPassRepository;


    public List<LogPassEntity> findAllByUser(UserEntity userEntity){
        return this.iLogPassRepository.finaAllByUser(userEntity);
    }
}
