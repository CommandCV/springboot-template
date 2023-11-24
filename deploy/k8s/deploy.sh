SCRIPT_DIR=$(dirname "$0")
PROJECT_ROOT_DIR=$(cd $(dirname "$0"); cd ../..; pwd)

# create namespace if not exist
NAMESPACE=istio-test
if [[ -z $(kubectl get namespaces | grep "${NAMESPACE}") ]];then
    echo "create namespace: ${NAMESPACE}"
    kubectl create namespace "${NAMESPACE}"
    kubectl label namespace "${NAMESPACE}" istio-injection=enabled
fi
echo "current namespace is ${NAMESPACE}"

# package the module
cd "$PROJECT_ROOT_DIR"
# shellcheck disable=SC2005
echo "cd $(pwd)"
echo "package for api-client and api-server"
mvn clean package

# build images
services=(api-client api-server)
echo "build api-client and api-server images"
for service in "${services[@]}";
do
  echo "build image: $service"
  docker build -t "$service":latest ./"$service"
done

cd "$SCRIPT_DIR"
echo "cd $(pwd)"
# deployment gateway
kubectl apply -f ./istio-gateway.yaml

# deployment api-client and api-server service
echo "deployment gateway..."
kubectl apply -f istio-gateway.yaml
echo "deployment api-client..."
kubectl apply -f deployment-api-client.yaml
echo "deployment api-server..."
kubectl apply -f deployment-api-server.yaml

echo "done."