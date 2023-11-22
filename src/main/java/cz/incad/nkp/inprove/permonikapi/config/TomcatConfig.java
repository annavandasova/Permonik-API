package cz.incad.nkp.inprove.permonikapi.config;

import org.apache.catalina.connector.Connector;
import org.modelmapper.ModelMapper;
import org.modelmapper.module.jdk8.Jdk8Module;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TomcatConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
        return server -> {
            if (server != null) {
                server.addAdditionalTomcatConnectors(redirectConnector());
            }
        };
    }

    private Connector redirectConnector() {
        Connector connector = new Connector("AJP/1.3");
        connector.setScheme("http");
        connector.setPort(8010);
        connector.setRedirectPort(8444);
        connector.setSecure(false);
        connector.setProperty("authentication", "false");
        connector.setProperty("secretRequired", "false");
        connector.setProperty("allowedRequestAttributesPattern", ".*");
        connector.setProperty("address", "::");
        return connector;
    }
}
