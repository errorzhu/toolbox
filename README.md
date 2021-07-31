# toolbox
common tools
有用的信息源
https://jar-download.com/?search_box=ngdbc
json
https://www.jsonschema2pojo.org/
docker 镜像加速
https://yeasy.gitbook.io/docker_practice/install/mirror
spring
https://start.spring.io/
数据库排名
https://db-engines.com/en/ranking
apache 软件下载
http://archive.apache.org/dist/zookeeper/
maven nexus
http://mirrors.163.com/.help/maven.html
科学计算

https://www.numpy.org.cn/reference/
https://www.pypandas.cn/

springboot例子
https://github.com/ityouknow/spring-boot-examples
https://github.com/527515025/springBoot

优秀blog
http://www.lcsays.com/



-------------------------------------------------------
yum相关
wget http://mirrors.163.com/.help/CentOS6-Base-163.repo
wget http://mirrors.163.com/.help/CentOS7-Base-163.repo
http://mirrors.aliyun.com/repo/epel-7.repo

yum install --downloadonly --downloaddir=/download lrzsz
-------------------------------------------------------
docker

docker images | awk '{if ($1 ~ /^(openshift|centos)/) print $1 " " $2 " " $3 }' | tr -c "a-z A-Z0-9_.\n-" "%" | while read REPOSITORY TAG IMAGE_ID
do
  echo "== Saving $REPOSITORY $TAG $IMAGE_ID =="
  docker  save   -o /path/to/save/$REPOSITORY-$TAG-$IMAGE_ID.tar $IMAGE_ID
done

docker images |grep -v REPOSITORY | awk '{ print $1 " " $2 " " $3 }' | tr -c "a-z A-Z0-9_.\n-" "%" | while read REPOSITORY TAG IMAGE_ID
do
  echo "== Saving $REPOSITORY $TAG $IMAGE_ID =="
  docker  save   -o /home/docker/$REPOSITORY-$TAG-$IMAGE_ID.tar $IMAGE_ID
done




-------------------------------------------------------
python
pip install pyspark -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install pyscaffold -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install Faker -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install pyinotify -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install watchdog -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install avro -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install avro-python3  -i https://pypi.tuna.tsinghua.edu.cn/simple
pip --default-timeout=100 install numpy  matplotlib -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install tox  -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install virtualenv  -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install torch==1.8.1+cpu torchvision==0.9.1+cpu torchaudio===0.8.1 -f https://download.pytorch.org/whl/torch_stable.html

pip download -d package -r requirements.txt --only-binary=:all: --platform linux_x86_64  -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install --no-index --find-links=/packages/ -r requirements.txt

pip install ansible==2.9.10 -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install Flask -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install flask-cors -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install pytest-mock -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install intensio-obfuscator -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install pyminifier -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install pyarmor -i https://pypi.tuna.tsinghua.edu.cn/simple
pip install flasgger -i  https://pypi.tuna.tsinghua.edu.cn/simple
pip install gunicorn -i  https://pypi.tuna.tsinghua.edu.cn/simple
pip install peewee -i  https://pypi.tuna.tsinghua.edu.cn/simple
pip install pymysql -i  https://pypi.tuna.tsinghua.edu.cn/simple
pip install flake8 -i  https://pypi.tuna.tsinghua.edu.cn/simple
pip install black -i  https://pypi.tuna.tsinghua.edu.cn/simple

pip install numpy -i  https://pypi.tuna.tsinghua.edu.cn/simple
pip install pandas -i  https://pypi.tuna.tsinghua.edu.cn/simple
pip install ray -i  https://pypi.tuna.tsinghua.edu.cn/simple
pip install dask -i  https://pypi.tuna.tsinghua.edu.cn/simple
pip install modin -i https://mirrors.aliyun.com/pypi/simple/
-------------------------------------------------------
mvn
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=myapp -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

mvn archetype:generate -DinteractiveMode=false -DgroupId=org.example -DartifactId=spark-ee -Dversion=1.0-SNAPSHOT -DarchetypeGroupId=org.scala-tools.archetypes -DarchetypeArtifactId=scala-archetype-simple -DarchetypeVersion=1.2 

mvn archetype:generate   -DgroupId=org.zhu.flink -DartifactId=flink-test    -DarchetypeGroupId=org.apache.flink -DarchetypeArtifactId=flink-quickstart-scala     -DarchetypeVersion=1.11.1  -DinteractiveMode=false

mvn archetype:generate   -DgroupId=org.zhu.flink -DartifactId=flink-test    -DarchetypeGroupId=org.apache.flink -DarchetypeArtifactId=flink-quickstart-java     -DarchetypeVersion=1.11.1  -DinteractiveMode=false


-Dmaven.test.skip=true  不编也不执行
-DskipTests 

-------------------------------------------------------
maven clean
for i in `find  /maven/m2/repository/ -name "_remote.repositories"` ;do rm -f $i ;done
for i in `find  /maven/m2/repository/ -name "*.lastUpdated"` ;do rm -f $i ;done
-------------------------------------------------------

tar
tar -zcvf img.tar.gz img1 img2
tar -zxvf
tar xvf xxx.tar
xz -d xxx.tar.xz
tar -xvJf Python-3.7.2.tar.xz
-------------------------------------------------------
ssh
ssh-keygen
ssh-copy-id -i ~/.ssh/id_rsa.pub root@192.168.9.234
------------------------------------------------------- 
shell
-d ：判断制定的是否为目录
-z：判断制定的变量是否存在值
-f：判断制定的是否为文件
-L:判断制定的是否为符号链接
-r：判断制定的是否可读
-w:判断制定的是否可写
-x：判断存在的对象是否可以执行
!：测试条件的否定符号

-------------------------------------------------------
find
find . -name "*.text" -exec sed -i 's/curl/curl -H "Content-Type:application/json"/g' {} \;
-------------------------------------------------------
vagrant

vagrant plugin install vagrant-disksize
vagrant box list
vagrant up
vagrant reload
vagrant halt
vagrant destroy

-------------------------------------------------------
mysql
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;
-------------------------------------------------------
python -m pwiz -e mysql -u root -H 192.168.9.238 --password -p 3306  cosmo_bdp > model.py
