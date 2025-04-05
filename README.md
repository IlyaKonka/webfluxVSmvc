# Spring WebFlux VS Spring MVC Performance Comparison

## Project Overview

This project aims to compare the performance of Spring WebFlux and Spring MVC by analyzing how the system handles high load with hundreds of simultaneous requests.

## Technologies Used

- **Java**
- **Spring Boot**
- **Spring WebFlux**
- **Spring MVC**
- **Maven**

## Endpoints

### WebFlux Endpoints

- **GET /webflux**: Returns a `Mono` containing a string "somedata" after a specified delay.

### Spring MVC Endpoints

- **GET /blocking**: Returns a string "somedata" after a specified delay.
- **GET /cfuture**: Returns a `CompletableFuture` containing a string "somedata" after a specified delay.
- **GET /callable**: Returns a `Callable` containing a string "somedata" after a specified delay.

## Performance Testing

### JMeter Test Plan

The JMeter test plan for classic Spring MVC(`mvcDemo/blocking-webclient.jmx`) or for WebFlux (`webFluxDemo/webflux-webclient.jmx`) is configured to simulate high load with the following settings:

- **Thread Group**: 500 threads
- **Loop Count**: 30 iterations
- **HTTP Request**: Sends GET requests to the `/blocking?delay=1000` endpoint 
  (or `/webflux?delay=1000`) with a delay of 1 second

### Running the Test

1. Open the JMeter test plan file `mvcDemo/blocking-webclient.jmx` or `webFluxDemo/webflux-webclient.jmx` in JMeter (https://jmeter.apache.org/).
2. Start the test to simulate high load on the Spring MVC / WebFlux endpoint.
3. Analyze the results using the Summary Report and View Results Tree listeners.

## Results
The results of the performance tests:
- **Spring MVC (blocking)**: 
  - Average response time: 2407ms
  - Minimum response time: 1000ms
  - Maximum response time: 3800ms
  - Throughput: 189.7 requests per second


- **Spring WebFlux**:
  - Average response time: 1025ms
  - Minimum response time: 1000ms
  - Maximum response time: 1553ms
  - Throughput: 482.9 requests per second

## License

This project is licensed under the MIT License.