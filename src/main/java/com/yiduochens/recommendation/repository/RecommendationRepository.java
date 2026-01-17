package com.yiduochens.recommendation.repository;

import com.yiduochens.recommendation.domain.RecommendationItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecommendationRepository {

    private final DynamoDbTable<RecommendationItem> table;

    public RecommendationRepository(
            DynamoDbEnhancedClient enhancedClient,
            @Value("${app.dynamodb.table-name}") String tableName
    ) {
        this.table = enhancedClient.table(tableName, TableSchema.fromBean(RecommendationItem.class));
    }

    public void put(RecommendationItem item) {
        table.putItem(item);
    }

    public List<RecommendationItem> findByPk(String pk) {
        List<RecommendationItem> results = new ArrayList<>();
        var query = table.query(r -> r.queryConditional(
                QueryConditional.keyEqualTo(Key.builder().partitionValue(pk).build())
        ));
        query.stream().forEach(page -> results.addAll(page.items()));
        return results;
    }
}
