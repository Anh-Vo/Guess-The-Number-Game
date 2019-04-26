package academy.learnprogramming.config;

import academy.learnprogramming.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@Import(GameConfig.class)
@ComponentScan(basePackages = "academy.learnprogramming")
public class AppConfig {

    // == bean methods ==
    @Bean
    public NumberGenerator numberGenerator() {
        return new NumberGeneratorImpl();
    }

    @Bean
    public Game game() {
        return new GameImpl();
    }

    @Bean
    public MessageGenerator messageGenerator() {
        return new MessageGeneratorImpl();
    }

    public static class Main {

        private static final Logger log = LoggerFactory.getLogger(Main.class);

        public static void main(String[] args) {
            log.info("Guess The Number Game!");

            // create the context (aka container)
            ConfigurableApplicationContext context
                    = new AnnotationConfigApplicationContext(AppConfig.class);

            // get number generator bean from context (container)
            NumberGenerator numberGenerator
                    = context.getBean(NumberGenerator.class);

            // call method next() to get a random number
            int number = numberGenerator.next();

            // log generated number
            log.info("number = {}", number);

            // Get game bean from context (container)
            Game game = context.getBean(Game.class);

            MessageGenerator messageGenerator =
                    context.getBean(MessageGenerator.class);
            log.info("academy.learnprogramming.config.AppConfig.Main message: {}", messageGenerator.getMainMessage());
            log.info("Result message: {}", messageGenerator.getResultMessage());

            // close context (aka container)
            context.close();

        }
    }
}
