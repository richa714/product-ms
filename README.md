# product-ms


GET Products----- http://localhost:8080/api/products/
POST Product----- http://localhost:8080/api/products
PUT Product------ http://localhost:8080/api/products/101
DELETE Product--- http://localhost:8080/api/products/101

Body--
{
"name":"Whirlpool",
"description":"Whirlpool 190L",
"price":42342,
"type":"Refrigerator"
}


GET Reviews----- http://localhost:8080/api/products/101/reviews/
POST Reviews---- http://localhost:8080/api/products/101/reviews/
PUT Reviews----- http://localhost:8080/api/products/101/reviews/501
DELETE Reviews-- http://localhost:8080/api/products/101/reviews/501