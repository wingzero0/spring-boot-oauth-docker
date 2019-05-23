Build a spring boot oauth server with mysql db

## docker
linux container

    $> docker-composer up # create env
    $> docker-composer start # rerun env
    $> docker-composer stop # stop env
    $> docker-composer down # delete env


## testing command
generate access token
curl -X POST \
	http://localhost:8081/auth/oauth/token \
	-F grant_type=password \
	-F username=john \
	-F password=456 \
	-F client_id=spring-security-oauth2-read-write-client \
	-F client_secret=spring-security-oauth2-read-write-client-password1234

use access token to get value
curl http://localhost:8081/auth/user/me -H "Authorization: Bearer 5a428f4c-3356-41c3-9a57-ca54971d75e0"


curl -X POST \
	http://localhost:8081/auth/oauth/token \
	-F grant_type=client_credentials \
	-F client_id=spring-security-oauth2-read-write-client \
	-F client_secret=spring-security-oauth2-read-write-client-password1234

curl http://localhost:8081/auth/app/fullUserList -H "Authorization: Bearer 40e82601-421d-4fbf-9de3-5a5c1ca80550" 