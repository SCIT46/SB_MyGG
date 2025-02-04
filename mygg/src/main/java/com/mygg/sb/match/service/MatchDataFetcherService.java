package com.mygg.sb.match.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

@Service
public class MatchDataFetcherService {

    // @Autowired
    // private GlobalMatchQueueService globalMatchQueueService;

    private final ConcurrentLinkedQueue<String> globalQueue = new ConcurrentLinkedQueue<>();

    private static final int API_RATE_LIMIT = 100; // 예시: 100회/10분 (라이엇 API 제한)
    private static long lastRequestTime = 0;
    private static long rateLimitResetTime = 0;
    private boolean isRunning = false;

    /*
     * 시나리오:
     * 1. matchId 리미트에 걸린다
     * 2. queue에 채운다
     * 3. 일정 시간마다 queue를 돌린다.(스케쥴러 사용)
     * 3-1) queue에서 하나씩 빼서 matchId를 요청
     * 3-2) 요청한 api request 헤더에서 시간값을 추출한다
     * 3-3) 그 시간만큼 슬립한다.
     */
    public void fetchMatchData() {
        while (true) {
            // 1. queue에서 matchId를 갖고 온다
            String matchId = globalQueue.poll();

            if (matchId == null) {
                System.out.println("queue가 null 입니다. queue를 종료합니다.");
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

                globalQueue.addAll(globalQueue); // 큐에 재추가
                try {
                    Thread.sleep(5000); // 5초 후 재시도
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

    public void addQueueRequest(String matchId) {
        globalQueue.add(matchId);
    }

}
