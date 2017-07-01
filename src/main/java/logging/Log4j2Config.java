package logging;

import controller.ServiceController;
import groovy.lang.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@Singleton
public class Log4j2Config implements InitializingBean {

    private Logger LOG = LogManager.getLogger(ServiceController.class);

    private final String PATH_LOG_CONFIG = "/Users/alexandersorokin/Documents/Source Code/log4j2.xml";

    private LoggerContext loggerContext;

    @PostConstruct
    public void init() throws IOException {
        loggerContext = Configurator.initialize(null, PATH_LOG_CONFIG);
    }

    public void reload() throws IOException {
        loggerContext.reconfigure();
        LOG.trace("reload");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.trace("Before initialization...");
    }

}
