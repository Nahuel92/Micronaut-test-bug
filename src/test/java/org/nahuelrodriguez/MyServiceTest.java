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

    @MockBean(WorkingRepository.class)
    WorkingRepository workingRepository = mock(WorkingRepository.class);

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
    void successWhenRepositoryClassIsAnnotatedAsTransactional() {
        // when
        subject.myWorkingMethod();

        // then
        verify(workingRepository).workingFetch();
    }
}