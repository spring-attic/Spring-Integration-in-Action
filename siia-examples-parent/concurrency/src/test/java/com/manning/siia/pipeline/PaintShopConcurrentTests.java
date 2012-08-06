package com.manning.siia.pipeline;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Marius Bogoevici
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:paint-shop-concurrent.xml")
public class PaintShopConcurrentTests {

    @Autowired SupplyInput supplyInput;

    @Autowired Counter counter;

    @Test
    public void testBuild() throws Exception {

       counter.resetLatch(10);

       for (int i=0; i < 10; i++ ) {
           PieceKit pieceKit = new PieceKit(i);
           supplyInput.buildCar(pieceKit);
       }

       Assert.assertEquals(10, counter.getCount());
    }
}
