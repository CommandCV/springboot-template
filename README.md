#springboot-template
******

A spring boot template with istio integration.

## 1. Install istio
See https://istio.io/latest/docs/setup/getting-started/ .

## 2. Deploy the application
### 1. Building the module with Maven
```shell
cd ${PROJECT_ROOT} 
mvn clean package
```
### 2. Build `api-client` and `api-server` images
```shell
# build `api-client` image
cd api-client/target
docker build -t api-client .
# build `api-server` image
docker build -t api-server .
```
### 3. Create namespace and enable istio injection
```shell
kubectl create ns istio-test
kubectl label namespace istio-test istio-injection=enabled
```
### 4. Deploy 
```shell
cd ${PROJECT_ROOT}/deploy/k8s
# gateway
kubectl apply -f istio-gateway.yaml
# api-client
kubectl apply -f deployment-api-client.yaml
# api-server
kubectl apply -f deployment-api-server.yaml
```
### 5. Test
```shell
curl -X GET "http://localhost/api/hello?name=test"
# hello api client, this is api server, port: 8090 
```