package com.location.voiture.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.location.voiture.web.rest.TestUtil;

public class OffreLocationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OffreLocation.class);
        OffreLocation offreLocation1 = new OffreLocation();
        offreLocation1.setId(1L);
        OffreLocation offreLocation2 = new OffreLocation();
        offreLocation2.setId(offreLocation1.getId());
        assertThat(offreLocation1).isEqualTo(offreLocation2);
        offreLocation2.setId(2L);
        assertThat(offreLocation1).isNotEqualTo(offreLocation2);
        offreLocation1.setId(null);
        assertThat(offreLocation1).isNotEqualTo(offreLocation2);
    }
}
