package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.repository.ReaderPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReaderPostCreator {

    private final ReaderPostRepository readerPostRepository;

    public void addReadPost (Long memberId, Long postId) {
        readerPostRepository.addReadPost(memberId.toString(), postId.toString());
    }
}
