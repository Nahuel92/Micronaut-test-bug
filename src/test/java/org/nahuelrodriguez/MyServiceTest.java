package org.nahuelrodriguez;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@MicronautTest
class MyServiceTest {
    @MockBean(BuggedRepository.class)
    BuggedRepository buggedRepository = mock(BuggedRepository.class);

    @MockBean(WorkingMicronautRepository.class)
    WorkingMicronautRepository workingMicronautRepository = mock(WorkingMicronautRepository.class);

    @MockBean(WorkingJakartaRepository.class)
    WorkingJakartaRepository workingJakartaRepository = mock(WorkingJakartaRepository.class);

    @Inject
    private MyService subject;

    @Test
    void failureWhenRepositoryMethodsAreAnnotatedAsTransactionalMicronaut() {
        // when
        subject.myBuggedMethodMicronaut();

        // then
        verify(buggedRepository).buggedFetchMicronaut();
    }

    @Test
    void failureWhenRepositoryMethodsAreAnnotatedAsTransactionalJakarta() {
        // when
        subject.myBuggedMethodJakarta();

        // then
        verify(buggedRepository).buggedFetchJakarta();
    }

    @Test
    void successWhenRepositoryIsClassAnnotatedAsMicronautTransactional() {
        // when
        subject.myWorkingMicronautMethod();

        // then
        verify(workingMicronautRepository).workingFetch();
    }

    @Test
    void successWhenRepositoryIsClassAnnotatedAsJakartaTransactional() {
        // when
        subject.myWorkingJakartaMethod();

        // then
        verify(workingJakartaRepository).workingFetch();
    }
}