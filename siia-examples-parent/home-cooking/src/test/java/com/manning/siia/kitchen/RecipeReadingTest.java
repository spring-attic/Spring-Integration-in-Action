package com.manning.siia.kitchen;

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

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/** 
 * @author Iwein Fuld
 */
@ContextConfiguration(locations = {"/TEST-home-dinner-flow.xml", "/TEST-recipe-reader.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RecipeReadingTest {

	@Autowired
	private TemporaryFolder recipeBookLocation;

    @Autowired
    private PollableChannel test;

	@Test
	public void shouldReadFileInDirectoryToMessage() throws IOException {
		File resource = new ClassPathResource("/pilav.xml").getFile();
		//copy
		File recipeWriting = recipeBookLocation.newFile("pilav.xml.writing");
		FileUtils.copyFile(resource, recipeWriting);
		//then rename
		final File outFile = recipeBookLocation.newFile("pilav.xml");
		recipeWriting.renameTo(outFile);

		final Message<File> message = (Message<File>) test.receive(2000);
		assertThat(message, is(notNullValue()));
		final File payload = message.getPayload();
		assertThat(payload.getPath(), is(outFile.getPath()));
	}
}
