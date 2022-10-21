**Подключение postgres и pgAdmin локально с помощью Docker**

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

Работа с postgres через консоль
Подключиться к контейнеру: docker exec -it some-postgres bash
Зайти как пользователь: psql -U user
(по умолчанию используется БД postgres, можно явно указать БД с помощью команды postgres-# \c test_base)
Получить список таблиц в БД: \d


**Порядок создания таблиц с помощью liquibase**
1. Создать liquibase-outputChangeLog.xml c помощью команды: mvn liquibase:generateChangeLog, добавить его в файл db.changelog-master.xml
2. Включить генерацию таблиц с помощью liquibase и отключить аналогичную опцию hibernate
spring.liquibase.enabled=true
spring.jpa.hibernate.ddl-auto=none
Запустить приложение и убедиться, что все таблицы сущностей созданы в БД и добавились две новые таблицы логов
3. Заполнить таблицу данными. Eсли БД ранее была заполнена другими способами, например, с помощью hibernate, то данные сохранятся в БД
или можно заполнить их:
 - по-старому, временно отключив liquibase и включив первоначальную инициализацию данных.
 - через liquibase: сгенерировать файл changelog плагином и конце файла добавить новый changeSet (см. https://docs.liquibase.com/change-types/community/sql-file.html)
