Following are steps to setup Twitter-newsfeed-service on a Ubuntu server 14.04 LTS.

Create a user devuser in the server.

    sudo adduser devuser

Add devuser to sudo group
    
    sudo gpasswd -a devuser sudo

Install updates -
    
    sudo apt-get update

Install mysql  -

    sudo apt-get install mysql-server
    
Setup the code in /home/devuser/projects/Twitter-newsfeed-service

 Changes for configuration settings for twitter4j and mail plugin need to be done in Twitter-newsfeed-service/grails-app/conf/Config.groovy
 
     //twitter4j configuration
    twitter4j {
        OAuthConsumerKey       = 'YOUR TWITTER APP CONSUMER KEY'
	    OAuthConsumerSecret    = 'YOUR TWITTER APP CONSUMER SECRET'
    }
    
    mail {
        host = "YOUR HOST PROVIDER"
        port = PORT NUMBER
        username = "YOUR USERNAME ON THIS HOST"
        password = "YOUR PASSWORD ON THIS HOST"  //if your password contains special characters use a \ before that character eg. pa\$word
        props = ["mail.debug": "false",
                 "mail.smtp.auth":"true"]
        disabled = false
    }
    
    //email configurations
    myapp.email.from="FROM EMAIL ADDRESS"
    myapp.email.replyTo="REPLYTO EMAIL ADDRESS"
    myapp.email.to = "SUPERADMIN OR CONCERNED AUTHORITY EMAIL ADDRESS"

Database setup (considering mysql username and password is root and root respectively)-

    echo "CREATE USER 'admin'@'localhost' IDENTIFIED BY '\!newsfeed\@'" | mysql -uroot -proot
    echo "CREATE DATABASE newsfeed" |  mysql -uroot -proot
    echo "GRANT ALL ON newsfeed.* TO 'admin'@'localhost'" |  mysql -uroot -proot
    echo "flush privileges" |  mysql -uroot -proot

Install java -
 
    sudo apt-get install -y python-software-properties  
    sudo add-apt-repository -y ppa:webupd8team/java  
    sudo apt-get update -y
    
Accept license agreement when asked for the command below -

    sudo apt-get install oracle-java7-installer
    export JAVA_HOME="/usr/lib/jvm/java-7-oracle/"
    export PATH=$JAVA_HOME/bin:$PATH
    sudo ln -s /usr/lib/jvm/java-7=7-oracle /usr/lib/jvm/default-java | true

Setup grails -

    sudo apt-get install curl unzip
    curl -s get.gvmtool.net | bash
    source "/home/devuser/.gvm/bin/gvm-init.sh"
    gvm help
    gvm install grails 2.4.2

Install tomcat -

    sudo apt-get install -y tomcat7
    sudo service tomcat7 stop

Configure tomcat -

    sudo touch /usr/share/tomcat7/bin/setenv.sh

Edit the file - setenv.sh

    sudo vi /usr/share/tomcat7/bin/setenv.sh
    
Copy the following contents into the file setenv.sh and save the file -

    JAVA_OPTS="-Djava.awt.headless=true -server -Xms640M -Xmx640m -XX:MaxPermSize=128M -XX:+UseParNewGC -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=/var/log/tomcat7 -Djava.security.egd=file:/dev/./urandom"

Get the code in the folder /home/devuser/projects/Twitter-newsfeed-service

    /home/devuser/projects/Twitter-newsfeed-service


Build application -
   
    cd /home/devuser/projects/Twitter-newsfeed-service
    grails war
    sudo cp target/Twitter-newsfeed-service.war /var/lib/tomcat7/webapps/Twitter-newsfeed-service.war

Start tomcat -

    sudo service tomcat7 start
    
