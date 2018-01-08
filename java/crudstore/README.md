Create the postgres docker container:

    $ docker run --name postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

Connect with psql:

    $ docker run -e PGPASSWORD=mysecretpassword -it --rm --link postgres:postgres postgres psql -h postgres -U postgres

