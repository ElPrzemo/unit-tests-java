package HOMEWORK;


import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinatesTest {

   @Test
   public void constructorShouldFailIfArgumentIsToHigh() throws IllegalArgumentException{
       assertThrows(IllegalArgumentException.class, ()-> new Coordinates(101,20));
   }

    @Test
    public void constructorShouldFailIfArgumentIsToLow() throws IllegalArgumentException{
       assertThrows(IllegalArgumentException.class, ()-> new Coordinates(-1,20));
    }

    @Test
    public void copyShouldReturnNewObject (){

      Coordinates coordinates = new Coordinates(10,10);
    Coordinates copy = Coordinates.copy(coordinates, 0, 0);

    assertThat(copy, not(sameInstance(coordinates)));
    assertThat(copy, equalTo(coordinates));
    }

    @Test
    void copyShouldReturnaAddCoordinates(){
       //given
        Coordinates coordinates = new Coordinates(10,10);
        //when
        Coordinates copy = Coordinates.copy(coordinates, 5, 6);
        //then
        assertThat(copy.getX(),equalTo(15));
        assertThat(copy.getY(),equalTo(16));
    }

}