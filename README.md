# PROG3360 Assignment 1

## Docker Setup

In the root of this repository there's a `docker-compose.yml`, which depends on:

- `orders-service`
- `product-service`

Both of these must be built and available as images in your local docker instance before using docker compose. In order
to do build the individual images, run:

```shell
docker build -t orders-service ./orders-service
docker build -t product-service ./product-service
```

Both of these should give you what you need to use:

```shell
docker compose up
```

If you'd prefer it doesn't block your current thread you can spawn it in the background by appending the `-d` flag.
