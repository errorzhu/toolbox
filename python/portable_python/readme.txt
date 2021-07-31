python免安装
修改yum源
wget http://mirrors.163.com/.help/CentOS7-Base-163.repo

安装编译环境
yum -y install zlib-devel bzip2-devel openssl-devel ncurses-devel sqlite-devel readline-devel tk-devel gcc make libffi-devel openssl-devel

下载解压python源码
tar -xvJf Python-3.8.10.tar.xz

编译安装
cd /opt/portable_python/Python-3.8.10
./configure --prefix=/opt/portable_python/python --enable-optimizations
make && make install


在bin目录创建脚本 activate

```
#!/bin/bash
root_dir=$(cd "$(dirname -- $0)";pwd)
PATH=$root_dir:$PATH

new_python=$root_dir/python3.8

for file in `grep -rn "/opt/portable_python/python/bin/python3.8" | grep -v activate  | awk -F ":" '{print $1}'
;do  
  sed -i "s#/opt/portable_python/python/bin/python3.8 #$new_python#g" $file ;
done
```


打包
tar -zcvf bdp_agent_python.tar.gz python

source activate




