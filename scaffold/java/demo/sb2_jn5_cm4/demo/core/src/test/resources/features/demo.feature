Feature: 地址测试

  Scenario: 测试创建地址

    Given 输入地址信息
      | id  | userId | province | city | street |
      | 100 | 1    | 江苏      | 南京  | 颐和路  |

    When 发送创建请求 'WITH ALL REQUIRED FIELDS'
    Then 保存成功 'IS SUCCESSFUL'