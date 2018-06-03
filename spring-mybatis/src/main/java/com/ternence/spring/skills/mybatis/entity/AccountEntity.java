package com.ternence.spring.skills.mybatis.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * TODO:Responsibility description of this class.
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/3 21:20
 */
@Table(name = "account")
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "balance")
    private BigDecimal balance;
}
