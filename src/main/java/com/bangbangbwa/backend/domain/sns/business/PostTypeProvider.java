package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.member.common.enums.Role;
import com.bangbangbwa.backend.domain.sns.common.enums.PostType;
import org.springframework.stereotype.Component;

@Component
public class PostTypeProvider {

    public PostType getInversePostTypeForRole(Role role) {
        if (role.equals(Role.STREAMER)) return PostType.MEMBER;
        return PostType.STREAMER;
    }
}
