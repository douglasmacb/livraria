# livraria
Livraria GuiaBolso Interview - It is a simple application to find books from Kotlin main page using scraping methods.

## # Step 1

We are using mLab cloud-hosted MongoDB free services to provide a NoSQL database for the interview test, but if you want to execute in localhost database, you will need create a collection into livraria and change application.properties with localhost database settings.

```bash
$ use livraria;
$ db.createCollection("livro");
```

## # Step 2

Clone environment from github.

```bash
$ sudo git clone https://github.com/douglasmacb/livraria.git
```

After that, use maven tool to build the project.

```bash
$ sudo mvn clean install -U
```

## # Step 3

Install Docker

```bash
$ sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
$ sudo apt-get update
$ sudo apt-get install docker-ce
```

After install, you need stay in project root directory where Dockerfile exists to execute docker build.

```bash
$ sudo docker build -t livraria .
```
Run the application in container

```bash
$ sudo docker run -p 5000:8080 livraria
```

## How to test application

You may get API details using Swagger UI when application is running.
```bash
http://localhost:5000/sdoc.jsp
```

### Find a Book
```bash
$ curl -X GET \
  http://localhost:5000/books/5c425ccacdd3c03419e98799 \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' 
```

### GET All Books
```bash
$ curl -X GET \
  'http://localhost:5000/books' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' 
  ```
  
### Author
[Douglas Miranda](https://github.com/douglasmacb)

### License
Livraria is available under the Apache 2.0 license. See the [LICENSE](./LICENSE) file for more information.
