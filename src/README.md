Подключение postgres и pgAdmin локально с помощью Docker

1. Запуск контейнера postgres
docker pull postgres:latest (при необходимости)
docker run --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=user -d postgres

2. Запуск контейнера pgAdmin
docker pull dpage/pgadmin4 (при необходимости)
docker run --name some-pg-admin -p 80:80 --link some-postgres -e "PGADMIN_DEFAULT_EMAIL=email@domain.com" -e "PGADMIN_DEFAULT_EMAIL=postgres" -d dpage/pgadmin4

3. Настройка веб-приложения
Зайти в веб-приложение pgAdmin http://localhost:80, залогиниться с помощью параметров подключения pgAdmin,
которые указали при инициализации контейнера.
При добавлении нового сервера в веб-приложение phAdmin использовать имя сервера some-postgres,
credentials - параметры подключения postgres, которые указали при инициализации контейнера,
host - из поля IPAdress контейнера postgres, которое можно найти при помощи команды
docker inspect container_id | grep IPAddress

P.S.: Обратите внимание, --link some-postgres когда мы вызывали файл pgAdmin.
Эта команда делает контейнер postgres видимым для контейнера pgAdmin.