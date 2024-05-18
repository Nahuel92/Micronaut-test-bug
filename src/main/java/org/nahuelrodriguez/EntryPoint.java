package org.nahuelrodriguez;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

/**
 * Please:
 * - Export MICRONAUT_ENVIRONMENTS=prod
 * Before running the app, as I had to split DB config for production mode and tests.
 * This is needed because `@Transactional` behavior is broken depending on how it is used
 * and this misbehavior can't be caught in tests (which makes this extremely unwanted).
 */

@Singleton
public class EntryPoint {
    private WorkingMicronautRepository workingMicronautRepository;

    public EntryPoint(final WorkingMicronautRepository workingMicronautRepository) {
        this.workingMicronautRepository = workingMicronautRepository;
    }

    @EventListener
    @Requires(notEnv = Environment.TEST)
    void init(final StartupEvent e) {
        final var result = workingMicronautRepository.buggedMethod(); // This fails at runtime (but not during testing)
        System.out.println(result);
    }
}
