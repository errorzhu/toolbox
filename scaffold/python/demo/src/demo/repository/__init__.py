from repository.mysql_client import MySQLClient
from api import app
import base64


class DbFactory:
    def __init__(self):
        pass

    @staticmethod
    def get_client():
        host = app.config["DB_HOST"]
        password = base64.b64decode(app.config["DB_PASSWORD"]).decode()
        port = int(app.config["DB_PORT"])
        user = app.config["DB_USER"]
        database = app.config["DB_DATABASE"]
        return MySQLClient(
            host=host, port=port, user=user, password=password, database=database
        )
