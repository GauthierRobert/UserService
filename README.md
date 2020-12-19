# LifeBook

docker pull postgres:latest

docker run --rm --name rabbitmq -p 5672:5672 rabbitmq:3-management

docker run --rm --name pg-docker -e POSTGRES_PASSWORD=postgres -v %cd%/data:/data -d -p 5432:5432 postgres

docker exec -it pg-docker bash -c "psql -U postgres < data/init.sql"