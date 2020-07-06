package pl.strefakursow.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;

@Configuration
public class ETagFilterConfig
{
    @Bean
    public Filter etag()
    {
        return new ShallowEtagHeaderFilter();
    }
}
