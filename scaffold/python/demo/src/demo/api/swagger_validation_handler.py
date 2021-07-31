import jsonschema
from jsonschema import ValidationError
from werkzeug.exceptions import abort

try:
    from http import HTTPStatus
except ImportError:
    import httplib as HTTPStatus
try:
    import simplejson as json
except ImportError:
    import json

from flask import Response


def validation_error_inform_error(err, data, schema):
    """
    Custom validation error handler which produces 404 Bad Request
    response in case validation fails and returns the error
    """
    abort(Response(json.dumps({"error": err.message}), status=HTTPStatus.BAD_REQUEST))


def validation_error_404(err, data, schema):
    """
    Custom validation error handler which produces 404 Not Found
    response in case validation fails instead of 400 Bad Request
    """
    abort(Response(status=HTTPStatus.NOT_FOUND))


def validation_error_try_to_accept(err, data, schema):
    """
    Custom validation error handler which attempts alternative
    validation
    """
    if not isinstance(err, ValidationError):
        abort(Response(err, status=HTTPStatus.BAD_REQUEST))

    alernative_schema = dict(schema)
    alernative_schema["properties"]["running_time"].update(
        {"description": "Films's running time", "type": "integer", "example": 169}
    )

    try:
        jsonschema.validate(data, alernative_schema)
    except ValidationError as err:
        abort(Response(str(err), status=400))
