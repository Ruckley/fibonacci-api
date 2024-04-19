# Overview

This is a simple http service that calculates the nth Fibonacci number.

## Fibinacci Algortihm

The algorithm for calculating the fibonacci number is a simple iterative method.
As I come from a functional background my first though was to use tail recursion but java cannot guarantee a tail recursive
method will be optimised and so stack overflow was an issue, hence the iterative method.
I did have a quick look at other fibonacci algorithms including one that uses matrix calculations but felt it was a more 
than was needed for this exercise as the iterative method can calculate n over 100000 in reasonable time.

## Fibinacci API

I have used spring webflux to create the http service. I used webflux as it is built on reactive which allows routes to 
be non-blocking. I thought this would be important as large n requests could be long running so being able to handle requests 
concurrently will be important.<br>
Any errors thrown during execution of a route will be caught and the api will return an unexpected error message and the
trace will be logged.<br>
I have taken advantage of the webflux to handle any malformed requests. If a malformed request is recieved the service will
return a 400 bad request.<br>
The fib route is designed to have a timeout of 1 minuite. This can be set in the config yaml.<br>

## Docker container

The image created by the docker file is a simple image with java17 installed. It will pull the service jar file from the
target directory on build

## Kubernetes Deployment

The Kubernetes yaml creates a deployment for the fibonacci service with ready and live probes that mae use of the health_check
route.<br>
A horizontal pod autoscaler is also created to manage scaling for the service pods. As the service route is non-blocking
this will not greatly improve performance but it should make it more resilient. The minimum replicants is set to 2 so that
if one goes down there will always be another to serve requests until another is spun up.

## Testing

If you wish to run the tests you can use<br>
```mvn test```

## Deployment

The makefile will create the service jar, the docker image and deploy it to kubernetes with the fib.yaml. It needs the docker
engine and minikube to be running.<br>
```make all```
This will create a minikube tunnel so that the service can be accessed. The ip address and port will be displayed.
