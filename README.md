# livraria
Livraria GuiaBolso Interview - It is a simple application to find books from Kotlin main page using scraping methods.

## # Step 1

We are using mLab cloud-hosted MongoDB free services to provide a NoSQL database for the interview test, but if you want to execute in localhost database, you will need create a collection into livraria.

```bash
use livraria;
db.createCollection("livro");
```

## # Step 2

Clone environment from github.

```bash
sudo git clone https://github.com/douglasmacb/livraria.git
```

After that, use maven tool to build the project.

```bash
$ sudo mvn clean install -U
```

## # Step 3

You need stay in root directory where Dockerfile exists to execute docker build to create a container image.

```bash
docker build -t livraria .

```
docker run -p 8092:8092 livraria

## How to test application

You may get API details using Swagger UI when application is running.
```bash
http://localhost:8092/sdoc.jsp
```

### Find a Book
```bash
$ curl -X GET \
  http://localhost:8092/books/5c425ccacdd3c03419e98799 \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' 
```

### GET All Books
```bash
$ curl -X GET \
  'http://localhost:8092/books' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' 
  ```
  
### Author
[Douglas Miranda](https://github.com/douglasmacb)

### License
Livraria is available under the Apache 2.0 license. See the [LICENSE](./LICENSE) file for more information.
