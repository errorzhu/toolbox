#!/bin/bash
root_dir=$(cd "$(dirname $0)";pwd)
root_dir=$(readlink -f "$root_dir")
work_dir=$(dirname $root_dir)
cd $work_dir
######################main################

java -cp "$work_dir/etc:$work_dir/*" org.springframework.boot.loader.JarLauncher
