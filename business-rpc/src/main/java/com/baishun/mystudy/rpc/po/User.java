package com.baishun.mystudy.rpc.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 代码描述
 *
 * @Author BAISHUN
 * @Date: 2024/5/19 15:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;

    private String name;

    private LocalDateTime updateTime;
}
