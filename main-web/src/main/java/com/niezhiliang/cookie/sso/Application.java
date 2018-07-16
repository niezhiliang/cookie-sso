package com.niezhiliang.cookie.sso;

import org.apache.catalina.Context;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    /**
     * 解决tomcat不能设置.test.com防止cookie.setDomain(".test.com")报错
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer cookieProcessorCustomizer() {
        return new EmbeddedServletContainerCustomizer() {

            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                if (container instanceof TomcatEmbeddedServletContainerFactory) {
                    ((TomcatEmbeddedServletContainerFactory) container)
                            .addContextCustomizers(new TomcatContextCustomizer() {

                                @Override
                                public void customize(Context context) {
                                    context.setCookieProcessor(new LegacyCookieProcessor());
                                }

                            });
                }
            }

        };
    }
}
