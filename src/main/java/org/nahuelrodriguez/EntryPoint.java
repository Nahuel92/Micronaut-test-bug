package org.nahuelrodriguez;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

/**
 * Please:
 * - Start the Docker-compose stack.
 * - Export MICRONAUT_ENVIRONMENTS=prod
 * Before running the app
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
