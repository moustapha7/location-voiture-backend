package com.location.voiture.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.location.voiture.web.rest.TestUtil;

public class StatusReservationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatusReservation.class);
        StatusReservation statusReservation1 = new StatusReservation();
        statusReservation1.setId(1L);
        StatusReservation statusReservation2 = new StatusReservation();
        statusReservation2.setId(statusReservation1.getId());
        assertThat(statusReservation1).isEqualTo(statusReservation2);
        statusReservation2.setId(2L);
        assertThat(statusReservation1).isNotEqualTo(statusReservation2);
        statusReservation1.setId(null);
        assertThat(statusReservation1).isNotEqualTo(statusReservation2);
    }
}
