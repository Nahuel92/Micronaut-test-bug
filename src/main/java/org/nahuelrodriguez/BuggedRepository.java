package org.nahuelrodriguez;

import io.micronaut.data.annotation.Repository;
import java.util.List;

@Repository
public abstract class BuggedRepository {
    @io.micronaut.transaction.annotation.Transactional
    public List<Integer> buggedFetchMicronaut() {
        return List.of(5, 10, 15);
    }

    @jakarta.transaction.Transactional
    public List<Integer> buggedFetchJakarta() {
        return List.of(5, 10, 15);
    }
}
