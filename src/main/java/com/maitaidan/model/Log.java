package com.maitaidan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Crytis on 2016/5/25.
 * Just test.
 */
@Data
@AllArgsConstructor()
@NoArgsConstructor
public class Log {

    private int id;
    private int userId;
    private int SignConfigId;
    private String content;
    private Date createTime;
}
