package com.nefer.service.ticket;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LotteryTicketServiceTest {

    @Test
    public void divide() {
        Integer numOfSecondPlace = 2;
        Assertions.assertThat(Math.divideExact(numOfSecondPlace, 5)).isEqualTo(0);
        Assertions.assertThat(Math.divideExact(numOfSecondPlace, 5)).isInstanceOf(Integer.class);
        Assertions.assertThat(numOfSecondPlace % 5).isEqualTo(2);
    }

}
