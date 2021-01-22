package com.project.duo.memo.util;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtAuthenticationToken {
    private String token;
}
