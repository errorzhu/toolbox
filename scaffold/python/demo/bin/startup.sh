#!/bin/bash
root_dir=$(cd "$(dirname $0)";pwd)
root_dir=$(readlink -f "$root_dir")
work_dir=$(dirname $root_dir)
cd $work_dir
######################main################



##########substitute gunicorn configuration#######################
sed -i "s#@work_dir@#$work_dir#g" $work_dir/etc/gunicorn.py
sed -i "s#@work_dir@#$work_dir#g" $work_dir/etc/logging.conf


if [ -f $work_dir/pid ];then
  echo "bdp  agent already started !!!"
  exit 0
fi

gunicorn -D  --log-config  $work_dir/etc/logging.conf --chdir $work_dir/src/bdp_agent/  -c $work_dir/etc/gunicorn.py   main:app

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




