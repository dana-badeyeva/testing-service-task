package com.playtika.automation.school.test.framework.pojo.requests;

import lombok.Value;
import lombok.Builder;

@Value
@Builder
public class UpdateNoteRequest {
    
    String content;
    Integer version;
}
