package org.nahuelrodriguez;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import java.util.List;

@Repository
@io.micronaut.transaction.annotation.Transactional  // Works during testing time but not when running in Production mode
public abstract class WorkingMicronautRepository {
    private JdbcOperations jdbcOperations;

    public WorkingMicronautRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public List<Integer> workingFetch() {
        return List.of(5, 10, 15);
    }

    public List<MyEntity> buggedMethod() {
        return jdbcOperations.prepareStatement(
                "SELECT id, name FROM mytable",
                statement -> jdbcOperations.entityStream(statement.executeQuery(), MyEntity.class).toList()
        );
    }
}

@Introspected
record MyEntity (int id, String name) {}