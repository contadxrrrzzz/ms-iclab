#!/bin/bash
echo "Entro shell"

if [[ $# -eq 3 ]]; then
        actual="$1";
        nuevo="$2";
        path="$3";
echo "$actual-$nuevo-$path"
sed -i "12,14 s/$actual/$nuevo/g" $path/pom.xml
else 
        echo "fallo";

fi
exit 0