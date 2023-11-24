SCRIPT_DIR=$(dirname "$0")
PROJECT_ROOT_DIR=$(cd $(dirname "$0"); cd ../..; pwd)

# exit script if namespace not exist
NAMESPACE=istio-test
if [[ -z $(kubectl get namespace "${NAMESPACE}" --no-headers --output=go-template="{{.metadata.name}}") ]];then
    echo "NAMESPACE ${NAMESPACE} not found."
    exit
fi
echo "current namespace is ${NAMESPACE}"

# delete api-client and api-server all resources
echo "clean up deployments, services, virtualservices, gateways resources for springboot template"
resources=(deployments services virtualservices gateways)
for resource in "${resources[@]}";
do
  for resource_name in $(kubectl get -n "${NAMESPACE}" "$resource" -o name);
  do
    echo "clean up $resource: $resource_name"
    kubectl delete "$resource_name" -n "${NAMESPACE}";
  done
done

# wait for 6 second for container clean up
sleep 6

# delete images
services=(api-client api-server)
echo "clean up api-client and api-server images"
for service in "${services[@]}";
do
  echo "delete image: $service"
  docker rmi "$service":latest
done

# delete namespace
#echo "delete $NAMESPACE"
#kubectl delete namespace istio-test