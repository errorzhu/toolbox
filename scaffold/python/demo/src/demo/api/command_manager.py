from api import swagger
from flask import request, Blueprint
import json
import os
from worker import executor
import threading
import uuid
from storage import app_share_storage
import logging
from constant import app_constant
from flasgger import swag_from
from api import swagger_validation_handler

logger = logging.getLogger(app_constant.LOG_NAME)
current_dir = os.path.dirname(__file__)
project_dir = os.path.abspath(os.path.join(current_dir, "../../../"))
swagger_yml_dir = os.path.join(project_dir, "src", "bdp_agent", "swagger")

command_manager_blueprint = Blueprint("command_manager", __name__)


@command_manager_blueprint.route("/service/command", methods=["POST"])
@swagger.validate(
    "service_command",
    validation_error_handler=swagger_validation_handler.validation_error_inform_error,
)
@swag_from(os.path.join(swagger_yml_dir, "command.yml"), validation=True)
def exec_command():
    data = json.loads(request.get_data(as_text=True))

    return data




def save_command_info(command_id, response, thread):
    lock = threading.Lock()
    try:
        lock.acquire()
        app_share_storage.execution_response_dic[command_id] = (thread, response)
    finally:
        lock.release()


@command_manager_blueprint.route("/command/status/<command_id>", methods=["GET"])
def get_command_status(command_id):
    thread, response = app_share_storage.execution_response_dic.get(command_id)
    return response.status


@command_manager_blueprint.route("/command/response/<command_id>", methods=["GET"])

