使用grafana+exporter+prometheus构建自己的hadoop 监控系统

可以使用ambari 2.7.5 写好的dashboard,grafana 要用2.6的版本
docker pull prom/node-exporter
docker pull prom/prometheus
docker pull grafana/grafana

启动 node-exporter
docker run -d -p 9100:9100 -v "/proc:/host/proc:ro" -v "/sys:/host/sys:ro" -v "/:/rootfs:ro"  prom/node-exporter

-------------------------------------------------------
vim prometheus.yml

global:
  scrape_interval:     60s
  evaluation_interval: 60s
 
scrape_configs:
  - job_name: prometheus
    static_configs:
      - targets: ['localhost:9090']
        labels:
          instance: prometheus
 
  - job_name: linux
    static_configs:
      - targets: ['192.168.3.100:9100']
        labels:
          instance: localhost
		  
启动prometheus
docker run  -d -p 9090:9090 -v /opt/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus

启动grafana

docker run -d -p 3000:3000 --name=grafana -v /opt/grafana-storage:/var/lib/grafana grafana/grafana

配置jmx_exporter

vi hadoop-env.sh

export HDFS_DATANODE_OPTS="-Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.port=1234 -javaagent:/opt/hadoop-monitor/jmx_prometheus_javaagent-0.15.0.jar=4321:/opt/hadoop-monitor/hadoop/datanode.yaml $HDFS_NAMENODE_OPTS"


vi /opt/hadoop-monitor/hadoop/datanode.yaml

---
startDelaySeconds: 0
hostPort: 192.168.9.234:1234
#jmxUrl: service:jmx:rmi:///jndi/rmi://127.0.0.1:1234/jmxrmi
ssl: false
lowercaseOutputName: true
lowercaseOutputLabelNames: true
whitelistObjectNames:
  - 'Hadoop:service=DataNode,name=*'
  - 'Hadoop:service=DataNode,name=MetricsSystem,sub=*'
blacklistObjectNames:
  - 'Hadoop:service=DataNode,name=RpcActivity*'
  - 'Hadoop:service=DataNode,name=RpcDetailedActivity*'
  - 'Hadoop:service=DataNode,name=UgiMetrics'
rules:
  # MetricsSystem
  - pattern: 'Hadoop<service=(.*), name=MetricsSystem, sub=(.*)><>(.*): (\d+)'
    attrNameSnakeCase: true

-------------------------------------------------------

参考文章
https://zhuanlan.zhihu.com/p/357378691
https://prometheus.io/docs/instrumenting/exporters/


