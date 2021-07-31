from peewee import Model, CharField
from repository import DbFactory


class UnknownField(object):
    def __init__(self, *_, **__):
        pass


class BaseModel(Model):
    class Meta:
        database = DbFactory.get_client().get_db()


class PackageInfo(BaseModel):
    compression_suffix = CharField(null=True)
    os_version = CharField(null=True)
    package_name = CharField()
    package_version = CharField(null=True)

    class Meta:
        table_name = "package_info"
