package com.macan.account.query.api.queries;

import com.macan.cqrs.core.queries.BaseQuery;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindAccountByIdQuery extends BaseQuery {
    private String id;
}
