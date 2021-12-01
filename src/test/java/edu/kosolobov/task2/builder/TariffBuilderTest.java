package edu.kosolobov.task2.builder;

import edu.kosolobov.task2.entity.Operator;
import edu.kosolobov.task2.entity.Parameters;
import edu.kosolobov.task2.entity.Tariff;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TariffBuilderTest {

    @Test
    void build() {
        TariffBuilder builder = new TariffBuilder();

        Tariff tariff = builder
                .name("Fast")
                .operator(new Operator("MTC", "Belarus"))
                .payroll(15.0)
                .smsPrice(0.2)
                .callPrice(1.0)
                .homeCallPrice(1.2)
                .outdoorCallPrice(5.0)
                .parameters(Parameters.getParameters(Parameters.ROAMING))
                .build();

        assertNotNull(tariff);
    }
}