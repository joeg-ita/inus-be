package it.joeg.inus.be.configuration.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ehcache events logger
 */
public class CacheEventLogger implements CacheEventListener<Object, Object> {
    
    private static final Logger LOG = LoggerFactory.getLogger(CacheEventLogger.class);

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        LOG.info("Key: {} | EventType: {} | Old value: {} | New value: {}",
             cacheEvent.getKey(), cacheEvent.getType(), cacheEvent.getOldValue(), 
             cacheEvent.getNewValue());
    }

}
