#language: ru
  Функционал: тестирование API
    Сценарий: test GET
      Дано используем стандартные параметры запроса
      Если сделать GET запрос на endpoint "/pet"
      Тогда код ответа будет 405
