package com.laining.mybatis.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.junit.runners.model.InitializationError;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

public class MyRunner extends SpringJUnit4ClassRunner {

    static {
        try {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator joranConfigurator = new JoranConfigurator();
            joranConfigurator.setContext(lc);
            lc.reset();
            String resolvedLocation = SystemPropertyUtils.resolvePlaceholders("classpath:logback-test.xml");
            URL url = ResourceUtils.getURL(resolvedLocation);
            File file = null;
            if (("file".equals(url.getProtocol())) && (!(file = ResourceUtils.getFile(url)).exists())) {
                throw new FileNotFoundException(" file [" + resolvedLocation + "] not found");
            }
            joranConfigurator.doConfigure(file);
            StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
        } catch (JoranException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

}
