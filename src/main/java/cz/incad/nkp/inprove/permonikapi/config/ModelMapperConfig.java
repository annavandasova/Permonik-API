package cz.incad.nkp.inprove.permonikapi.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.module.jdk8.Jdk8Module;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ModelMapperConfig {

    @Bean
    @Primary
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setCollectionsMergeEnabled(false);
        modelMapper.getConfiguration().setSkipNullEnabled(false);
        modelMapper.getConfiguration().setDeepCopyEnabled(true);

        modelMapper.registerModule(new Jsr310Module());
        modelMapper.registerModule(new Jdk8Module());

        return modelMapper;
    }
}
