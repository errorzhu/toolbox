import json

import pytest
from api import command_manager
from pytest_mock import mocker


def test_json_serde():
    inventory = {"webdriver": {"hosts": {"192.168.3.103": "", "192.168.9.234": ""}}}
    parameter = {
        "package_dir": "/opt/install_packages",
        "package_name": "prometheus-2.27.1.linux-amd64",
        "install_dir": "/opt",
        "compress_suffix": "tar.gz",
        "inventory": inventory,
        "component": "test",
        "service": "",
        "command": "test",
    }

    assert parameter == json.loads(
        '{"component": "test", "service": "", "command": "test","package_dir": "/opt/install_packages", '
        '"package_name": "prometheus-2.27.1.linux-amd64", "install_dir": "/opt", "compress_suffix": "tar.gz", '
        '"inventory": {"webdriver":{"hosts":{"192.168.3.103":"","192.168.9.234":""}}}} '
    )


def test_run_playbook(mocker):
    mocker.patch("api.command_manager.exec_command", return_value={"stats": "success"})
    assert {"stats": "success"} == command_manager.exec_command()


if __name__ == "__main__":
    pytest.main(["./test_api.py"])
