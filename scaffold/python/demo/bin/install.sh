#!/bin/bash
root_dir=$(cd "$(dirname $0)";pwd)
root_dir=$(readlink -f "$root_dir")
work_dir=$(dirname $root_dir)
cd $work_dir
######################main################
cd ..

if [  ! -L  bdp_agent ];then
  ln -s $work_dir bdp_agent
fi

cd $work_dir

find $work_dir -name '__pycache__' -exec rm -rf  {} \;
python  -m compileall $work_dir/src
find $work_dir/src -name '*.py' -type f -print -exec rm {} \;
find $work_dir/src -name '*.pyc' -exec rename ".cpython-38" "" {} \;
find $work_dir/src -name '*.pyc' -execdir mv {} .. \;
#find $work_dir/src -name 'main.pyc' -exec rename ".pyc" ".py" {} \;
find $work_dir/src -name '__pycache__' -prune -exec rmdir {} \;

if [  "$?" == "0"  ];then
  echo "install successful !!!"
fi