#!/bin/bash
root_dir=$(cd "$(dirname $0)";pwd)
root_dir=$(readlink -f "$root_dir")
work_dir=$(dirname $root_dir)
cd $work_dir
######################main################


if [ -f $work_dir/pid ];then
  echo "bdp  agent already started !!!"
  exit 0
fi


nohup java -cp "$work_dir/etc:$work_dir/*" org.springframework.boot.loader.JarLauncher > /dev/null  2>&1 &

echo $! > $work_dir/pid


sleep 3

if [ ! -f $work_dir/pid  ];then
   echo 'bdp agent failed ,please check logs  !!!!'
   exit 255
fi

oldpid=`cat $work_dir/pid`

pid=$(ps -p $oldpid | tail -1 | awk '{ print $1 }')

if echo $pid | egrep -q '^[0-9]+$'; then
    echo "bdp agent started successful !!!!"
else
    rm -f $work_dir/pid
    echo 'bdp agent failed ,please check logs  !!!!'
fi