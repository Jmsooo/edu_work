package com.lagou.testFastJSON;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @JSONField(name = "USERNAME", ordinal = 1)
    private String username;

    @JSONField(name = "AGE", ordinal = 2)
    private int age;

    @JSONField(name = "BIRTHDAY", ordinal = 3)
    private String birthday;
}
