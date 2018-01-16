Create the postgres docker container:

    $ docker run --name postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres

Connect with psql:

    $ docker run -e PGPASSWORD=mysecretpassword -it --rm --link postgres:postgres postgres psql -h postgres -U postgres

Create database crudstore

    postgres=# CREATE DATABASE crudstore;

Connect to database crudstore

    postgres=# \c crudstore;