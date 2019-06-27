# Setup Postgres 

## Start

First run:

```docker run --name tenk-challenge --interactive -e POSTGRES_PASSWORD=tenk -p 5432:5432 -d postgres```

Second and ongoing runs:

```docker start --interactive tenk-challenge```

The Postgres database ist running on port 5432

## Control

Container lookup:

````docker container ls````

## Stop

Select the running container using the container lookup and copy the Image-ID. With this id you can run the following command:

```docker container stop <IMAGE_ID>```