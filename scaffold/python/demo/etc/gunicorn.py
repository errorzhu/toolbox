bind = "0.0.0.0:5000"
workers = 4
backlog = 2048
pidfile = "@work_dir@/pid"
accesslog = "@work_dir@/logs/access.log"
errorlog = "@work_dir@/logs/debug.log"
timeout = 600
debug = False
capture_output = True