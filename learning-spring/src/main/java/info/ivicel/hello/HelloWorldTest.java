package info.ivicel.hello;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.*;

import info.ivicel.hello.impl.subpackage.Show;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class HelloWorldTest {
    @javax.annotation.Resource
    private Person person;

//    @Autowired
//    private Show s;

//    @Autowired
    private Boss boss;

    @Test
    public void testResourceInjection() {
        System.out.println(person);
    }

//    @Test(expected = RuntimeException.class)
//    public void showTest() {
//        s.show();
//        s.showError();
//    }

    @Test
    public void xmlBeanFactoryTest() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource("classpath:applicationContext.xml");
        System.out.println(res.getURI());
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(res);
        person = (Person)factory.getBean("info.ivicel.hello.Person#0");
        System.out.println(person);
    }
}
