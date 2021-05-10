#  URL Shortening
> 무신사 과제테스트

## Assignment content
> URL을 입력받아 짧게 줄여주고, Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service

* URL 입력폼 제공 및 결과 출력
* URL Shortening Key는 8 Character 이내로 생성되어야 합니다.
* 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다.
* 동일한 URL에 대한 요청 수 정보를 가져야 한다(화면 제공 필수 아님)
* Shortening된 URL을 요청받으면 원래 URL로 리다이렉트 합니다.
* Database 사용은 필수 아님
* Unit test 및 Integration test 작성. (가산점 부여항목)

## How to run
```bash
$ ./gradlew build
$ java -jar ./build/libs/shorturl-0.0.1-SNAPSHOT.jar
```

## Directory Structure
```bash
.
├── main
│   ├── java
│   │   ├── controller
│   │   │   ├── MainController
│   │   │   └── ConvertController
│   │   ├── domain
│   │   │   ├── ShortUrlRepository
│   │   │   └── ShortUrl
│   │   ├── dto
│   │   │   ├── ShortUrlResponse
│   │   │   └── UrlType
│   │   ├── service
│   │   │   └── ConvertService
│   │   └── Application
│   └── resources
│       └── application.yml
└── test
    └── java
        ├── controller
        │   └── ConvertControllerTest
        ├── service
        │   └── ConvertServiceTest
        └── ApplicationTest
```
