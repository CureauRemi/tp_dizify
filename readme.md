#  TP Java-React Dizify

##  School group project,  4 :

 1. Gurvan touzé
 2. Jordan Lefeuvre
 3. Anthony Elineau
 4. Rémi Cureau

# Server

[http://51.91.58.213/dizify/#/](http://51.91.58.213/dizify/#/)

# Front
## Before starting the project for the first time
```bash
yarn install
```

## Running the app
```bash
yarn start
```
Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.


## Building the app
```bash
yarn build
```
Build the app on the path '/'
You need to change the field homepage in the package.json to change the path
for example : 'http://localhost/dizify/'

#Back server (Debian)

package installé:
maven
java

Install:
```bash
mvn install
```
Clean:
```
mvn clean install
```

## Service
/etc/systemd/system/dizifyback.service
````bash
[Unit]
Description=Dizify Rest server
After=syslog.target

[Service]
ExecStart=/usr/bin/java -jar /var/www/html/tp_dizify/back/target/rest-ws-1.0.0.jar
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target
```

## LAUNCH 
systemctl start dizifyback


