docker-compose up -d
./buildjeopardized.sh
docker run -i --rm -p 8080:8080 --net jeopardized_jeopardynet --link  jeopardydatasource --name jeopardy jeopardized
