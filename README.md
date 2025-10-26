# 공통 데이터 조회 방식에 따른 성능 측정

## 구현 방식
<img width="670" height="210" alt="구현 방식" src="https://github.com/user-attachments/assets/c2d0943c-8612-4b79-84a2-b64ba4a147df" />

1. **SQL**(**monolith**): `http://localhost:8080/api/service-requests`
2. **API 건별 요청**(**microservice**): `http://localhost:8081/api/service-requests`
3. **API 일괄 요청**(**microservice**): `http://localhost:8081/api/service-requests/batch`
4. **API 일괄 요청 & 로컬 캐시**(**microservice**): `http://localhost:8081/api/service-requests/cache`

## 측정 결과
4가지 방식으로 상담 이력 조회 수(30건, 100건, 1000건, 10000건)에 따라 10번씩 측정하였다.<br>
세부적인 조회 시간보다는 방식 간의 상대적인 성능 차이를 비교하는 데에 중점을 두었다.

| 조회 데이터 수  | SQL   | API 건별 요청 | API 일괄 요청 | API 일괄 요청 & 로컬 캐시 |
|-----------|-------|-----------|-----------|-------------------|
| **30**    | 9ms   | 356ms     | 20ms      | 10ms              |
| **100**   | 10ms  | 798ms     | 24ms      | 12ms              |
| **1000**  | 25ms  | 6686ms    | 90ms      | 27ms              |
| **10000** | 118ms | 67840ms   | 142ms     | 75ms              |
