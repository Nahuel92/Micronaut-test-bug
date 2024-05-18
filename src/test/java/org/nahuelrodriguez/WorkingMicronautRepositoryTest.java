package org.nahuelrodriguez;

import io.micronaut.context.annotation.Property;
import io.micronaut.test.annotation.Sql;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Sql("classpath:sqls/init.sql")
@Property(name = "datasources.default.driver-class-name", value = "org.h2.Driver")
@Property(name = "datasources.default.url", value = "jdbc:h2:mem:h2")
@Property(name = "datasources.default.username", value = "sa")
@MicronautTest
class WorkingMicronautRepositoryTest {
    @Inject
    private WorkingMicronautRepository subject;

    @Test
    @DisplayName("Test that, hopefully, proves that buggy @Transactional behavior can't be caught when running tests")
    void successOnExecutingBuggyMethodEvenWhenItFailsInProd() {
        // when
        final var result = subject.buggedMethod();

        // then
        Assertions.assertFalse(result.isEmpty());
    }
}