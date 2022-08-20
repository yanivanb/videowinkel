package be.vdab.videowinkel.sessions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MandjeTest {
    private Mandje mandje;
    @BeforeEach
    void beforeEach() {
        mandje = new Mandje();
    }
    @Test
    void eenNieuwMandjeIsLeeg() {
        assertThat(mandje.getIds()).isEmpty();
    }
    @Test
    void nadatJeEenItemInHetMandjeLegtBevatDitMandjeEnkelDitItem() {
        mandje.voegToe(5L);
        assertThat(mandje.getIds()).containsOnly(5L);
    }
    @Test
    void jeKanEenItemMaarÉénKeerToevoegenAanHetMandje() {
        mandje.voegToe(5L);
        mandje.voegToe(5L);
        assertThat(mandje.getIds()).containsOnly(5L);
    }
    @Test
    void nadatJeTweeItemsInHetMandjeLegtBevatDitMandjeEnkelDieItems() {
        mandje.voegToe(6L);
        mandje.voegToe(6L);
        assertThat(mandje.getIds()).containsOnly(6L, 6L);
    }
}
