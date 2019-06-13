#!/usr/bin/env bash

# Download mo for mustache template.
curl -sSL https://git.io/get-mo -o ./mo

# Make executable
chmod +x ./mo

JENKINS_URL=$1
JENKINS_USER=$2
JENKINS_TOKEN=$3

export CREDENTIAL_ID=$4
export NEW_VALUE=$5

curl --user $JENKINS_USER:$JENKINS_TOKEN -d "script=$(cat updatepw.groovy | ./mo )" http://$JENKINS_URL/script

