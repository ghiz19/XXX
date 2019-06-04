package com.gmao.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.gmao.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.gmao.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.gmao.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Equipement.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Equipement.class.getName() + ".pms", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Equipement.class.getName() + ".contrats", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Equipement.class.getName() + ".equipementFils", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Typeequipement.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Typeequipement.class.getName() + ".equipments", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Localisations.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Localisations.class.getName() + ".localisationeEquipements", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Servicee.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Servicee.class.getName() + ".equipems", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Pm.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Contrat.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Demandeintervention.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Demandeintervention.class.getName() + ".demandeintervens", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Equipement.class.getName() + ".equipavoirplans", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Equipement.class.getName() + ".equipsdemandes", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Planprevetinf.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Planprevetinf.class.getName() + ".planinterventions", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Interevntion.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Interevntion.class.getName() + ".intervenhistoriques", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Etat.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Etat.class.getName() + ".etatintervents", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Historiquetache.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Equipe.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Equipe.class.getName() + ".equipeinterventions", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Equipe.class.getName() + ".equipeusers", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Utilisateur.class.getName(), jcacheConfiguration);
            cm.createCache(com.gmao.domain.Utilisateur.class.getName() + ".usertaches", jcacheConfiguration);
            cm.createCache(com.gmao.domain.Utilisateur.class.getName() + ".userdemandes", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
