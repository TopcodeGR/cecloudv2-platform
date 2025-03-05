docker build  --no-cache \
  --build-arg REPOSILITE_USERNAME=admin \
  --build-arg REPOSILITE_PASSWORD=Q7dDy5Oc2I3aOrLNW2pL8wt/qeJjirs7Xuwit25bt73wlLCfQ9hXk3Zq8KM14yzg \
  -t registry.digitalocean.com/topcode/cecloudv2-platform:dev .


docker push registry.digitalocean.com/topcode/cecloudv2-platform:dev
