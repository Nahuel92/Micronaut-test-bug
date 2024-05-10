package org.nahuelrodriguez;

import jakarta.inject.Singleton;

@Singleton
public class MyService {
    private final BuggedRepository buggedRepository;
    private final WorkingMicronautRepository workingMicronautRepository;
    private final WorkingJakartaRepository workingJakartaRepository;

    public MyService(final BuggedRepository buggedRepository, final WorkingMicronautRepository workingMicronautRepository,
            final WorkingJakartaRepository workingJakartaRepository) {
        this.buggedRepository = buggedRepository;
        this.workingMicronautRepository = workingMicronautRepository;
        this.workingJakartaRepository = workingJakartaRepository;
    }

    public void myBuggedMethodMicronaut() {
        buggedRepository.buggedFetchMicronaut();
    }

    public void myBuggedMethodJakarta() {
        buggedRepository.buggedFetchJakarta();
    }

    public void myWorkingMicronautMethod() {
        workingMicronautRepository.workingFetch();
    }

    public void myWorkingJakartaMethod() {
        workingJakartaRepository.workingFetch();
    }
}