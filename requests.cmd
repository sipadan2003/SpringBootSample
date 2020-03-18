@echo off

echo 404
curl -X GET http://localhost:8080/test404

echo;
echo hello w/o parameter
curl -X GET http://localhost:8080/api/v1/hello

echo;
echo hello w/ parameter
curl -X GET http://localhost:8080/api/v1/hello?name=Hoge
