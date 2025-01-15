package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.exception.DuplicatePendingPromotionException;
import com.bangbangbwa.backend.domain.streamer.common.enums.PendingType;
import com.bangbangbwa.backend.domain.streamer.repository.PendingStreamerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromoteValidator {

    private final PendingStreamerRepository pendingStreamerRepository;

    public void checkForDuplicatePendingPromotion(Long memberId) {
        pendingStreamerRepository.findByMemberIdAndStatus(memberId, PendingType.PENDING)
                .ifPresent(pendingPromotion -> {
                    throw new DuplicatePendingPromotionException();
                });
    }
}
