package com.project.duo.memo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class MemoRequest {
    @JsonProperty("member_id")
    private Long memberId;

    @JsonProperty("memo_id")
    private Long memoId;

    private String content;

    private int seq;
}
