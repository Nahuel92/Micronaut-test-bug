package org.nahuelrodriguez;

import io.micronaut.data.annotation.Repository;
import java.util.List;

@Repository
@jakarta.transaction.Transactional  // Works during testing time
// but not when running in Production mode
public abstract class WorkingJakartaRepository {
    public List<Integer> workingFetch() {
        return List.of(5, 10, 15);
    }
}
