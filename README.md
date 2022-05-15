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
