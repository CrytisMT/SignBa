package com.maitaidan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Crytis on 2016/5/23.
 * Just test.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignConfig {

    private int id;
    private int userId;
    private int method;
    private String url;
    private String cookie;
    private String params;
    private String remark;

}
