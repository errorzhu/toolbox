from api import app
from constant import app_constant
import logging.config
import os

current_dir = os.path.dirname(__file__)
project_dir = os.path.abspath(os.path.join(current_dir, "../../"))
if __name__ == "__main__":
    #(todo:zhuyu logrotate killall -s USR1 gunicorn)
    #logging.config.fileConfig(os.path.join(project_dir, "etc", "logging.conf"))
    logger = logging.getLogger(app_constant.LOG_NAME)
    logger.info("starting up bdp agent ........")
    app.run(host="0.0.0.0")
