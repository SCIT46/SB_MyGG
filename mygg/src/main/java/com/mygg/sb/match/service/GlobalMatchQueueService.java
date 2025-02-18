package com.mygg.sb.match.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mygg.sb.exception.custom.RiotApiTooManyRequests;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.List;

@Service
@Slf4j
public class GlobalMatchQueueService 
{
    private final ConcurrentLinkedQueue<String> globalQueue = new ConcurrentLinkedQueue<>();
    private PublicMatchService publicMatchService;

    @Lazy // 지연 로딩 적용
    @Autowired
    public void setPublicMatchService(PublicMatchService publicMatchService) 
    {
        this.publicMatchService = publicMatchService;
        // ExecutorService를 생성하여 하나의 쓰레드에서만 큐를 처리하도록 함
    }
    
    // 앞선 동작에 끝나고 0.8초 뒤에 한 번씩 작동 (1000 = 1초)
    @Scheduled(fixedDelay = 10000) 
    public void run() throws Exception
    {
    	if(globalQueue.isEmpty()) return;
    	
        try 
        {
        	// System.out.println("======================= run 작동 =======================");
        	// System.out.println("globalQueue size: " + globalQueue.size());
        	
        	publicMatchService.changeJSONToDTOMatchData(globalQueue.peek());
        	globalQueue.poll();
        } 
        catch (RiotApiTooManyRequests e) 
        {
        	// System.out.println("======================= sleep 작동 =======================");
        	// System.out.println(Integer.parseInt(e.getRetryAfter()));
        	
            Thread.sleep(Integer.parseInt(e.getRetryAfter()));
        }
        catch (Exception e)
        {
        	// System.out.println("======================= exception Err=======================");
        	// System.out.println(e.getMessage());
            throw new Exception("Queue System에서 에러가 발생했습니다" + e.getMessage());
        }
    }

    // 매치 ID를 큐에 추가
    public void addAll(List<String> matchIds) 
    {
    	// System.out.println("================== addAll 작동 ==================");
    	// System.out.println(matchIds);
    	
    	// matchIds.forEach(name->System.out.println(name));
    	
        globalQueue.addAll(matchIds);
    }

    // 큐에 남은 매치 수 확인
    public int getQueueSize() 
    {
        return globalQueue.size();
    }

    
}
