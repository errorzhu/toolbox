from flask import Flask
from flasgger import Swagger
from flask_cors import CORS
import os

current_dir = os.path.dirname(__file__)
project_dir = os.path.abspath(os.path.join(current_dir, "../../../"))
app_config_dir = os.path.join(project_dir, "etc", "config.py")

app = Flask(__name__)

app.config.from_pyfile(app_config_dir)

swagger = Swagger(app)
CORS(app, supports_credentials=True)

from api.command_manager import command_manager_blueprint
from api.meta_manager import meta_manager_blueprint

app.register_blueprint(command_manager_blueprint)
app.register_blueprint(meta_manager_blueprint)
