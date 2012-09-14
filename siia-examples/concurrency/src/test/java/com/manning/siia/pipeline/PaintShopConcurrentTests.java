/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
