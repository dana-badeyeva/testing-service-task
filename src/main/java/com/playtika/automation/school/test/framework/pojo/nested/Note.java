package com.playtika.automation.school.test.framework.pojo.nested;

import lombok.Data;

@Data
public class Note {

    Integer id;
    String content;
    String createdAt;
    String modifiedAt;
    Integer version;
}
