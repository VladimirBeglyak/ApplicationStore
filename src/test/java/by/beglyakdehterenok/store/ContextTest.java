package by.beglyakdehterenok.store;

import by.beglyakdehterenok.store.config.DatabaseConfig;
import by.beglyakdehterenok.store.entity.Brand;
import by.beglyakdehterenok.store.repository.BrandRepository;
import by.beglyakdehterenok.store.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
public class ContextTest {

    @Test
    public void test1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);

        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(bean-> System.out.println(bean));
    }
}
