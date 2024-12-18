package com.bangbangbwa.backend.domain.sns.repository;

import com.bangbangbwa.backend.global.test.RedisTest;
import com.bangbangbwa.backend.global.util.randomValue.RandomValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(ReaderPostRepository.class)
class ReaderPostRepositoryTest extends RedisTest {

    @Autowired
    private ReaderPostRepository readerPostRepository;

// 읽은 게시물 반환 되지 않도록 수정 후 주석 해제
//    @Test
//    void addReadPost_AND_findAllReadPostsByMemberId() {
//        // given
//        int postCount = RandomValue.getInt(0,5);
//
//        List<String> postIdList = IntStream.range(0, postCount)
//                .mapToObj(i -> String.valueOf(RandomValue.getRandomLong(0, 9999)))
//                .toList();
//
//
//        String memberId = String.valueOf(RandomValue.getRandomLong(0,9999));
//
//
//        // when
//        postIdList.forEach(i -> readerPostRepository.addReadPost(memberId, i));
//
//        // then
//        Set<String> postIds = readerPostRepository.findAllReadPostsByMemberId(memberId);
//        Set<String> expectedList = new HashSet<>(postIdList);
//        assertThat(expectedList).isEqualTo(postIds);
//    }
}