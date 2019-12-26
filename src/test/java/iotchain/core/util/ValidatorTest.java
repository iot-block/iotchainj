package iotchain.core.util;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ValidatorTest {
    @Test
    public void testCheckAddress(){
        boolean v1 = Validator.isValidAddress("f6df328deb0df489caad847df5761a6f7e3a082c", true);
        assertThat(v1, is(true));

        boolean v2 = Validator.isValidAddress("f6df328deb0df489caad847df5761a6f7e3a082c", false);
        assertThat(v2, is(false));

        boolean v3 = Validator.isValidAddress("itcf6df328deb0df489caad847df5761a6f7e3a082c", true);
        assertThat(v3, is(true));

        boolean v4 = Validator.isValidAddress("itcf6df328deb0df489caad847df5761a6f7e3a082c", false);
        assertThat(v4, is(true));

    }
}
