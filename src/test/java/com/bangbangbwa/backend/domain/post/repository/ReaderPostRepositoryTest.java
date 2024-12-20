package com.bangbangbwa.backend.domain.post.repository;

import com.bangbangbwa.backend.domain.sns.repository.ReaderPostRepository;
import com.bangbangbwa.backend.global.test.RedisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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