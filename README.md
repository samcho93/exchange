# 실시간 환율 계산기

실시간 환율 데이터를 기반으로 최대 4개 국가의 통화를 한 화면에서 동시에 변환할 수 있는 Android 앱입니다.

## 개요

- 최대 **4개 통화**를 선택하여 한 화면에서 실시간 환율 확인
- **양방향 입력**: 어느 통화 행에서든 금액을 입력하면 나머지 통화가 자동 계산
- **30개 주요 통화** 지원 (USD, KRW, EUR, JPY, GBP, CNY 등)
- **오프라인 지원**: Room DB 캐시로 네트워크 불가 시에도 마지막 환율 사용
- **자동 갱신**: 60초 간격으로 최신 환율 자동 업데이트
- **통화 선택 기억**: 앱 재시작 시에도 마지막 선택한 통화 유지

## 사용법

1. 앱 실행 시 기본 4개 통화(USD, KRW, EUR, JPY)가 표시됩니다.
2. 원하는 통화 행의 금액 입력 필드에 숫자를 입력하면 다른 통화가 자동 계산됩니다.
3. 국기+통화코드를 탭하면 다른 통화로 변경할 수 있습니다.
4. 우측 하단 `+` 버튼으로 통화를 추가하고 (최대 4개), `X` 버튼으로 삭제합니다 (최소 2개).
5. 상단 새로고침 버튼으로 수동 환율 갱신이 가능합니다.

## 기술 스택

| 분류 | 기술 |
|------|------|
| 언어 | Kotlin |
| UI | Jetpack Compose + Material3 |
| 아키텍처 | MVVM + Clean Architecture |
| DI | Hilt (Dagger) |
| 네트워크 | Retrofit2 + OkHttp + Gson |
| 로컬 DB | Room |
| 비동기 | Coroutines + Flow |
| 환율 API | [Frankfurter API](https://www.frankfurter.app/) (무료, API 키 불필요) |
| 빌드 | Gradle 8.5, AGP 8.3.0, Kotlin 1.9.22 |
| 최소 SDK | Android 7.0 (API 24) |

## 프로젝트 구조

```
app/src/main/java/com/project/exchange/
├── ExchangeApp.kt                    # @HiltAndroidApp Application
├── MainActivity.kt                   # @AndroidEntryPoint 진입점
│
├── domain/                           # Domain 레이어
│   ├── model/
│   │   ├── Currency.kt               # 통화 모델 + 30개 지원 통화 목록
│   │   └── ExchangeRate.kt           # 환율 데이터 모델
│   └── usecase/
│       └── ConvertCurrencyUseCase.kt  # 환율 변환 계산 로직
│
├── data/                             # Data 레이어
│   ├── remote/
│   │   ├── ApiConstants.kt           # API Base URL
│   │   ├── ExchangeApiService.kt     # Retrofit API 인터페이스
│   │   └── ExchangeApiResponse.kt    # API 응답 모델
│   ├── local/
│   │   ├── ExchangeDatabase.kt       # Room Database
│   │   ├── ExchangeRateDao.kt        # Room DAO
│   │   └── ExchangeRateEntity.kt     # Room Entity
│   └── repository/
│       └── ExchangeRepository.kt     # API 호출 + Room 캐시 (오프라인 폴백)
│
├── di/                               # 의존성 주입
│   ├── NetworkModule.kt              # Retrofit, OkHttp, ApiService 제공
│   └── DatabaseModule.kt             # Room DB, DAO, SharedPreferences 제공
│
└── ui/                               # UI 레이어
    ├── theme/
    │   └── Theme.kt                  # Material3 테마 (파란색 계열)
    ├── viewmodel/
    │   └── ExchangeViewModel.kt      # 환율 상태 관리, 양방향 변환, 자동 갱신
    ├── screens/
    │   └── ExchangeScreen.kt         # 메인 화면 (Scaffold + 통화 행 목록)
    └── components/
        ├── CurrencyRow.kt            # 국기 + 통화코드 + 금액 입력 행
        └── CurrencySelector.kt       # 통화 선택 다이얼로그 (검색 기능)
```

## 아키텍처

```
┌─────────────┐     ┌──────────────────┐     ┌─────────────────┐
│   UI Layer  │     │  Domain Layer    │     │   Data Layer    │
│             │     │                  │     │                 │
│ Screen      │────▶│ ConvertCurrency  │     │ Repository      │
│ ViewModel   │     │ UseCase          │     │  ├─ API Service │
│ Components  │     │                  │     │  └─ Room DAO    │
└─────────────┘     └──────────────────┘     └─────────────────┘
       │                                            ▲
       └────────────────────────────────────────────┘
                   ViewModel → Repository → DataSource (단방향)
```

## 빌드 및 실행

```bash
# 빌드
./gradlew assembleDebug

# 설치 및 실행
adb install app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.project.exchange/.MainActivity
```

## 지원 통화 (30개)

| 통화 | 국가 | 통화 | 국가 |
|------|------|------|------|
| USD | 미국 달러 | KRW | 한국 원 |
| EUR | 유로 | JPY | 일본 엔 |
| GBP | 영국 파운드 | CNY | 중국 위안 |
| AUD | 호주 달러 | CAD | 캐나다 달러 |
| CHF | 스위스 프랑 | HKD | 홍콩 달러 |
| SGD | 싱가포르 달러 | SEK | 스웨덴 크로나 |
| NOK | 노르웨이 크로네 | MXN | 멕시코 페소 |
| INR | 인도 루피 | BRL | 브라질 헤알 |
| NZD | 뉴질랜드 달러 | ZAR | 남아공 랜드 |
| TRY | 터키 리라 | THB | 태국 바트 |
| PLN | 폴란드 즈워티 | DKK | 덴마크 크로네 |
| IDR | 인도네시아 루피아 | CZK | 체코 코루나 |
| HUF | 헝가리 포린트 | ILS | 이스라엘 셰켈 |
| PHP | 필리핀 페소 | MYR | 말레이시아 링깃 |
| RON | 루마니아 레우 | BGN | 불가리아 레프 |
