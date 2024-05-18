package org.nahuelrodriguez;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.annotation.Sql;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import java.util.Map;

@Testcontainers(disabledWithoutDocker = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql("classpath:sqls/init.sql")
@MicronautTest
class WorkingMicronautRepositoryTest implements TestPropertyProvider {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:12.19-alpine3.19")
    );

    @Inject
    private WorkingMicronautRepository subject;

    @Override
    public @NonNull Map<String, String> getProperties() {
        if (!postgres.isRunning()) {
            postgres.start();
        }
        return Map.of(
                "datasources.default.driver-class-name", "org.postgresql.Driver",
                "datasources.default.url", postgres.getJdbcUrl(),
                "datasources.default.username", postgres.getUsername(),
                "datasources.default.password", postgres.getPassword()
        );
    }

    @Test
    @DisplayName("Test that, hopefully, proves that buggy @Transactional behavior can't be caught when running tests")
    void successOnExecutingBuggyMethodEvenWhenItFailsInProd() {
        // when
        final var result = subject.buggedMethod();

        // then
        Assertions.assertFalse(result.isEmpty());
    }
}