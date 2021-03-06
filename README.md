# WhoCanIKnow

Master: [![master](https://github.com/T-bond/WhoCanIKnow/actions/workflows/android.yml/badge.svg)](https://github.com/T-bond/WhoCanIKnow/actions/workflows/android.yml)    
Development: [![development](https://github.com/T-bond/WhoCanIKnow/actions/workflows/android.yml/badge.svg?branch=development)](https://github.com/T-bond/WhoCanIKnow/actions/workflows/android.yml)

# OpenAPI documentation

The OpenAPI documentation is available at [doc/openapi.yml](doc/openapi.yml).

## Generating Retrofit 2 client layer

```shell
mkdir -p app/build/generated/source/retrofit2/
docker run --rm \
    -v $PWD:/local openapitools/openapi-generator-cli generate \
    -i /local/doc/openapi.yaml \
    -g kotlin \
    --additional-properties library=jvm-retrofit2 \
    -o /local/app/build/generated/source/retrofit2/
```

# Video demos

Playlist: [https://www.youtube.com/playlist?list=PLBgu02Z_5kSt0bdjnoTkiy8fo8njHihUj](https://www.youtube.com/playlist?list=PLBgu02Z_5kSt0bdjnoTkiy8fo8njHihUj)

## Program demo

[https://youtube.com/shorts/VVQu8bJRBDc?feature=share](https://youtube.com/shorts/VVQu8bJRBDc?feature=share)

## Architectural presentation

[https://youtu.be/RN1UbEWfSqw](https://youtu.be/RN1UbEWfSqw)

# Tests

## Coverage report

![](doc/Test coverage.png)

## Instrumented tests

![](doc/Instrumented tests.png)

## Unit tests

![](doc/Unit tests.png)

## Crashlytics

New feature: App crashes on the call FAB button

![](doc/Test%20crash.png)

## Analytics

Analytics about opening a person detailed view. Tested with Analytics debug mode, so I don't have to
wait 24 hour to the first reports to fall in.

Debug mode:
`adb shell setprop debug.firebase.analytics.app hu.t_bond.whocaniknow`

![](doc/Analytics.png)