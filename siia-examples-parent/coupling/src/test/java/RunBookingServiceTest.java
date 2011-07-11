import com.manning.siia.coupling.MealPreference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.integration.support.MessageBuilder.withPayload;

/**
 * @author Marius Bogoevici
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context.xml")
public class RunBookingServiceTest {

    @Autowired @Qualifier("mealPreferenceUpdatesChannel")
    MessageChannel channel;


    @Test
    public void updateMealPreferences() {
        channel.send(withPayload(new MealPreference()).build());
    }
}
