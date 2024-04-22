# How to run

The Makefile has teh details on how to run the Fibonacci service. If you run make all it will create the jar, the docker
image and deploy it to minikube. The Makefile expects the docker engine and minikube to be running.<br>
This section of the makefile is so that the image is saved to the minikube cache and can be used from three rather than 
an image repo. This is also why "imagePullPolicy: IfNotPresent" is set in the fib.yaml.
If you want to run the tests you can use
```mvn test```

# Overview

This is a simple http service that calculates the nth Fibonacci number.

## Fibonacci Algorithm

The algorithm for calculating the fibonacci number is a simple iterative method.
As I come from a functional background my first though was to use tail recursion but java cannot guarantee a tail recursive
method will be optimised and so stack overflow was an issue, hence the iterative method.
I did have a quick look at other fibonacci algorithms including one that uses matrix calculations but felt it was a more 
than was needed for this exercise as the iterative method can calculate n over 100000 in reasonable time on my machine.

## Fibonacci API

I have used spring webflux to create the http service. I used webflux as it is built on reactive which allows routes to 
be non-blocking. I thought this would be important as large n requests could be long running so being able to handle requests 
concurrently is important.<br>
Any errors thrown during execution of a route will be caught and the api will return an unexpected error message and the
trace will be logged.<br>
I have taken advantage of the webflux to handle any malformed requests. If a malformed request is received the service will
return a 400 bad request.<br>
The fib route has a timeout that can be set in the config file. I have set it to 15 seconds which on my machine is enough
to calculate n of 6 digits. If you want to change this value above 30 please also change the grace period in the fib.yaml as this
will ensure scaling doesn't shut down a pod mid-calculation.

## Docker container

The image created by the docker file is a simple image with java17 installed. It will pull the service jar file from the
target directory on build

## Kubernetes Deployment

The Kubernetes yaml creates a deployment for the fibonacci service with ready and live probes that make use of the health_check
route.<br>
A horizontal pod autoscaling is also created to manage scaling for the service pods. As the service route is non-blocking
this will not greatly improve performance, but it should make it more resilient. The minimum replicates is set to 3 so that
if one goes down there will always be another to serve requests until another is spun up.
I have set a request limit of 0.2. This will be overkill for most requests but as large n will require a large amount of
cpu resources it is a good starting point. No CPU limit is set as I have found that setting CPU limits causes more issues
than it solves. As three is only a single type of node and pod in this deployment there shouldn't be resource starvation
issues.

## Testing

There are 2 test classes one for unit testing the Fibonacci algorithm and one for integration testing the fibonacci API
If you wish to run the tests you can use<br>
```mvn test```

## Deployment

The makefile will create the service jar, the docker image and deploy it to kubernetes with the fib.yaml. It needs the docker
engine and minikube to be running.<br>
```make all```
This will create a minikube tunnel so that the service can be accessed. The ip address and port will be displayed.
