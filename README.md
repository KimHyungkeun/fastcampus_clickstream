# fastcampus_clickstream

# 1. 개요
**1) 용어 정리** 
 - 클릭스트림(ClickStream) : 사용자가 웹사이트를 탐색한 내역을 확인하는 것
   
**2) 기술스택**
 - CentOS7 (OS)
 - Java OpenJDK 1.8.0_372
 - Docker 24.0.2 
 - Docker Compose 2.18.1 (Kafka를 docker-compose를 이용해 띄우기 위함)
 - Kafka confluentinc:7.0.1
 - Flink 1.15.0
 - MySQL 8.0.33 MySQL Community Server

  
  
# 2. 아키텍처
1) Log Generator : 로그 생성기 (Java)
2) Kafka : 실시간 스트리밍 메시지 큐 버퍼 (Kafka)
3) ClickStream Analyzer : 클릭스트림 Analyzer (Flink, Java)
4) Database : 데이터베이스 (MySQL)
5) Dashboard : 클릭스트림 대쉬보드 (Grafana) 
![image](https://github.com/KimHyungkeun/fastcampus_clickstream/assets/12759500/7abc78f9-78da-42c1-a0ad-7b61ef7b9013)

# 3. To-do
- 실시간 클릭스트림 분석 통계 정보 확인
    - 현재 활성 유저(Session) 정보
    - 초당 광고 배너 클릭 횟수
    - 초당 요청 횟수
    - 초당 에러 코드 발생 횟수
      
- 실시간 대시보드 확인 (Grafana 확인)
  ![image](https://github.com/KimHyungkeun/fastcampus_clickstream/assets/12759500/f4e531de-c5ff-45ce-a2d7-58697c559611)

