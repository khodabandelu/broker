package com.macan.account.query.api.queries;

import com.macan.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAccountByIdQuery extends BaseQuery {
    private String id;
}
