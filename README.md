Predictable applications
    • Runs the same from dev to uat to prod
    • Runs the same on all deployed instances

Your application
    • More than your source code

Environment
    • Hardware resources
    • Operating system
    • System path
    • Installed software / runtimes
    • Configuration

Code vs Runtime Enviornment

Isolation and Security

From bare metals to VMs
    * Provisioning physical hardwares( old school)

Provisioning physical hardware
• Procure hardware
• Mount on server racks
• New server hardware per app
• Plan for peak capacity

Multiple apps in one machine
    * What's the problem?

Environment
    * Global in scope

Problems
• Conflicting runtimes
• Competing for resources
• Unknown dependencies ( one process may break oher process due to undependence in some other project)

The non-ideal solution
• Separate servers per app
• Physical servers with slow provisioning
• Capacity for peak load - often idle

Virtual Machines
* Breakthrough solution to this problem

## Virtual Machines
Simulates a h/w that runs a full os
Run a os "inside" os

A hypervisor, or virtual machine manager, is a software program that allows multiple operating systems to share a single hardware host. It acts as a intermediary between the operating systems and the hardware, allowing each operating system to run on a separate "virtual" machine. This allows multiple operating systems to run on the same computer at the same time, which can be useful for a variety of purposes, such as testing software on different platforms, consolidating servers, and running multiple applications that are incompatible with each other. There are two main types of hypervisors: type 1, or native or bare-metal hypervisors, and type 2, or hosted hypervisors. Type 1 hypervisors run directly on the host's hardware, while type 2 hypervisors run on top of a host operating system.

* See vm hypervisor.png 
ex of hypervisor -> virtual box

* One app per vm 

Advantages of VMs
* Share server resources
* Shift VMs among servers(This is accomplished by copying the memory state of the VM to the destination server while the VM is still running on the source server. Once the memory state has been copied, the VM is paused on the source server and resumed on the destination server, effectively moving the VM to the new server with minimal downtime.)
* Multiple OS on the same server
* Machine images

Disadvantages
* See HLD of vm setup.png 
* running multiple of on a single system becomes very intensive.
* guest resources is repeated multiple times
• Wasted computing resources with multiple OS instances
• OS needs maintenance (updates, patches)
• OS licensing costs
• Slow to start up

Better solution 
* containers

Virtual machines
* Isolating environments but with a cost
Isolating environments
* What do we really need?

Virtual machines
* To run an OS
What we need
* To run a process

Virtual machines
* Virtualizing hardware to run a full OS
What we need
* Virtualizing OS to run a process

Isolating processes
When a process is running
• Separation of file systems
• Separation of installed apps and runtime
• Separation of running processes
• Separation of resource usage

Three technologies
• chroot
• namespaces 
• control groups

chroot
* A chroot on Unix operating systems is an operation that changes the apparent root directory for the current running process and its children.

Namespaces
* Namespaces are a feature of the Linux kernel that partitions kernel resources such that one set of processes sees one set of resources while another set of processes sees a different set of resources.

Namespaces types
• User ID namespace
• Process ID namespace
• And more...

The init process is the first process that is started when the system boots up and it is responsible for starting and managing all other processes on the system.

chroot is not exactly the same thing as a namespace, but it can be used to create a limited, isolated environment for a process, similar to how namespaces are used to isolate processes from each other.

chroot
* Path and file isolation
Namespaces
* Process and resource isolation

Control groups
* groups (abbreviated from control groups) is a Linux kernel feature that limits, accounts for, and isolates the resource usage (CPU, memory, disk
1/O, network, etc.) of a collection of processes.
-- noisy neighbour

Three "features"
• File and path isolation
• namespaces
• groups

* see linux container like setup.png

This is factilitated by docker

## Docker
* Makes creating containers easy
* Makes delivering software in containers easy

* Docker is a set of platform as a service (PaaS) products that use OS-level virtualization to deliver software in packages called containers.
* Containers are isolated from one another and bundle their own software, libraries and configuration files
* Because all of the containers share the services of a single operating system kernel, they use fewer resources than virtual machines

## Definition on Docker website
 A container is a standard unit of software that packages up code and all its dependencies so the application runs quickly and reliably from one computing environment to another.

Process virtualization
Not machine virtualization

If we run docker on any other os other than linux , it installs a linux virtual machine.

In windows it used wsl ( windows sub system for linux )


Docker 
Client server models

Docker uses a client-server architecture.

Client
The Docker daemon
The Docker engine

* The Docker client is the command-line interface that allows users to interact with the Docker system.
* The Docker daemon is a background process that runs on the host machine, it listens to the requests from the client and performs the actions requested by the client.
* The Docker engine is the core technology that enables the running and management of Docker containers, it works as a middleware between the client and daemon, it communicates with the Docker daemon to create, start, stop and manage containers and also communicates with the host operating system to manage the container's resources.

# images

Creating containers
$ docker run (hypothetical -> suppose this creates a empty container, now what)

we need a starting point
So, Images

Containerization
• Creating an isolated container
• Pre-setup the container to what you want

What do you need?
• Operating system utilities , ex shell 
• Runtimes ( ex jre for java app )
• Your application

All these need to be present before we seal our container from outer world.

Docker images
Because an "empty" container doesn't make sense! (set aside some wierd case)
Template for a container

Docker image
• Bunch of files that need to be in a container
• Basically a compressed file
• You can use one to start a container
• The container will contain the files from the image

Imagine a Docker image
• Can contain ls and cat commands
• Can contain some barebones Unix commands
• Can contain full runtimes! (Java, Node.js...)

Starting a container
$ docker run <imagefile>
Creating new isolated environment

Docker images
• You can create one
• Public images repository

Docker image registries
• Docker Hub (deafult repo, show docker hub)
• Other public registries
* self hosted pvt repos.
* class object analogy


When the process ends the container ends
$ docker run alpine
$ docker run alpine:3.14.3
$ docker run alpine:latest

Observation
Containerization makes sense only for a process
* we are jailing a process.
* the container ends when process ends
$ docker run <image> <command>
$ docker run echo hello
$ docker run alpine ls
$ docker run -it alpine sh

docker run
Downloads images automatically

What happened when we use Docker run

What happened
• Downloaded image file from Docker Hub
• Used the linux containerization features
• New container created with the files from the image
• Started a prompt within the container

### show how to run java without installing java in host os
jshell
# similarly we can run python or node etc on our system without installing them.

# how is containers used in dev
# cloud providers like AWS , OCI , GCP have containerisstion and container orchasteration system

### Images vs containers

• Every container has a starting point as an image
• Two containers starting from the same image are exactly identical (at start)
• You can modify things on a container!
• That doesn't modify the image

# show how docker image is just a file 
docker save alpine -o alpine.zip 

# Advantage
Immutability


# Pick your image
Don't tweak your container

docker ps
List of Docker containers

## Important Docker command

docker ps // shows running containers
docker stop <id> //normal stop a process
docker kill <id> //force stop a process
docker start <id> // start a stopped process
docker run <name>
docker run -d <name>  // run the process in background
docker run -it <name> <command> // run a docker container in interactive terminal mode and run the command in that container
docker ps -a
docker rm <id> remove a stopped container
// cannot remove a running container
// explain why docker stop doesnot free up any memory
docker commit 

docker exec <id> <command> // runs command in already running container
docker exec <id> ls

## --rm
docker run --rm -it <name> <command> //remove the container when the process is over
docker run --name <my-container-name> <name> // name a container as choice

docker run --name <my-container-name> -e <evn_var_name>=<env_var_val> <name> // we can set enviorment variables. // this may be useful for setting password of db

docker run -v ${PWD}:/hostvol <name> // now the container the pwd folder from inside the container
docker run -v <host_sys_path>:<container_path> <name> // container is able to access <host_sys_path> from inside the container and the folder is mounted in the <container_path>
// we can even save files inside the container in the mounted vol and it will get reflected in the host system
//it acts like a bridge

docker run --name my-mysql-1 -e MYSQL_ROOT_PASSWORD=secret -v ${PWD}: /var/lib/mysql -d mysql  // my sql saves the files in the /var/lib/mysql so here we are plugging our host dir in place of that


* most people ususlly prefer runnning stateless app in docker , it is not preferred to run dbs in docker

## volumes in docker 
Create a new volume: docker volume create my_volume
List all volumes: docker volume ls
Inspect a volume: docker volume inspect my_volume
Remove a volume: docker volume rm my_volume

docker run -v my_volume:/app/data my_image

## port forwarding in docker
docker run -p 8080:80 nginx
docker run -p <hostpost>:<dockerport> <imagename>
// both port forwarding and volume mount at once
docker run --rm -v ${PWD} : /usr/share/nginx/html -p 8080:80 nginx

A docker image has a layered structure that can be reused by other images
In case some layer is being used by multiple images and we remove one of those image the layer is just untagged and not deleted

docker inspect image <imagename> // this will provide the informations about every layer of the image

