package com.project.duo.memo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class MemoRequest {
    private Long id;

    private String content;

    private int seq;
}
