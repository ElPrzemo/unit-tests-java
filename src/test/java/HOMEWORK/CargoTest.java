package HOMEWORK;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CargoTest {

    @Test
    public void testCargoConstructorSetingCorrectNames(){
        //given
        String name= "Box";
        int weight = 10;

        //then
        Cargo cargo = new Cargo(name, weight);

        assertEquals("Box", name);
        assertEquals(10, weight);
    }


}