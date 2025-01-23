package com.mygg.sb.match.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;

@Service
public class GlobalMatchQueueService {

    private final ConcurrentLinkedQueue<String> globalQueue = new ConcurrentLinkedQueue<>();

    // 매치 ID를 큐에 추가
    public void addMatchIdsToQueue(List<String> matchIds) {
        globalQueue.addAll(matchIds);
    }

    // 큐에서 매치 ID를 하나 꺼내기
    public String getNextMatchId() {
        return globalQueue.poll();  // 큐에서 첫 번째 매치 ID 반환하고 제거
    }

    // 큐에 남은 매치 수 확인
    public int getQueueSize() {
        return globalQueue.size();
    }
}
