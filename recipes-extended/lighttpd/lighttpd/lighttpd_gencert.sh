#!/bin/sh

set -e

tmpdir="/tmp"
certdir="/etc/lighttpd"

if [ -f $certdir/server.pem ] && [ -f $certdir/server.key ] ; then
    echo "certificate already exists."
    echo "To recreate the certificate, delete the file $certdir/server.pem"
    exit 0
fi

#generate https certificate
cat > $tmpdir/cert.cnf << "EOF"
RANDFILE = /dev/urandom
[ req ]
default_bits = 1024
encrypt_key = yes 
distinguished_name = req_dn
x509_extensions = cert_type
prompt = no

[ req_dn ]
C=FR
ST=FRANCE
L=Paris
O=Calaos
OU=Calaos
CN=calaos.fr
emailAddress=none@calaos.fr

[ cert_type ]
basicConstraints                = critical,CA:FALSE
nsCertType                      = server
nsComment                       = "Calaos SSL Certificate"
subjectKeyIdentifier            = hash
authorityKeyIdentifier          = keyid,issuer:always
issuerAltName                   = issuer:copy
keyUsage                        = keyEncipherment, digitalSignature
extendedKeyUsage                = serverAuth
EOF

openssl req -new -outform PEM -config $tmpdir/cert.cnf -out $certdir/server.pem -newkey rsa:2048 -nodes -keyout $certdir/server.key -keyform PEM -days 9999 -x509

rm -f $tmpdir/cert.cnf

echo "Successfully generated self-signed certificate"

