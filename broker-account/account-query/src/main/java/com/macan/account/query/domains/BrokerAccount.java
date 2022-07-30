package com.macan.account.query.domains;

import com.macan.account.common.dto.AccountType;
import com.macan.cqrs.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrokerAccount extends BaseEntity {

    @PrimaryKey
    private String id;
    private String accountHolder;
    private Date creationDate;
    private AccountType accountType;
    private double balance;

}
