/**
 * Created by chitwan on 6/19/17.
 */
import static org.junit.Assert.*;

import org.junit.Test;



public class Fliktest {

    @Test
    public void testisSameNumber(){
        assertEquals(false, Flik.isSameNumber(2, 3));
        assertEquals(true, Flik.isSameNumber(10, 10));

    }


}
