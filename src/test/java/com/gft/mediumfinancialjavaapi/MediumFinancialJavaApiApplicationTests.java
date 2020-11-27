package com.gft.mediumfinancialjavaapi;

import com.gft.mediumfinancialjavaapi.repository.Transactions;
import com.gft.mediumfinancialjavaapi.service.StatisticService;
import com.gft.mediumfinancialjavaapi.service.TransactionService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@SpringBootTest(classes = { TransactionService.class, StatisticService.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@ActiveProfiles("test")
class MediumFinancialJavaApiApplicationTests {

    @MockBean
    Transactions transactions;

    @Autowired
    TransactionService transactionService;

    @Autowired
    StatisticService statisticServices;

    @Test
    public void shouldReturnNotNullTransactionService() {
        assertNotNull(transactionService);
    }

    @Test
    public void shouldReturnNotNullStatisticService() {
        assertNotNull(statisticServices);
    }

}
