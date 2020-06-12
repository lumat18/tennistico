package com.gruzini.tennistico.checkers;

import com.gruzini.tennistico.services.HostedGameService;
import com.gruzini.tennistico.services.JoinRequestGameService;
import com.gruzini.tennistico.services.UpcomingGameService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FutureGameCheckerTest {
    @Mock
    private UpcomingGameService upcomingGameService;
    @Mock
    private HostedGameService hostedGameService;
    @Mock
    private JoinRequestGameService joinRequestGameService;
    @InjectMocks
    private FutureGameChecker futureGameChecker;


}