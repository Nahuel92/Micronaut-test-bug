package org.nahuelrodriguez;

import jakarta.inject.Singleton;

@Singleton
public class MyService {
    private final BuggedRepository buggedRepository;
    private final WorkingRepository workingRepository;

    public MyService(final BuggedRepository buggedRepository, final WorkingRepository workingRepository) {
        this.buggedRepository = buggedRepository;
        this.workingRepository = workingRepository;
    }

    public void myBuggedMethodMicronaut() {
        buggedRepository.buggedFetchMicronaut();
    }

    public void myBuggedMethodJakarta() {
        buggedRepository.buggedFetchJakarta();
    }

    public void myWorkingMethod() {
        workingRepository.workingFetch();
    }
}