package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.repository.ReaderPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ReaderPostReader {

    private final ReaderPostRepository readerPostRepository;

    public Set<String> findAllReadPostsByMemberId(Long memberId) {
        return readerPostRepository.findAllReadPostsByMemberId(memberId.toString());
    }
}
