# Docker 

## Predictable applications
* Runs the same from dev to uat to prod
* Runs the same on all deployed instances

## Your application
* More than your source code

## Environment
* Hardware resources
* Operating system
* System path
* Installed software / runtimes
* Configuration

## Code vs Runtime Enviornment
* Code refers to the written instructions 
* A runtime environment refers to the software infrastructure in which a code runs.(ex. JRE)

## Multiple apps in one machine
## Problems
* Conflicting runtimes
* Competing for resources
* Unknown dependencies ( one process may break oher process due to undependence in some other project)
* Isolation and Security

## The non-ideal solution
* Separate servers per app.

## Provisioning physical hardware
* Procure hardware
* Mount on server racks
* New server hardware per app
* Plan for peak capacity

## Problems
* Physical servers with slow provisioning.
* Capacity for peak load - often idle.

## From bare metals to VMs
* Provisioning physical hardwares( old school).

## Virtual Machines
* Breakthrough solution to this problem.

## Virtual Machines
* Simulates a h/w that runs a full os.
* Run a os "inside" os.

```
A hypervisor, or virtual machine manager, is a software program that allows multiple operating systems to share a single hardware host. It acts as a intermediary between the operating systems and the hardware, allowing each operating system to run on a separate "virtual" machine. This allows multiple operating systems to run on the same computer at the same time, which can be useful for a variety of purposes, such as testing software on different platforms, consolidating servers, and running multiple applications that are incompatible with each other. There are two main types of hypervisors: type 1, or native or bare-metal hypervisors, and type 2, or hosted hypervisors. Type 1 hypervisors run directly on the host's hardware, while type 2 hypervisors run on top of a host operating system.
```
* See vm hypervisor.png 
ex of hypervisor -> virtual box

* One app per vm 

## Advantages of VMs
* Share server resources
* Shift VMs among servers(This is accomplished by copying the memory state of the VM to the destination server while the VM is still running on the source server. Once the memory state has been copied, the VM is paused on the source server and resumed on the destination server, effectively moving the VM to the new server with minimal downtime.)
* Multiple OS on the same server
* Machine images

## Disadvantages

* See HLD of vm setup.png 
* running multiple of on a single system becomes very intensive.
* guest resources is repeated multiple times
* Wasted computing resources with multiple OS instances
* OS needs maintenance (updates, patches)
* OS licensing costs
* Slow to start up

## Better solution 
* containers

## Virtual machines
* Isolating environments but with a cost
* What do we really need?

## Virtual machines
* To run an OS
## What we need
* To run a process

## Virtual machines
* Virtualizing hardware to run a full OS

## What we need
* Virtualizing OS to run a process

## Isolating processes,how?
When a process is running
* Separation of file systems
* Separation of installed apps and runtime
* Separation of running processes
* Separation of resource usage

## Three technologies
* chroot
* namespaces 
* control groups

## chroot
* A chroot on Unix operating systems is an operation that changes the apparent root directory for the current running process and its children.

## Namespaces
* Namespaces are a feature of the Linux kernel that partitions kernel resources such that one set of processes sees one set of resources while another set of processes sees a different set of resources.

## Namespaces types
* User ID namespace
* Process ID namespace
* And more...

```
The init process is the first process that is started when the system boots up and it is responsible for starting and managing all other processes on the system.
```
```
chroot is not exactly the same thing as a namespace, but it can be used to create a limited, isolated environment for a process, similar to how namespaces are used to isolate processes from each other.
```

## chroot
* Path and file isolation

## Namespaces
* Process and resource isolation

## Control groups
* groups (abbreviated from control groups) is a Linux kernel feature that limits, accounts for, and isolates the resource usage (CPU, memory, disk
1/O, network, etc.) of a collection of processes.
-- noisy neighbour

## Three "features"
* File and path isolation
* namespaces
* groups

* see linux container like setup.png

## This is factilitated by docker

## Docker
* Makes creating containers easy
* Makes delivering software in containers easy

* Docker is a set of platform as a service (PaaS) products that use OS-level virtualization to deliver software in packages called containers.
* Containers are isolated from one another and bundle their own software, libraries and configuration files
* Because all of the containers share the services of a single operating system kernel, they use fewer resources than virtual machines

## Definition on Docker website
```
 A container is a standard unit of software that packages up code and all its dependencies so the application runs quickly and reliably from one computing environment to another.
```

* Process virtualization
* Not machine virtualization

* If we run docker on any other os other than linux , it installs a linux virtual machine.
* In windows it used wsl ( windows sub system for linux )


## Client server models

## Docker uses a client-server architecture.
* Client
* The Docker daemon
* The Docker engine

* The Docker client is the command-line interface that allows users to interact with the Docker system.
* The Docker daemon is a background process that runs on the host machine, it listens to the requests from the client and performs the actions requested by the client.
* The Docker engine is the core technology that enables the running and management of Docker containers, it works as a middleware between the client and daemon, it communicates with the Docker daemon to create, start, stop and manage containers and also communicates with the host operating system to manage the container's resources.

# Images

## Creating containers
```
$ docker run (hypothetical -> suppose this creates a empty container, now what)
```

* we need a starting point
* So, Images

## Containerization
* Creating an isolated container
* Pre-setup the container to what you want

## What do you need?
* Operating system utilities , ex shell 
* Runtimes ( ex jre for java app )
* Your application

All these need to be present before we seal our container from outer world.

## Docker images
* Because an "empty" container doesn't make sense! (set aside some wierd case)
* Template for a container

## Docker image
* Bunch of files that need to be in a container
* Basically a compressed file
* You can use one to start a container
* The container will contain the files from the image

## Imagine a Docker image
* Can contain ls and cat commands
* Can contain some barebones Unix commands
* Can contain full runtimes! (Java, Node.js...)

## Starting a container
```
$ docker run <imagefile>
```
Creating new isolated environment

## Docker images
* You can create one
* Public images repository

## Docker image registries
* Docker Hub (deafult repo, show docker hub)
* Other public registries
* self hosted pvt repos.
* class object analogy

## Running a docker container
```
$ docker run alpine
$ docker run alpine:3.14.3
$ docker run alpine:latest
```

## Observation
* Containerization makes sense only for a process
* we are jailing a process.
* the container ends when process ends

```
$ docker run <image> <command>
$ docker run echo hello
$ docker run alpine ls
$ docker run -it alpine sh
```

* docker run Downloads images automatically

## What happened when we use Docker run

* Downloaded image file from Docker Hub
* Used the linux containerization features
* New container created with the files from the image
* Started a prompt within the container

## show how to run java without installing java in host os
jshell
* similarly we can run python or node etc on our system without installing them.

## how is containers used in dev
* cloud providers like AWS , OCI , GCP have containerisstion and container orchasteration system

## Images vs containers

* Every container has a starting point as an image
* Two containers starting from the same image are exactly identical (at start)
* You can modify things on a container!
* That doesn't modify the image

## docker image is just a file 
```
docker save alpine -o alpine.zip 
```

## Advantage
* Immutability


## Pick your image
* Don't tweak your container

## List of Docker containers
```
docker ps
```


## Important Docker command

## shows running containers
```
docker ps 
```

## normal stop a process
```
docker stop <id> 
```

## force stop a process
```
docker kill <id>
```

## start a stopped process
```
docker start <id>
```

## run a process in background
```
docker run -d <name>  
```

## run a docker container in interactive terminal mode
```
docker run -it <name> <command>
```

## show all the containers
```
docker ps -a
```
## remove a stopped container
```
docker rm <id> 
```
* cannot remove a running container
* explain why docker stop doesnot free up any memory

## docker commit
```
docker commit 
```

## run command in already running container
```
docker exec <id> <command> 
docker exec <id> ls
```

## --rm
```
docker run --rm -it <name> <command> //remove the container when the process is over
```

## naming a container
```
docker run --name <my-container-name> <name> // name a container as choice
```

## setting enviornment variables
```
docker run --name <my-container-name> -e <evn_var_name>=<env_var_val> <name>
```

## mount the host dir to a dir in docker called hostvol
```
docker run -v ${PWD}:/hostvol <name> 
docker run -v <host_sys_path>:<container_path> <name> // container is able to access <host_sys_path>
```
* pwd will be mounted at /hostdir in the container
* we can even save files inside the container in the mounted vol and it will get reflected in the host system
* it acts like a bridge

## run a mysql container
```
docker run --name my-mysql-1 -e MYSQL_ROOT_PASSWORD=secret -v ${PWD}: /var/lib/mysql -d mysql 
``` 
* my sql saves the files in the /var/lib/mysql so here we are plugging our host dir in place of that
* most people recommend runnning stateless app in docker , it is not preferred to run dbs in docker

# volumes in docker 

## Create a new volume: 
```
docker volume create my_volume
```

## List all volumes: 
```
docker volume ls
```

## Inspect a volume: Returns metadata about the volume
```
docker volume inspect my_volume
```

## Remove a volume
```
docker volume rm my_volume
```

## mount a volume 
```
docker run -v my_volume:/app/data my_image
```

## port forwarding in docker
```
docker run -p 8080:80 nginx
docker run -p <hostpost>:<dockerport> <imagename>
```

## both port forwarding and volume mount at once
```
docker run --rm -v ${PWD} : /usr/share/nginx/html -p 8080:80 nginx
```

* A docker image has a layered structure that can be reused by other images
* In case some layer is being used by multiple images and we remove one of those image the layer is just untagged and not deleted.

## informations about every layer
```
docker inspect image <imagename> 
```
[saved checkpoint vid 36](https://www.youtube.com/watch?v%3D82w_vjUFp7k%26list%3DPLqq-6Pq4lTTaXVqNgp1hSsvySCKj0RpUY%26index%3D33)

## docker commit
```
docker commit <container-to-commit> <new-image>
```

## The golden image problem

## Imagine this scenario
* Spin up a container
* Find some issues
* Make some fixes
* Create a new image

## What's the problem?
* Time to update some libraries

## What's the problem?
* Nobody knows what fixes were made

## What's the problem?
* The changes aren't recorded

* You need code

## Dockerfile
* Write code that creates an image

## Dockerfile
* the name of the file is "Dockerfile"

## from
* defined what are the images on which we are basing the new image
* it should be the first line of the Dockerfile
```
FROM alpine
```

## run 
* this allows for a arbiraty command to run when the image is being created
* the command is going to run on the base image.
* can run multiple run commands
```
RUN touch hello.txt
```

## run
* can run multiple commands at once using the following syntax
```
RUN mkdir code; \
    cd code; \
    echo "Hello World" > hello-world.txt
```

## workdir
* it sets the working dir to the specified dir
* run command will run commands in the workdir that was set above 
```
WORKDIR <dir-name>
```

## CMD
* runs when the conatiner is created or starts up
* default cmd is shell for example apline
* the follwing command overrides that command
* there can be only one CMD command
```
CMD echo "Welcome to my container instance"
```
* the following command will run 2 commands at a time
* && is a shell command , so that is a composite type of command
```
CMD echo "Welcome to my container instance" && sh
```
* the cmd command specified in the Dockerfile can be overriden by specifing a command in docker run 

## copy command 
* we can mount a folder whose files we want to be transferred to the container
* but instead we can also copy the images 
* the file should be present in the dir of dockerfile
```
COPY <file-name> <location-on-container>
```
* . specifies the current working directory of image
```
COPY <file-name> .
```

## exec way
* the following code will spun up a shell and on that shell the javac Hello.java will run
```
RUN javac Hello. java
```
* the below is the recommended way where no shell will spun and the javac Hello.java will directly run.
* same applies for CMD command as well
```
RUN [" javac", "Hello. java"]
```

## Limitations of exec way
* wont be able to run the below command because && is a shell command
* nor can we put multiple CMD as there can be only one CMD command
```
CMD ["echo" ,"Welcome to my container instance", "&&" ,"sh"]
```

## ENTRYPOINT
* entrypoint is executed no matter what
* the command in CMD will get appended to the entrypoint
* we can override the CMD in docker run command but the entrypoint will alwaws run
* in below case the Vishal will go as the argument in the entrypoint command
```
ENTRYPOINT [" java", "Hello" ]
CMD "Vishal" 
```
## MAINTAINER
* MAINTAINER Koushik Kothagal <myemail@domain.com>

## ADD
* it is similar to COPY but a bit more
* if the file is a zip file , it will extract the file
* we can also specify the url and it can download the files from there
```
ADD myfile.tar.gz .
```
```
ADD https://www.github.com/mylibrary/releases/1.0.1.jar /lib
```

## ENV
* set enviornment variables
* the variables will be available even while building the image in the subsequent commands
```
ENV DB_HOST_URL mydatabaseur1: 1234/db
```

## expose a port
* The EXPOSE instruction informs Docker that the container listens on the specified network ports at runtime.
```
EXPOSE 8080
```

## Volume
```
VOLUME [" host/dir", "/container/dir"]
```

## USER
* specifies the currnt logged in user
```
USER vishal
```

## each command in docker file creates a new image
* so if we set some enviornment variable using run command , it wont get transferrend to the next image and will be lost.

## Docker networking

## A container
* needs access to the network
* needs internet
* needs to communicate with another container

## What happens when
* docker run httpd
* Access localhost

## Virtualized everything
* Includes network

## Default
* Network bridge

## get the ip of container in bridge(virtual) network
```
docker inspect <container-id>
```
* container can talk to each other through the bridge network
* but if we want the container to be accessible in the host network , we need some kind of forwading from the bridge network to local network.

## Network types
* bridge network 
* host network
* none
* overlay
* ipvlan
* macvlan
* third pary drivers

```
docker network ls
```

## create own networks
* we can even create our own networks
* and put container accouring to our choice in various network
* ex we can create our own bride network , and put different container in diff bridge network

```
docker network create -d bridge mybridge
```

## put a container in some network
* by default the countainer is put in the default bridge network
```
docker run --net=mybridge httpd
```
## Docker compose
## An app
* Needs multiple containers

## Option
* Run CLI commands in a script
* Use docker-compose

## Docker compose
* Takes in a YML file
* File contains info about the setup
* Possibly multiple containers
* One command to manage them all

## docker-compose.yml
```yml
version: '3' 
services:
    mongodb:
        image: mongo 
        ports: 
            - 27017:27017 
        environment:
            - MONGO_INITDB_ROOT_USERNAME=root
            - MONGO_INITDB_ROOT_PASSWORD=root 
    redis:
        image: redis
        ports:
            - 6379:6379 
        environment:
            - REDIS_PASSWORD = root
```