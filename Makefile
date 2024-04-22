all: create_jar create_docker_image_in_minikube_cache deploy_on_kubernetes minikube_tunnel

create_jar:
	mvn clean install

create_docker_image_in_minikube_cache:
	eval $(minikube docker-env)
	docker build -t fibonacci .

deploy_on_kubernetes:
	kubectl apply -f fib.yaml

minikube_tunnel:
	minikube service fibonacci-nodeport --url







