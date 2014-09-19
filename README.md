SSHCredentialManagement
========================

This is an example application using [Spring Boot](http://projects.spring.io/spring-boot/ "Spring Boot").

###Downloading the example
	git clone https://github.com/martijnhaex/SSHCredentialManagement.git

###Running the example
	mvn spring-boot:run

Next up, go to **localhost:7007** to see the index page, with an overview of all your SSH Credentials. At startup there will be no SSH Credentials. Through the import menu you can batch import a list of SSH Credentials.

###sshpass
> You can use sshpass command to provide password for ssh based login. sshpass is a utility designed for running ssh using the mode referred to as "keyboard-interactive" password authentication, but in non-interactive mode. ssh uses direct TTY access to make sure that the password is indeed issued by an interactive keyboard user. sshpass runs ssh in a dedicated TTY, fooling it into thinking it is getting the password from an interactive user.

More information, about installing, can be found on [sshpass](https://gist.github.com/arunoda/7790979 "sshpass").