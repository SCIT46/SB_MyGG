package com.mygg.sb.match.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MatchDataFetcherService {

    @Autowired
    private GlobalMatchQueueService globalMatchQueueService;

    private static final int API_RATE_LIMIT = 100;  // 예시: 100회/10분 (라이엇 API 제한)
    private static long lastRequestTime = 0;
    private static long rateLimitResetTime = 0;

    @Async
    public void fetchMatchData() {
        while (true) {
            String matchId = globalMatchQueueService.getNextMatchId();
            if (matchId == null) {
                System.out.println("No more matches in the queue.");
                break;
            }

            if (System.currentTimeMillis() < rateLimitResetTime) {
                long waitTime = rateLimitResetTime - System.currentTimeMillis();
                System.out.println("Rate limit reached. Waiting for " + waitTime + "ms");
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                makeApiRequest(matchId);
                lastRequestTime = System.currentTimeMillis();
                rateLimitResetTime = lastRequestTime + TimeUnit.MINUTES.toMillis(10);
            } catch (Exception e) {
                System.out.println("Error fetching data for match ID: " + matchId);
                globalMatchQueueService.addMatchIdsToQueue(List.of(matchId));  // 큐에 재추가
                try {
                    Thread.sleep(5000);  // 5초 후 재시도
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void makeApiRequest(String matchId) throws Exception {
        // 라이엇 API 요청 로직 (예시)
        System.out.println("Requesting data for match ID: " + matchId);
        // 실제 API 호출 처리 코드 구현
    }
}
