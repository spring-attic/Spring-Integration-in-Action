package com.manning.siia.kitchen;

import com.manning.siia.kitchen.domain.Meal;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.Message;
import org.springframework.integration.core.PollableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Jeroen van Erp
 */
@ContextConfiguration(locations = "/TEST-home-dinner-flow.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EndToEndIntegrationTest {

	@Autowired
	private TemporaryFolder recipeBookLocation;

    @Autowired
    private PollableChannel meals;

    @Test
    public void shouldCreateAMeal() throws IOException {
	    final TimedPollableChannel timed = new TimedPollableChannel(meals);
	    File resource = new ClassPathResource("/pilav.xml").getFile();
		//copy
		File recipeWriting = recipeBookLocation.newFile("pilav.xml.writing");
		FileUtils.copyFile(resource, recipeWriting);
		//then rename
		recipeWriting.renameTo(recipeBookLocation.newFile("pilav.xml"));

        Message<?> message = timed.receive(3500);
	    assertThat(message, is(notNullValue()));
        Meal meal = (Meal) message.getPayload();
        assertThat(meal.getRecipe().getName(), is("Pilav"));
        assertThat(meal.isDone(), is(true));
    }
}
