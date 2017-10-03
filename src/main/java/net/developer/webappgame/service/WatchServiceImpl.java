package net.developer.webappgame.service;


import net.developer.webappgame.repository.QueryInfoRepository;
import net.developer.webappgame.repository.QueryInfoRepositoryImpl;

import java.util.List;

public class WatchServiceImpl implements WatchService {

    private long codeTime;
    private int queryCount;
    private int queryTime;

    QueryInfoRepository queryInfoRepository=new QueryInfoRepositoryImpl();

    @Override
    public void startWatching() {
        queryCount=0;
        queryInfoRepository.startWatching();
        codeTime= System.currentTimeMillis();
    }

    @Override
    public String endWatching() {
        codeTime=System.currentTimeMillis()-codeTime;
        List<Integer> queryTimeList=queryInfoRepository.stopWatching();
        queryCount=queryTimeList.size();
        if(queryCount>0) {
            queryTime = queryTimeList.stream().reduce((x, y) -> x + y).get();
        }
        String result=String.format("page:%d ms, db: %d req ( %d ms )",codeTime,queryTimeList.size(),queryTime) ;
        return result;
    }
}
