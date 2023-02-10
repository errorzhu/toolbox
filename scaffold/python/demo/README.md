# demo

## 依赖

python3.8

## 开发

参考wiki 创建远程开发环境

使用 requirements-dev.txt 下载所需依赖

使用flake8做代码检查

使用black做格式化工具

## 构建
构建安装包,需要安装apache ant 1.9

```
#在项目根目录执行
ant clean dist
#在dist目录下获取安装包
bdp_agent_x.y.z.tar.gz
```

## 部署


1 安装conda

```
1.wget https://mirrors.tuna.tsinghua.edu.cn/anaconda/miniconda/Miniconda3-py38_4.8.2-Linux-x86_64.sh
2.sh Miniconda3-py38_4.8.2-Linux-x86_64.sh
3.source ~/.bashrc
```

2 创建虚拟环境

```
conda create -n venv  --clone base
```

3 激活虚拟环境

```
conda activate venv
```

4 上传安装包
将安装包上传到待部署服务器

5 在虚拟环境下下载依赖

```
tar -zxvf bdp_agent_x.y.z.tar,gz
cd bdp_agent_x.y.z
pip install -r requirements.txt -i https://pypi.tuna.tsinghua.edu.cn/simple
或者离线安装
pip install --no-index --find-links=/packages/ -r requirements.txt

```

6 配置bdp agent节点到所有被控制节点的ssh

```
ssh-keygen
ssh-copy-id -i ~/.ssh/id_rsa.pub root@ip1
ssh-copy-id -i ~/.ssh/id_rsa.pub root@ip2
ssh-copy-id -i ~/.ssh/id_rsa.pub root@ip3
...

```


7 安装并启动

````
cd bdp_agent_x.y.z
bash bin/install.sh
bash bin/startup.sh

````

8 确认
```
cd bdp_agent
ps -ef | grep `cat pid`

```


