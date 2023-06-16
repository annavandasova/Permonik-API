package cz.incad.nkp.inprove.permonikapi.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(basePackages = { "cz.incad.nkp.inprove.permonikapi.metaTitle", "cz.incad.nkp.inprove.permonikapi.specimen" })
public class SearchContext {

    @Bean
    public SolrClient solrClient(@Value("${solr.host}") String solrHost){
        return new HttpSolrClient.Builder(solrHost).build();
    }
}
