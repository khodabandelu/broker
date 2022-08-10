package com.macan.account.query.api.dto;

import com.macan.account.common.dto.BaseResponse;
import com.macan.account.query.domains.BrokerAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountLookupResponse extends BaseResponse {
    private List<BrokerAccount> accounts;

    public AccountLookupResponse(String message){
        super(message);
    }
}
