package net.developer.webappgame.service;

/**
 * Monitoring Service
 */
public interface WatchService {

    /**
     * Start collect information of generating page
     */
    void startWatching();

    /**
     * Start collect information of generated page
     * @return information of generated page
     */
    String endWatching();
}
