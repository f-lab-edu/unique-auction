ACCESSKEY="3llRJaaOdNenSwt7HruV"

SECRETKEY="TGFs7Jjsul9376tTIsypkUNN1KvuxE26jQHmhvp0"

project=4873

stage=5632

scenario=5893

METHOD="POST"



function makeSignature() {

        nl=$'\\n'

        SIG="$4"' '"$5"${nl}"$3"${nl}"$1"

        SIGNITURE=$(echo -n -e "$SIG"|iconv -t utf8 |openssl dgst -sha256 -hmac $2 -binary|openssl enc -base64)

}



TIMESTAMP=$(echo $(($(date +%s%N)/1000000)))

URI="/api/v1/project/$project/stage/$stage/scenario/$scenario/deploy"



makeSignature $ACCESSKEY $SECRETKEY $TIMESTAMP $METHOD $URI



curl -X $METHOD https://vpcsourcedeploy.apigw.ntruss.com$URI -H "accept: application/json" -H "x-ncp-region_code: KR" -H "x-ncp-iam-access-key: $ACCESSKEY" -H "x-ncp-apigw-timestamp: $TIMESTAMP" -H "x-ncp-apigw-signature-v2: $SIGNITURE"
