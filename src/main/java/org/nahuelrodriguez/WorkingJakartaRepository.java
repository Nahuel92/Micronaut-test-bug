package org.nahuelrodriguez;

import io.micronaut.data.annotation.Repository;
import java.util.List;

@Repository
@jakarta.transaction.Transactional
public abstract class WorkingJakartaRepository {
    public List<Integer> workingFetch() {
        return List.of(5, 10, 15);
    }
}
