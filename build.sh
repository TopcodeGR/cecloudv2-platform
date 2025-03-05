docker build  --no-cache \
  --build-arg REPOSILITE_USERNAME=$REPOSILITE_USERNAME \
  --build-arg REPOSILITE_PASSWORD=$REPOSILITE_PASSWORD \
  -t registry.digitalocean.com/topcode/cecloudv2-platform:dev .


docker push registry.digitalocean.com/topcode/cecloudv2-platform:dev
