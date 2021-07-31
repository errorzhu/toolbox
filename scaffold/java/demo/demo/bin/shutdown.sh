#!/bin/bash
root_dir=$(cd "$(dirname $0)";pwd)
root_dir=$(readlink -f "$root_dir")
work_dir=$(dirname $root_dir)
cd $work_dir
######################main################


if [ -f $work_dir/pid ];then
  cat $work_dir/pid |  xargs kill -15
  rm -f $work_dir/pid
fi

echo "demo  stopped !!!"