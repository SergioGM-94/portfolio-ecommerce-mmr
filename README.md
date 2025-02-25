
# +MMR | E-commerce backend

This is an API service for an e-commer web application called "Check-it". This is a personal project built with Java, Spring Boot, and Docker. The service is hosted in "www.render.com"


## Demo

As mentioned in the introduction paragraph, the services in hosted in "www.render.com". The Base URL for this service is:

https://portfolio-ecommerce-mmr.onrender.com

Currently, I'm using the free plan on the website to host the service, so in order to use it or test it, you need to access the url and wait for approximately one minute for the service to go live. After that, you can start using the API.


## API Reference

#### Register User

```http
  POST /api/user
```
Json model:

{
    "user": "",
    "email": "",
    "password": "",
    "type": ""
}

#### User Login

```http
  POST /api/user/login
```
Json model:

{
    "email": "",
    "password": ""
}

#### Get user list

```http
  GET /api/user
```
#### Get users by userId

```http
  GET /api/user/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `int` | **Required**. Searched user ID |

#### Update User

```http
  PUT /api/user/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `int` | **Required**. Searched user ID |

Json model:

{
    "user": "",
    "email": "",
    "password": "",
    "type": ""
}

#### Delete User by user ID

```http
  DELETE /api/user/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `int` | **Required**. Searched user ID |

#### Register Product

```http
  POST /api/product
```

Json model:

{
    "title": "",
    "price": ,
    "categoryId": ,
    "description": "",
    "image": "",
    "categoryObj":{
        "categoryId": 
    }
}


#### Get products

```http
  GET /api/product?categoryId=#&sort=asc
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `categoryId` | `int` | **Required**. Searched category ID |
| `sort` | `asc` | **Required**. search order |

#### Get product by product ID

```http
  GET /api/product/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `productId` | `int` | **Required**. Searched product ID |

#### Update product

```http
  PUT /api/product/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `int` | **Required**. Searched product ID |

Json model:

{
    "title": "",
    "price": ,
    "categoryId": ,
    "description": "",
    "image": "",
    "categoryObj":{
        "categoryId": 
    }
}

#### Delete product

```http
  DELETE /api/product/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `int` | **Required**. Searched product ID |

#### Create category

```http
  POST /api/category
```
Json model:

{
    "name": ""
}

#### List categories

```http
  GET /api/category
```

#### Update Category

```http
  PUT /api/category/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `int` | **Required**. Searched category ID |

Json model:

{
    "name": ""
}

#### Delete category

```http
  DELETE /api/category/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `int` | **Required**. Searched category ID |



