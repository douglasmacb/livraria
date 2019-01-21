# livraria
Livraria GuiaBolso Interview - It's a simple application to find books from Kotlin main page using regex to get ISBN code.

## # Step 1

Create a new collection in mongodb

```bash
use livraria;
db.createCollection("livro");
```

## # Step 2

Clone environment from github.

```bash
sudo git clone https://github.com/douglasmacb/livraria.git
```

After that, use maven tool to build and generate a executable jar file

```bash
$ mvn clean install -U
```

## # Step 3

Open target folder and run jar file to start the application

```bash
java -jar livraria-1.0.jar
```


## How to test application

You may get API details using Swagger UI when application is running
```bash
http://localhost:8092/sdoc.jsp
```

### Find a Book
```bash
$ curl -X GET \
  http://localhost:8092/books/5c425ccacdd3c03419e98799 \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d 8881153
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
