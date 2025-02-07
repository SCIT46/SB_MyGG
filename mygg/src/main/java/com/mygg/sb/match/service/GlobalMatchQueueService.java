package com.mygg.sb.match.service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mygg.sb.exception.custom.RiotApiTooManyRequests;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GlobalMatchQueueService 
{
    private final ConcurrentLinkedQueue<String> globalQueue;
    private final PublicMatchService publicMatchService;

    // 앞선 동작에 끝나고 0.8초 뒤에 한 번씩 작동 (1000 = 1초)
    @Scheduled(fixedDelay = 800) 
    public void run() throws Exception
    {
        try 
        {
            publicMatchService.changeJSONToDTOMatchData(globalQueue.poll());
            log.info("=========== queue 시스템 Test ==========");
            log.info("동작완료");
        } 
        catch (RiotApiTooManyRequests e) 
        {
            log.error("queueSystem: 리퀘스트 요청초과" + e.getRetryAfter() + "대기합니다", e);
            log.error("queueSystem: getXAppRateLimit" + e.getXAppRateLimit());
            log.error("queueSystem: getXAppRateLimitCount" + e.getXAppRateLimitCount());
            Thread.sleep(Integer.parseInt(e.getRetryAfter()));
        }
        catch (Exception e)
        {
            throw new Exception("Queue System에서 에러가 발생했습니다" + e.getMessage());
        }
    }

    // 매치 ID를 큐에 추가
    public void addAll(List<String> matchIds) 
    {
        globalQueue.addAll(matchIds);
    }

    // 큐에 남은 매치 수 확인
    public int getQueueSize() 
    {
        return globalQueue.size();
    }

    
}
