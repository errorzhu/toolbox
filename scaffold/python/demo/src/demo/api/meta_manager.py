from flask import jsonify, Blueprint
import os
import logging
from constant import app_constant
from flasgger import swag_from
from repository.model.model import PackageInfo

logger = logging.getLogger(app_constant.LOG_NAME)
current_dir = os.path.dirname(__file__)
project_dir = os.path.abspath(os.path.join(current_dir, "../../../"))
swagger_yml_dir = os.path.join(project_dir, "src", "bdp_agent", "swagger")

meta_manager_blueprint = Blueprint("meta_manager", __name__)


@meta_manager_blueprint.route("/v1/packages", methods=["GET"])
@swag_from(os.path.join(swagger_yml_dir, "package.yml"))
def get_package_info():
    return jsonify({"result": list(PackageInfo.select().dicts())})
