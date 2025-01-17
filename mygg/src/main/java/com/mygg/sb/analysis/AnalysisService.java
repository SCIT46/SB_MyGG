package com.mygg.sb.analysis;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mygg.sb.exception.custom.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final AnalysisRepository analRepo;

    // -------------------------------------- CRUD --------------------------------------
    public UserAnalysisDTO getUserAnalysis(String puuid) {
        Optional<UserAnalysisEntity> entity = analRepo.findByPuuid(puuid);
        if (entity.isEmpty()) return null;
        
        return UserAnalysisDTO.toDTO(entity.get());
    }

    public void saveUserAnalysis(UserAnalysisDTO dto) {
        UserAnalysisEntity entity = UserAnalysisEntity.toEntity(dto);
        analRepo.save(entity);
    }

    public void updateUserAnalysis(UserAnalysisDTO dto) throws Exception {
        Optional<UserAnalysisEntity> tmp = analRepo.findByPuuid(dto.getPuuid());
        if (tmp.isEmpty()) throw new DataNotFoundException("통계를 업데이트 할 유저가 없습니다");
        UserAnalysisEntity entity = UserAnalysisEntity.toEntity(dto);
        analRepo.save(entity);
    }

    public void deleteUserAnalysis(String puuid) throws Exception {
        Optional<UserAnalysisEntity> tmp = analRepo.findByPuuid(puuid);
        if (tmp.isEmpty()) throw new DataNotFoundException("통계를 삭제할 유저가 없습니다");
        analRepo.delete(tmp.get());
    }
    // -----------------------------------------------------------------------------------
}
