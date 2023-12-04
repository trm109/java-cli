# README.

## Installation.
All you need to have installed is Docker and Docker Compose.
- Docker: https://docs.docker.com/engine/install/
- Docker-Compose: https://docs.docker.com/compose/install/

I recommend installing the Desktop version of Docker, as it comes with Docker Compose.

## Usage.
Run the project with the following command:
```bash
./run.sh
```
This will start the project.
- The CLI is called `./planner` and help is accessible by running `./planner --help`.
    - From here, we recommend populating the databases using `./populate`.
- The Database Viewer is accessible at `http://localhost:8080`.
    - The email is `admin@email.com`, and the password is `admin`
    - The database is available with these settings:
        - Hostname/Address: `db`
        - Port: `5432`
        - Maintenance Database: `postgres`
        - Username: `user`
        - Password: `password!`
