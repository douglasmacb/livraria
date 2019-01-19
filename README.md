# livraria
Livraria GuiaBolso Interview - It's a simple application to find books from Kotlin website using regex to get ISBN code.

## # Step 1

Clone environment from github.

```bash
sudo git clone https://github.com/douglasmacb/livraria.git
```

After that, use maven to install

```bash
$ mvn clean install -U
```
## How to test application

### Find a Book
```bash
$ curl -X GET \
  http://localhost:8080/books/5c425ccacdd3c03419e98799 \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d 8881153
```

### GET All Books
```bash
$ curl -X GET \
  'http://localhost:8080/books' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' 
  
