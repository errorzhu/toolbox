from repository.client import DbClient
from playhouse.pool import PooledMySQLDatabase


# todo(zhuyu: 配置文件密码加密)


class MySQLClient(DbClient):
    def __init__(self, host, port, user, password, database):
        settings = {"host": host, "password": password, "port": port, "user": user}
        self.__db = PooledMySQLDatabase(database, **settings)

    def is_closed(self):
        return self.__db.is_closed()

    def get_db(self):
        return self.__db

    def close(self):
        self.__db.close()
